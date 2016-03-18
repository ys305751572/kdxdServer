package com.leoman.controller.weixin;

import com.leoman.controller.common.CommonController;
import com.leoman.entity.Information;
import com.leoman.service.InfomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2016/3/18.
 */
@RequestMapping(value = "weixin/information")
@Controller
public class WeixinInformationController extends CommonController {

    @Autowired
    private InfomationService service;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index() {
        return null;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void list() {

    }

    public String detail(Long id, Model model) {
        Information info = service.getById(id);
        model.addAttribute("info",info);
        return null;
    }
}
