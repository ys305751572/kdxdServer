package com.leoman.service.impl;

import com.leoman.common.exception.GeneralExceptionHandler;
import com.leoman.core.Configue;
import com.leoman.entity.Image;
import com.leoman.entity.vo.FileBo;
import com.leoman.service.ImageService;
import com.leoman.service.UploadImageService;
import com.leoman.service.abs.AbstractUploadService;
import com.leoman.utils.FileUtil;
import com.leoman.utils.ImageUtil;
import com.leoman.utils.JsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangbin on 2014/12/7.
 */
@Service
@Transactional(readOnly = true)
public class UploadImageServiceImpl extends AbstractUploadService implements UploadImageService {

    @Autowired
    private ImageService imageService;

    @Override
    public FileBo saveImage(MultipartFile file) {
        if (!FileUtil.isImage(file.getOriginalFilename())) {
            GeneralExceptionHandler.handle("文件格式不正确，请上传图片!");
        }
        FileBo result = null;
        try {
            result = super.save(file);
        } catch (IOException e) {
            GeneralExceptionHandler.handle("上传失败,服务器繁忙!");
        }
        return result;
    }

    @Override
    public Image uploadImage(MultipartFile file, String... thumbSizes) {
        try {
            FileBo fileBo = saveImage(file);
            Image image = new Image();
            image.setPath(fileBo.getPath());

            //处理大于500k的jpg图片
            Map<String,Long> imgInfo = ImageUtil.getImgInfo(fileBo.getFile());
            Long imgSize = imgInfo.get("size");
            if(imgSize>512000&&fileBo.getExt().equals(".jpg")){
                String destImage = fileBo.getForeName() + "_" + "compact" + fileBo.getExt();
                ImageUtil.compactImage(fileBo.getFile(), Configue.getUploadPath(), destImage);
                image.setPath(destImage);
                imgInfo = ImageUtil.getImgInfo(Configue.getUploadPath()+destImage);
            }

            //存储宽和高
            Map<String,Long> attrMap = new HashMap<String, Long>();
            attrMap.put("width",imgInfo.get("width"));
            attrMap.put("height",imgInfo.get("height"));
            image.setAttribute(JsonUtil.obj2Json(attrMap));

            if (thumbSizes != null && thumbSizes.length > 0) {
                Map<String, String> thumb = new HashMap<String, String>();
                for (String size : thumbSizes) {
                    String regex = "(\\d+)x(\\d+)";
                    Pattern p = Pattern.compile(regex, Pattern.DOTALL);
                    Matcher m = p.matcher(size);
                    String destImage = fileBo.getForeName() + "_" + size + fileBo.getExt();
                    if (m.find()) {
                        int width = Integer.parseInt(m.group(1));// 宽
                        int height = Integer.parseInt(m.group(2));// 高
                        ImageUtil.thumbImage(fileBo.getFile(),Configue.getUploadPath(), destImage, width, height);
                    }
                    thumb.put(size, destImage);
                }
                image.setThumbs(JsonUtil.obj2Json(thumb));
            }
            return image;
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
        }
        return null;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            FileBo fileBo = super.save(file);
            if(fileBo != null){
                return fileBo.getPath();
            }
            return null;
        } catch (Exception e) {
            GeneralExceptionHandler.log(e);
        }
        return null;
    }

    @Override
    @Transactional
    public List<Image> uploadImages(MultipartFile[] files) {
        List<Image> images = new ArrayList<Image>();
        for(MultipartFile file : files){
            Image image = uploadImage(file);
            imageService.create(image);
            images.add(image);
        }
        return images;
    }


}
