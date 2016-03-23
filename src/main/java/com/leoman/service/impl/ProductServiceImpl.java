package com.leoman.service.impl;

import com.leoman.core.Constant;
import com.leoman.core.bean.Result;
import com.leoman.dao.ProductDao;
import com.leoman.entity.*;
import com.leoman.service.*;
import com.leoman.service.ProductService;
import com.leoman.utils.DateUtils;
import com.leoman.utils.KdxgUtils;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/10.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;

    @Autowired
    private ProductImageService service;

    @Autowired
    private PsService psService;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CouponService cService;

    @Autowired
    private ProductBuyRecordService pbservice;

    @Autowired
    private KUserService userService;


    @Override
    public Page<Product> findPage(final Product pro,final Integer type, int pagenum, int pagesize) {
        Specification<Product> spec = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(pro.getTitle() != null) {
                    criteriaBuilder.like(root.get("title").as(String.class),pro.getTitle());
                }
                if(type != null) {
                    if(type == 0) {
                        // 待抢购 开始时间 大于 当前时间 && 状态 == 0
                        criteriaBuilder.lt(root.get("startDate").as(Long.class),System.currentTimeMillis());
                        criteriaBuilder.equal(root.get("status").as(Integer.class),0);
                    }
                    else if(type == 1) {
                        // 抢购中 开始时间 小于 当前时间 && 结束时间 大于 当前时间 && 状态 == 0
                        criteriaBuilder.gt(root.get("startDate").as(Long.class),System.currentTimeMillis());
                        criteriaBuilder.lt(root.get("endDate").as(Long.class),System.currentTimeMillis());
                        criteriaBuilder.equal(root.get("status").as(Integer.class),0);
                    }
                    else {
                        // 已结束  结束时间 小于 当前时间  && 状态 == 1
                        criteriaBuilder.gt(root.get("endDate").as(Long.class),System.currentTimeMillis());
                        criteriaBuilder.equal(root.get("status").as(Integer.class),1);
                    }
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
    }

    @Override
    public Long findBuyCount(Long id) {
        String sql = "select count(t) from ProductBuyRecord t where t.product.id = " + id;
        Query query = em.createQuery(sql,Long.class);
        return (Long) query.getSingleResult();
    }

    @Transactional
    @Override
    public void buy(Long productId, Long serviceId, Long userId, Boolean isUsed, HttpServletRequest request,HttpServletResponse response) {

        KUser weixinUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        Integer buyCount = pbservice.findCountByProductId(productId);
        Product product = this.getById(productId);
        if(buyCount >= product.getCounts()) {
            WebUtil.print(response, new Result(false).msg("商品已抢完!"));
            return;
        }

        ProductBuyRecord record = new ProductBuyRecord();
        if(isUsed) {
            record.setResultStatus(0);
            Integer counts = cService.findCountByUserId(weixinUser.getId());
            if(counts == 0) {
                WebUtil.print(response, new Result(false).msg("您没有优惠券!"));
                return;
            }
        }
        else {
            // TODO 没有使用优惠券只有25%的概率可能抢到
            // TODO 没有使用优惠券在抢购成功后有25%的概率能够得到优惠券
            if(!KdxgUtils.isGetByprobability()) {
                WebUtil.print(response, new Result(false).msg("很遗憾，您没有抢到!"));
                record.setResultStatus(1);
            }else {
                record.setResultStatus(0);
            }
        }

        KUser user = new KUser();
        user.setId(weixinUser.getId());
        record.setUser(user);


//        record.setProductId(productId);
        record.setIsUserCoupons(1);

        com.leoman.entity.ProductService ps = psService.getById(serviceId);
        record.setPayMoney(ps.getMoney());
        record.setPayDays(ps.getDays());

        Long startDate = product.getStartDate();
        Long endDate = DateUtils.daysAfter(new Date(startDate),ps.getDays());
        String result = "";
        try {
            result = "水果一份(" + DateUtils.longToString(startDate,"yyyy-MM-dd HH:mm:ss") + "~" + DateUtils.longToString(endDate,"yyyy-MM-dd HH:mm:ss") + ")";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        record.setResult(result);
        WebUtil.print(response, new Result(true).msg("抢购成功"));


    }



    @Override
    public Product reduceInventory(Long id) {
        Product product = dao.findOne(id);
        product.setInventory(product.getInventory() - 1);
        return dao.save(product);
    }

    /**
     *
     * @param id     商品ID
     * @param isUsed
     * @param userId
     * @param model
     * @return
     */
    @Transactional
    @Override
    public Model toOrder(Long id, Boolean isUsed, Long userId, Model model) {


        boolean isGetCoupon = false;
        boolean resultStatus = false;

        // 获取用户默认地址 ,没有则为空
        Address address = userService.findDefaultAddressByUserId(userId);
        model.addAttribute("address",address);

        // 是否获得优惠券
        if(isUsed) {
            resultStatus = true;
        }
        else {
            if(KdxgUtils.isGetByprobability()) {
                resultStatus = true;
                model.addAttribute("success",true);
                isGetCoupon = KdxgUtils.isGetByprobability();
                if(isGetCoupon) {
                    long endDate = DateUtils.daysAfter(new Date(),3);
                    model.addAttribute("endDate",endDate);
                }
            }
        }
        ProductBuyRecord pbr = new ProductBuyRecord();

        KUser user = new KUser();
        user.setId(userId);
        pbr.setUser(user);

        Product _product = new Product();
        _product.setId(id);
        pbr.setProduct(_product);

        pbr.setIsUserCoupons(isUsed ? 1 : 0);
        pbr.setIsGetCoupons(isGetCoupon ? 1 : 0);
        pbr.setResultStatus(resultStatus ? 0 : 1);
        pbr.setPayDays(0);
        pbr.setPayMoney(0.0);
        pbr.setResult("");
        pbr = pbservice.create(pbr);

        model.addAttribute("pbr",pbr);
        return model;
    }

    /**
     * 消费一张优惠券
     * @param userId
     */
    public void toReduce(Long userId) {
        String sql = "select a.* from tb_coupons a where a.user_id = " + userId + " and a.is_used = 0 and a.end_date > UNIX_TIMESTAMP() * 1000 ORDER BY a.end_date DESC limit 1" ;
        Query query = em.createNativeQuery(sql,Coupon.class);
        Coupon c = (Coupon) query.getSingleResult();
        c.setIsUsed(1);
        cService.update(c);
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Page<Product> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<Product> find(int pageNum) {
        return null;
    }

    @Override
    public Product getById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public Product deleteById(Long id) {
        Product info = dao.findOne(id);
        dao.delete(info);
        return null;
    }

    @Override
    public Product create(Product product) {
        return this.update(product);
    }

    /**
     * 新增/编辑
     * @param product
     * @return
     */
    @Transactional
    @Override
    public Product update(Product product) {
//        Set<Image> set = product.getList();
//        ProductImage pi = null;
//        for (Image image : set) {
//            pi = new ProductImage();
//            pi.setImageId(image.getId());
//            pi.setProductId(product.getId());
//            service.create(pi);
//        }
//        Set<com.leoman.entity.ProductService> set = product.getServiceList();
//
//        product.setServiceList(null);
//        product = dao.save(product);
//        for (com.leoman.entity.ProductService ps : set) {
//            ps.setProductId(product.getId());
//            psService.create(ps);
//        }
        return dao.save(product);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for(Long id : ids) {
            deleteById(id);
        }
    }
}