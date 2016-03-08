package com.leoman.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leoman.entity.Project;

public interface ProjectDao extends JpaRepository<Project, Integer>{

}
