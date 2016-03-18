package com.leoman.controller.weixin;

import com.leoman.entity.Activity;
import com.leoman.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
@Controller
@RequestMapping(value = "weixin/activity")
public class WeixinActivityController {

    @Autowired
    private ActivityService service;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        List<Activity> list = service.findAll();
        if(list != null && !list.isEmpty()) {
            model.addAttribute("act",list.get(0));
        }
        return null;
    }
}
