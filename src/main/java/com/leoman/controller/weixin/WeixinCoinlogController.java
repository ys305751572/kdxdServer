package com.leoman.controller.weixin;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.Constant;
import com.leoman.core.bean.Result;
import com.leoman.entity.Coinlog;
import com.leoman.entity.Information;
import com.leoman.entity.KUser;
import com.leoman.entity.Payrecord;
import com.leoman.service.CoinlogService;
import com.leoman.service.PayrecordService;
import com.leoman.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/18.
 */
@Controller
@RequestMapping(value = "weixin/coinlog")
public class WeixinCoinlogController extends CommonController {

    @Autowired
    private CoinlogService service;

    @Autowired
    private PayrecordService payrecordService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        KUser user = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        model.addAttribute("user", user);
        return "weixin/recharge-online";
    }

    @RequestMapping("/list")
    public String list(HttpServletRequest request, ModelMap model, Integer pageNum, Integer pageSize) {
        List<Coinlog> list = null;
        try {
            if (null == pageNum) {
                pageNum = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }

            KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
            Page<Coinlog> page = service.findPageByUserId(kUser.getId(), pageNum, pageSize);
            list = page.getContent();

            model.addAttribute("coinList", list);
            model.addAttribute("current", pageNum);
            model.addAttribute("totalPage", page.getTotalPages());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "weixin/coin-list";
    }

    /**
     * 生成充值记录
     *
     * @param request
     */
    @RequestMapping(value = "/getRechargeOrderNo", method = RequestMethod.POST)
    public void getRechargeOrderNo(HttpServletRequest request, HttpServletResponse response, String money) {
        KUser user = (KUser) request.getSession().getAttribute(Constant.SESSION_WEIXIN_USER);
        try {
            Payrecord payrecord = new Payrecord();
            payrecord.setUser(user);
            payrecord.setMoney(Double.parseDouble(money));
            payrecord.setSn("kdxg" + System.currentTimeMillis());

            payrecordService.create(payrecord);

            WebUtil.print(response, new Result(true).data(payrecord.getSn()));
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).msg("操作失败!"));
        }
    }
}
