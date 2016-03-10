package com.leoman.controller;

import com.leoman.controller.common.CommonController;
import com.leoman.entity.Activity;
import com.leoman.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */

@Controller
@RequestMapping(value = "admin/act")
public class ActivityController extends CommonController{

    @Autowired
    private ActivityService service;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model) {
        List<Activity> list = service.findAll();
        Activity activity = null;
        if(list != null && !list.isEmpty()) {
            activity = list.get(0);
        }
        else {
            activity = new Activity();
        }
        model.addAttribute("act",activity);
        return "act/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Activity activity,Model model) {
        List<Activity> list = service.findAll();
        Activity _activity = null;
        if(list != null && !list.isEmpty()) {
            _activity = list.get(0);
            _activity.setContent(activity.getContent());
        }
        else {
            _activity = new Activity();
            _activity.setContent(activity.getContent());

        }
        _activity = service.create(_activity);
        model.addAttribute("act",_activity);
        return "act/detail";
    }
}
