package com.leoman.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.common.factory.DataTableFactory;
import com.leoman.controller.common.CommonController;
import com.leoman.core.bean.Result;
import com.leoman.entity.Project;
import com.leoman.service.ProjectService;
import com.leoman.utils.JsonUtil;
import com.leoman.utils.WebUtil;

@RequestMapping("admin/project")
@Controller
public class ProjectController extends CommonController {

	@Autowired
	private ProjectService service;

	@RequestMapping(value = "/index")
	public String index() {
		return "project-list";
	}

	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, HttpServletResponse response, Integer draw, Integer start,
			Integer length) {

		try {
			Integer pageNum = getPageNum(start, length);
			Page<Project> page = service.find(pageNum, length);
			Map<String, Object> result = DataTableFactory.fitting(draw, page);
			WebUtil.print(response, result);
		} catch (Exception e) {
			e.printStackTrace();
			WebUtil.print(response, emptyData);
			GeneralExceptionHandler.log(e);
		}
	}

	@RequestMapping(value = "/save")
	public void save(HttpServletRequest request, HttpServletResponse response, Project project,
			MultipartHttpServletRequest fileRequest) {

		try {
			service.saveProject(request, response, project, fileRequest);
			WebUtil.print(response, new Result(true).msg("商品操作成功!"));
		} catch (DataIntegrityViolationException ex) {
			GeneralExceptionHandler.log("商品编号已存在", ex);
			WebUtil.print(response, new Result(false).msg("商品编号已存在!"));
		} catch (Exception e) {
			GeneralExceptionHandler.log("商品添加失败", e);
			WebUtil.print(response, new Result(false).msg("商品操作失败!"));
		}
	}

	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, String ids) {
		try {
			int[] arrayId = JsonUtil.json2Obj(ids, int[].class);
			service.deleteAll(arrayId);
			WebUtil.print(response, new Result(true).msg("删除成功！"));
		} catch (Exception e) {
			GeneralExceptionHandler.log(e);
			WebUtil.print(response, new Result(false).msg("删除失败！"));
		}
	}
}
