package com.leoman.controller;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.bean.Result;
import com.leoman.entity.Image;
import com.leoman.entity.Information;
import com.leoman.entity.Message;
import com.leoman.entity.vo.MessageVo;
import com.leoman.service.MessageService;
import com.leoman.utils.DateUtils;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/10.
 */
@Controller
@RequestMapping(value = "admin/msg")
public class MessageController extends CommonController{

    @Autowired
    private MessageService service;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "msg/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public void list(HttpServletResponse response, Integer draw, Integer start, Integer length, Message msg,Integer type) {
        try {
            int pageNum = getPageNum(start, length);
            Page<Message> page = service.findPage(msg,type, pageNum, length);
            Map<String, Object> result = DataTableFactory.fitting(draw, page);
            WebUtil.print(response, result);
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, emptyData);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "msg/add";
    }

    /**
     * 保存
     *
     * @param response
     * @param msg
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletResponse response, Message msg,Integer imageId,String sendTimeQ) {
        try {
            Image image = new Image();
            image.setId(imageId);
            msg.setImage(image);
            msg.setIsList(0);
            long sendTime = DateUtils.stringToLong(sendTimeQ,"yyyy-MM-dd HH:mm");
            msg.setSendDate(sendTime);
            service.create(msg);
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
            return "msg/list";
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
        return null;
    }

    /**
     * 详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Long id,Model model) {
        MessageVo vo = null;
        try {
            Message message = service.getById(id);
            vo = new MessageVo();
            vo.setId(message.getId());
            vo.setTitle(message.getTitle());
            vo.setImage(message.getImage());
            try {
                vo.setSendDate(DateUtils.longToString(message.getSendDate(),"yyyy-MM-dd HH:mm:ss"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(message.getContent() != null) {
                message.setContent(message.getContent().replace("&lt","<").replace("&gt",">"));
                vo.setContent(message.getContent().replace("&lt","<").replace("&gt",">"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("message",vo);
        return "msg/detail";
    }
}