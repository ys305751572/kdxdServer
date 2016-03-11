package com.leoman.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leoman.entity.Project;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProjectDao extends JpaRepository<Project, Integer>,JpaSpecificationExecutor<Project>{

}
