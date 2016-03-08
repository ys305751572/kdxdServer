package com.leoman.service;


import com.leoman.entity.Image;
import com.leoman.entity.vo.FileBo;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by wangbin on 2014/12/7.
 */
public interface UploadImageService {

    public FileBo saveImage(MultipartFile file);

    public Image uploadImage(MultipartFile file, String... thumbSizes);

    public String uploadFile(MultipartFile file);

//    public Image uploadImage(MultipartFile file);


    public List<Image> uploadImages(MultipartFile[] file);


}
