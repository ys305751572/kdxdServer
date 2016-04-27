package com.leoman.service.impl;

import com.leoman.core.Constant;
import com.leoman.core.bean.Result;
import com.leoman.dao.ProductDao;
import com.leoman.entity.Coupon;
import com.leoman.entity.KUser;
import com.leoman.entity.Product;
import com.leoman.entity.ProductBuyRecord;
import com.leoman.service.*;
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

/**
 * Created by Administrator on 2016/3/10.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;

    @Autowired
    private PsService psService;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CouponService couponService;

    @Autowired
    private ProductBuyRecordService pbservice;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ImageService imageService;

    @Override
    public Page<Product> findPage(final Product pro, final Integer type, int pagenum, int pagesize) {
        Specification<Product> spec = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (pro.getTitle() != null) {
                    criteriaBuilder.like(root.get("title").as(String.class), pro.getTitle());
                }
                if (type != null) {
                    if (type == 0) {
                        // 待抢购 开始时间 大于 当前时间 && 状态 == 0
                        criteriaBuilder.lt(root.get("startDate").as(Long.class), System.currentTimeMillis());
                        criteriaBuilder.equal(root.get("status").as(Integer.class), 0);
                    } else if (type == 1) {
                        // 抢购中 开始时间 小于 当前时间 && 结束时间 大于 当前时间 && 状态 == 0
                        criteriaBuilder.gt(root.get("startDate").as(Long.class), System.currentTimeMillis());
                        criteriaBuilder.lt(root.get("endDate").as(Long.class), System.currentTimeMillis());
                        criteriaBuilder.equal(root.get("status").as(Integer.class), 0);
                    } else {
                        // 已结束  结束时间 小于 当前时间  && 状态 == 1
                        criteriaBuilder.gt(root.get("endDate").as(Long.class), System.currentTimeMillis());
                        criteriaBuilder.equal(root.get("status").as(Integer.class), 1);
                    }
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec, new PageRequest(pagenum - 1, pagesize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Long findBuyCount(Long id) {
        String sql = "select count(t) from ProductBuyRecord t where t.resultStatus = 0 and t.product.id = " + id + " and t.reset != 1";
        Query query = em.createQuery(sql, Long.class);
        return (Long) query.getSingleResult();
    }

    @Transactional
    @Override
    public void createOrder(Long productId, Long pbrId, Long serviceId, HttpServletRequest request, HttpServletResponse response) {

        KUser weixinUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        Product product = this.getById(productId);
        ProductBuyRecord record = pbservice.getById(pbrId);

        com.leoman.entity.ProductService ps = psService.getById(serviceId);
        record.setPayMoney(ps.getMoney());
        record.setPayDays(ps.getDays());

        Long startDate = product.getStartDate();
        Long endDate = DateUtils.daysAfter(new Date(startDate), ps.getDays());
        String result = "";
        try {
            result = "水果一份(" + DateUtils.longToString(startDate, "yyyy-MM-dd HH:mm:ss") + "~" + DateUtils.longToString(endDate, "yyyy-MM-dd HH:mm:ss") + ")";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        record.setResult(result);
        WebUtil.print(response, new Result(true).msg("操作成功"));
    }

    @Override
    public Page<Product> findList(int pageNum, int pageSize) {
        return dao.findPage(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Transactional
    @Override
    public void deleteImages(Long productId, Integer imageId) {
        productImageService.deleteProductImageByProductIdAndImageId(productId, imageId);
        imageService.deleteById(imageId);
    }


    @Override
    public Product reduceInventory(Long id) {
        Product product = dao.findOne(id);
        product.setInventory(product.getInventory() - 1);
        return dao.save(product);
    }

    /**
     * @param id     商品ID
     * @param isUsed
     * @param userId
     * @return
     */
    @Transactional
    @Override
    public ProductBuyRecord createProductByRecord(HttpServletResponse response, Long id, Boolean isUsed, Long userId) {
        boolean isGetCoupon = false;
        long endDate = 0;

        Integer buyCount = pbservice.findCountByProductId(id);
        Product product = getById(id);
        if (buyCount >= product.getCounts()) {
            WebUtil.print(response, new Result(false).msg("商品已抢完!"));
            return null;
        }

        ProductBuyRecord pbr = new ProductBuyRecord();

        KUser user = new KUser();
        user.setId(userId);
        pbr.setUser(user);

        Product _product = new Product();
        _product.setId(id);
        pbr.setProduct(_product);
        pbr.setIsUserCoupons(isUsed ? 1 : 0);


        // 是否使用优惠券
        if (isUsed) {
            List<Coupon> list = couponService.findListByUserId(userId);
            if (null == list || list.size() == 0) {
                WebUtil.print(response, new Result(false).msg("您没有优惠券!"));
                return null;
            } else {
                Coupon coupon = list.get(0);
                coupon.setIsUsed(1);

                // 将优惠券状态更改为已使用
                couponService.update(coupon);
            }

            // 使用了必中券，就一定会抢购成功
            pbr.setResultStatus(0);
        } else {
            Boolean flag = KdxgUtils.isGetByprobability();
            if (flag) {
                pbr.setResultStatus(0);
                isGetCoupon = KdxgUtils.isGetByprobability();
                if (isGetCoupon) {
                    pbr.setIsGetCoupons(1);
                    couponService.createCoupon(userId);
                } else {
                    pbr.setIsGetCoupons(0);
                }

                if (isGetCoupon) {
                    endDate = DateUtils.daysAfter(new Date(), 3);
                }
            } else {
                pbr.setIsGetCoupons(0);
                pbr.setResultStatus(1);
            }
        }

        pbr.setCouponsEndDate(endDate);
        pbr.setPayDays(0);
        pbr.setPayMoney(0.0);
        pbr.setResult("");
        pbr = pbservice.create(pbr);
        return pbr;
    }

    /**
     * 消费一张优惠券
     *
     * @param userId
     */
    public void toReduce(Long userId) {
        String sql = "select a.* from tb_coupons a where a.user_id = " + userId + " and a.is_used = 0 and a.end_date > UNIX_TIMESTAMP() * 1000 ORDER BY a.end_date DESC limit 1";
        Query query = em.createNativeQuery(sql, Coupon.class);
        Coupon c = (Coupon) query.getSingleResult();
        c.setIsUsed(1);
        couponService.update(c);
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
     *
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
        for (Long id : ids) {
            deleteById(id);
        }
    }
}