package com.leoman.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.leoman.core.Constant;
import com.leoman.dao.ProjectImageDao;
import com.leoman.entity.ProjectImages;
import com.leoman.service.ProjectImagesService;

@Service
public class ProjectImageServerImpl implements ProjectImagesService{

	@Autowired
	private ProjectImageDao dao;
	
	@Override
	public List<ProjectImages> findAll() {
		return dao.findAll();
	}

	@Override
	public Page<ProjectImages> find(int pageNum, int pageSize) {
		return dao.findAll(new PageRequest(pageNum, pageSize, Sort.Direction.DESC, "id"));
	}

	@Override
	public Page<ProjectImages> find(int pageNum) {
		return find(pageNum,Constant.PAGE_DEF_SZIE);
	}

	@Override
	public ProjectImages getById(int id) {
		return dao.findOne(id);
	}

	@Override
	public ProjectImages deleteById(int id) {
		ProjectImages image = dao.findOne(id);
		dao.delete(image);
		return image; 
	}

	@Override
	public ProjectImages create(ProjectImages t) {
		return dao.save(t);
	}

	@Override
	public ProjectImages update(ProjectImages t) {
		return dao.save(t);
	}

	@Override
	public void deleteAll(int[] ids) {
		for (int i : ids) {
			deleteById(i);
		}
	}

	@Override
	public void saveImages(List<ProjectImages> list) {
		for (ProjectImages projectImages : list) {
			create(projectImages);
		}
	}
}
