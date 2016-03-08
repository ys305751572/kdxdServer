package com.leoman.controller.common;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.core.Configue;
import com.leoman.core.bean.Result;
import com.leoman.entity.Image;
import com.leoman.service.ImageService;
import com.leoman.service.UploadImageService;
import com.leoman.utils.WebUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangbin on 2014/12/6.
 */
@Controller
@RequestMapping(value = "common/file")
public class FileController extends CommonController{


    @Autowired
    private UploadImageService uploadImageService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "index")
    public void index(HttpServletRequest request,
                      HttpServletResponse response){
        WebUtil.print(response, new Result(true));
    }


    @RequestMapping(value = "/save/image")
    public void saveImage(HttpServletRequest request,
                          HttpServletResponse response,
                          String thumbSizes,
                          @RequestParam(required=false) MultipartFile file){
        Image image = null;
        try {
            if(StringUtils.isBlank(thumbSizes)){
                image = uploadImageService.uploadImage(file);
            }else{
                image = uploadImageService.uploadImage(file,thumbSizes.split(","));
            }
            imageService.create(image);
            image.setPath(Configue.getUploadUrl()+image.getPath());
            WebUtil.print(response, new Result(true).data(image).msg("上传图片成功!"));
        }catch (Exception e){
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, new Result(false).msg(e.getMessage()));
        }
    }



    @RequestMapping(value = "/save/images")
    public void saveImage(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestParam(required = false) MultipartFile[] files){

        try {
            List<Image> imageList =uploadImageService.uploadImages(files);
            for(Image image : imageList){
                image.setPath(Configue.getUploadUrl()+image.getPath());
            }
            WebUtil.print(response, new Result(true).data(imageList).msg("上传图片成功!"));
        }catch (Exception e){
            GeneralExceptionHandler.log(e);
            WebUtil.print(response, new Result(false).msg(e.getMessage()));
        }
    }



}
