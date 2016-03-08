package com.leoman.utils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.leoman.entity.BaseEntity;

public class EntityListener {

	@PrePersist
	public void prePersist(BaseEntity entity) {
		entity.setCreateDate(System.currentTimeMillis());
	}
	
	@PreUpdate
	public void preUpdate(BaseEntity entity) {
		entity.setUpdateDate(System.currentTimeMillis());
	}
}
