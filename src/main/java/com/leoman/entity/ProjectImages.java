package com.leoman.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="project_image")
public class ProjectImages extends BaseEntity{

	private static final long serialVersionUID = 2416665917474611491L;

	@Column(name="image_url")
	private String url;
	
	@Column(name="project_id")
	private Integer projectId;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
}
