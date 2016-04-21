package com.leoman.controller;

import com.leoman.controller.common.CommonController;
import com.leoman.entity.Activity;
import com.leoman.entity.Image;
import com.leoman.service.ActivityService;
import com.leoman.utils.ConfigUtil;
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
        Image image = activity.getImage();
        image.setPath(ConfigUtil.getString("upload.url") + image.getPath());
        activity.setContent(activity.getContent().replaceAll("&lt","<").replaceAll("&gt",">"));
        model.addAttribute("act",activity);
        return "act/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Activity activity,Integer imageId,Model model) {
        List<Activity> list = service.findAll();
        Activity _activity = null;
        if(list != null && !list.isEmpty()) {
            _activity = list.get(0);
            _activity.setTitle(activity.getTitle());
            _activity.setContent(activity.getContent());
        }
        else {
            _activity = new Activity();
            _activity.setTitle(activity.getTitle());
            _activity.setContent(activity.getContent());

        }
        Image image = new Image();
        image.setId(imageId);
        _activity.setImage(image);
        _activity = service.create(_activity);
        if(_activity.getContent() != null)
        _activity.setContent(_activity.getContent().replaceAll("&lt","<").replaceAll("&gt",">"));
        model.addAttribute("act",_activity);
        return "act/detail";
    }
}