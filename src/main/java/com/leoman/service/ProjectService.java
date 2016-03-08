package com.leoman.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.leoman.entity.Project;
import com.leoman.service.common.ICommonService;

public interface ProjectService extends ICommonService<Project>{

	public void saveProject(HttpServletRequest request, HttpServletResponse response, Project project,MultipartHttpServletRequest fileRequest);
}
