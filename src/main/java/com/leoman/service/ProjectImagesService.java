package com.leoman.service;

import java.util.List;

import com.leoman.entity.ProjectImages;
import com.leoman.service.common.ICommonService;

public interface ProjectImagesService extends ICommonService<ProjectImages>{

	
	public void saveImages(List<ProjectImages> list);
}
