package com.leoman.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 项目
 * 
 * @author yesong
 *
 */
@Entity
@Table(name="project")
public class Project extends BaseEntity {

	private static final long serialVersionUID = 5766506701907803577L;

	@Column(name="project_name")
	private String name;
	@Column(name="project_detail")
	private String detail;

	@Column(name="project_image_index")
	private String imageIndexUrl;
	
	@Column(name="product_image_bg")
	private String imageBgUrl;

	@OneToMany
	@JoinColumn(name="project_id")
	private List<ProjectImages> images;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImageIndexUrl() {
		return imageIndexUrl;
	}

	public void setImageIndexUrl(String imageIndexUrl) {
		this.imageIndexUrl = imageIndexUrl;
	}

	public String getImageBgUrl() {
		return imageBgUrl;
	}

	public void setImageBgUrl(String imageBgUrl) {
		this.imageBgUrl = imageBgUrl;
	}

	public List<ProjectImages> getImages() {
		return images;
	}

	public void setImages(List<ProjectImages> images) {
		this.images = images;
	}
}
