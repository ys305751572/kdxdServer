package com.leoman.controller;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.bean.Result;
import com.leoman.entity.Member;
import com.leoman.service.MemberService;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.Md5Util;
import com.leoman.utils.WebUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by wangbin on 2015/8/10.
 */
@RequestMapping(value = "admin/member")
@Controller
public class MemberController extends CommonController {


    @Autowired
    private MemberService memberService;


    @RequestMapping(value = "/index")
    public String index(){
        return "商家账户管理";
    }


    @RequestMapping(value = "/list")
    public void list(HttpServletRequest request,
                     HttpServletResponse response,
                     Integer draw,
                     Integer start,
                     Integer length,
                     ModelMap model) {
            try {
                int pageNum = getPageNum(start, length);
                Page<Member> page = memberService.findByAdmin(pageNum, length);
                Map<String, Object> result = DataTableFactory.fitting(draw, page);
                WebUtil.print(response, result);
            } catch (Exception e) {
                GeneralExceptionHandler.log(e);
                WebUtil.print(response, emptyData);
            }
    }

    @RequestMapping(value = "/change/password")
    public void changePassword(HttpServletRequest request,
                               HttpServletResponse response,
                               Integer memberId,
                               String password){
        try {
            Member member = memberService.getById(memberId);
            member.setPassword(Md5Util.md5(password));
            memberService.update(member);
            WebUtil.print(response, new Result(true).msg("密码修改成功!"));
        }catch (Exception e){
            GeneralExceptionHandler.log("密码修改失败", e);
            WebUtil.print(response, new Result(false).msg("密码修改失败!"));
        }
    }

    @RequestMapping(value = "/delete")
    public void delete(HttpServletRequest request,
                       HttpServletResponse response,
                       String  ids){
        try {
            int[] arrayId = JsonUtil.json2Obj(ids, int[].class);
            memberService.deleteAll(arrayId);
            WebUtil.print(response, new Result(true).msg("删除成功！"));
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, new Result(false).msg("删除失败！"));
        }
    }




}
