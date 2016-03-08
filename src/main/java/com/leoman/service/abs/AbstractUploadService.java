package com.leoman.service.abs;

import com.leoman.core.Configue;
import com.leoman.entity.vo.FileBo;
import com.leoman.utils.FileUtil;
import com.leoman.utils.UploadUtil;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wangbin on 2014/12/6.
 */
public abstract class AbstractUploadService {




    public String getDesFileName(String origFileName){
        String ext =  FileUtil.getFileExt(origFileName);
        String fileName = String.valueOf(System.currentTimeMillis());
        String uploadPath = UploadUtil.getImagesUpladPath();
        String desFilePathName = uploadPath+fileName+ext;
        return desFilePathName;
    }

    public FileBo save(MultipartFile file) throws IOException{
        FileBo fileBo =  save(file,String.valueOf(System.currentTimeMillis()));
        return fileBo;
    }


    public FileBo save(MultipartFile file,String fileName) throws IOException{

        FileBo fileBo = new FileBo();
        InputStream inputStream = file.getInputStream();
        String origFileName = file.getOriginalFilename(); //原始名称,如aa.jpg
        String ext =  FileUtil.getFileExt(origFileName); //后缀，如jpg
        String uploadPath = UploadUtil.getImagesUpladPath(); //生成日期目录 image/2014/7/21/
        String foreName = uploadPath+fileName;   //文件名称 image/2014/7/21/221144144554
        String desFilePathName = uploadPath+fileName+ext;//完整文件名称 image/2014/7/21/221144144554.jpg
        File theFile = new File(Configue.getUploadPath(),desFilePathName); //生成的文件对象
        fileBo.setName(fileName);
        fileBo.setForeName(foreName);
        fileBo.setExt(ext);
        fileBo.setPath(desFilePathName);
        fileBo.setFile(theFile);
        FileUtils.copyInputStreamToFile(inputStream,theFile);

        return fileBo;
    }




}
