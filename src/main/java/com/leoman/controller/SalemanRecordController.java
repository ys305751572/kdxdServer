package com.leoman.controller;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.entity.SalemanRecord;
import com.leoman.service.SalamanRecordService;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/21.
 */
@RequestMapping(value = "/admin/salemanrecord")
@Controller
public class SalemanRecordController extends CommonController {

    @Autowired
    private SalamanRecordService service;

    @RequestMapping(value = "/index")
    public String index() {
        return "salemanrecord/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public void list(HttpServletRequest request, HttpServletResponse response, Integer draw, Integer start, Integer length, String name) {
        try {
            int pageNum = getPageNum(start, length);
            Page<SalemanRecord> page = service.findPage(name, pageNum, length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            e.printStackTrace();
            WebUtil.print(response, emptyData);
        }
    }
}

