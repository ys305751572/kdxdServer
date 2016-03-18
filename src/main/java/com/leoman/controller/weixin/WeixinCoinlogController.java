package com.leoman.controller.weixin;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.entity.Coinlog;
import com.leoman.entity.Information;
import com.leoman.service.CoinlogService;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/18.
 */
@Controller
@RequestMapping(value = "weixin/coinlog")
public class WeixinCoinlogController extends CommonController{

    @Autowired
    private CoinlogService service;

    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public String index() {
        return "";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void list(HttpServletResponse response, Integer draw, Integer start, Integer length,Long userId) {

        try {
            int pageNum = getPageNum(start, length);
            Page<Coinlog> page = service.findPageByUserId(userId,pageNum,length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }
}
