package com.leoman.controller.weixin;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.entity.Information;
import com.leoman.service.InfomationService;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
        return "weixin/info-list-test";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void list(HttpServletResponse response, Integer draw, Integer start, Integer length) {

        // TODO 测试页面
        // TODO 测试通过后需要改为微信菜单
        try {
            int pageNum = getPageNum(start, length);
            Information info = new Information();
            Page<Information> page = service.findPage(info, pageNum, length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    /**
     * 资讯详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Long id, Model model) {
        Information info = service.getById(id);
        info.setContent(info.getContent().replace("&lt","<").replace("&gt",">"));
        model.addAttribute("info",info);
        return "weixin/info-detail";
    }
}