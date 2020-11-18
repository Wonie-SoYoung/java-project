package com.soyoung.photo.freeanglequeen.util.file;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.File;

@Component
public class UploadingUtil {

    @Async
    //异步上传文件
    public void uploadFile(File file, String objectName, HttpSession session,String key){
        try{
            OSSUtil.uploadByFilewhen(OSSUtil.getOSSClient(),file,OSSUtil.BUCKETNAME,objectName,session,key);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Async
    //异步删除文件
    public void delUpload(String key){
        try{
            OSSUtil.deleteFile(OSSUtil.getOSSClient(),OSSUtil.BUCKETNAME,key);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
