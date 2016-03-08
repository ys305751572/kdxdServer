package com.leoman.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.leoman.core.Constant;
import com.leoman.dao.ProjectDao;
import com.leoman.entity.Attach;
import com.leoman.entity.Project;
import com.leoman.entity.ProjectImages;
import com.leoman.service.ProjectImagesService;
import com.leoman.service.ProjectService;
import com.leoman.utils.CommonUtils;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectDao dao;
	
	@Autowired
	private ProjectImagesService imageService;
	
	@Override
	public List<Project> findAll() {
		return dao.findAll();
	}

	@Override
	public Page<Project> find(int pageNum, int pageSize) {
		return dao.findAll(new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "id"));
	}

	@Override
	public Page<Project> find(int pageNum) {
		return find(pageNum, Constant.PAGE_DEF_SZIE);
	}

	@Override
	public Project getById(int id) {
		return dao.findOne(id);
	}

	@Override
	public Project deleteById(int id) {
		Project project = dao.findOne(id);
		dao.delete(project);
		return project;
	}

	@Override
	public Project create(Project t) {
		return dao.save(t);
	}

	@Override
	public Project update(Project t) {
		return dao.save(t);
	}

	@Override
	public void deleteAll(int[] ids) {
		for (int i : ids) {
			deleteById(i);
		}
	}

	@Override
	public void saveProject(HttpServletRequest request, HttpServletResponse response, Project project,
			MultipartHttpServletRequest fileRequest) {
		
		create(project);
		saveProductImage(project, fileRequest, fileRequest);
		update(project);
	}
	
	/**
	 * 
     * @Title: saveProductImage
     * @Description: 保存图片
     * @param @param fileRequest    参数
     * @return void    返回类型
     * @throws
	 */
	private void saveProductImage(Project project,MultipartHttpServletRequest fileRequest,HttpServletRequest request) {
		Iterator<String> fileNames = fileRequest.getFileNames();
		String webRoot = request.getServletContext().getRealPath("");
		
		List<ProjectImages> imageList = new ArrayList<ProjectImages>();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile file = fileRequest.getFile(fileName);
			Attach attach = CommonUtils.uploadAttach(file, webRoot,"/upload/pi", null);
			if(attach != null) {
				if("indexImg".equals(fileName)) {
					project.setImageIndexUrl(attach.getBak1());
				}else if("bgImg".equals(fileName)){
					project.setImageBgUrl(attach.getBak1());
				}else {
					ProjectImages image = new ProjectImages();
					image.setUrl(attach.getBak1());
					image.setProjectId(project.getId());
					imageList.add(image);
				}
			}
		}
		imageService.saveImages(imageList);
	}
}
