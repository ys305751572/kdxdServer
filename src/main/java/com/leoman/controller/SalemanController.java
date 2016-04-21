package com.leoman.controller;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.bean.Result;
import com.leoman.entity.Information;
import com.leoman.entity.KUser;
import com.leoman.entity.Product;
import com.leoman.entity.Saleman;
import com.leoman.service.ProductService;
import com.leoman.service.SalemanService;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/21.
 */
@RequestMapping(value = "/admin/saleman")
@Controller
public class SalemanController extends CommonController{

    @Autowired
    private ProductService productService;

    @Autowired
    private SalemanService service;

    @RequestMapping(value = "/index")
    public String index() {
        return "saleman/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public void list(HttpServletRequest request, HttpServletResponse response, Integer draw, Integer start, Integer length,String mobile,String name ) {
        try {
            int pageNum = getPageNum(start, length);
            Page<Saleman> page =  service.findPage(mobile,name,pageNum,length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    @RequestMapping(value = "/add")
    public String add(Long id,Model model) {
        if(id != null) {
            model.addAttribute("saleman",service.getById(id));
        }
        return "saleman/add";
    }

    @RequestMapping(value = "/save")
    public void save(HttpServletResponse response,Saleman saleman) {
        try{
            service.create(saleman);
            WebUtil.print(response, new Result(true).msg("操作成功!"));
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
    }

    /**
     * 删除
     *
     * @param response
     * @param id
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET )
    public String delete(HttpServletResponse response, Long id) {
        try {
            service.deleteById(id);
            return "saleman/list";
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
        return null;
    }

    @RequestMapping(value = "/proIndex")
    public String proIndex(Long salemanId,Model model) {
        model.addAttribute("salemanId",salemanId);
        return "saleman/pro-list";
    }

    /**
     * @param response
     * @param draw
     * @param start
     * @param length
     * @param pro
     */
    @RequestMapping(value = "/proList")
    @ResponseBody
    public void prolist(HttpServletResponse response,
                     Integer draw,
                     Integer start,
                     Integer length,
                     Product pro,
                     Integer type) {
        try {
            int pageNum = getPageNum(start, length);
            Page<Product> page = productService.findPage(pro, type, pageNum, length);
            List<Product> list = page.getContent();
            if (list != null && !list.isEmpty()) {
                for (Product product : list) {
                    Long counts = productService.findBuyCount(product.getId());
                    product.setBuyCount(counts);
                }
            }
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }


}
