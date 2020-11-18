package com.soyoung.photo.util;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 视频解析器工具包 用来获取视频长度及封面
 */
public class VideoResolverUtil {

    /**
     * 获取视频时长，单位为秒
     *
     * @param video 源视频文件
     * @return 时长（s）
     */
    public static String getVideoDuration(File video) {
        long duration = 0L;
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        try {
            ff.start();
            duration = ff.getLengthInTime() / (1000 * 1000);
            ff.stop();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
        return getTimeByMinutes(new Long(duration).intValue());
    }

    /**
     * 截取视频获得指定帧的图片
     *
     * @param video   源视频文件
     * @param picPath 截图存放路径
     */
    public static String getVideoPic(File video, String picPath) {
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        String objectName="";
        try {
            ff.start();
            // 截取中间帧图片(具体依实际情况而定)
            int i = 0;
            int length = ff.getLengthInFrames();
            System.out.println(length);
            int middleFrame = 1;
            System.out.println(middleFrame);
            Frame frame = null;
            while (i < length) {
                frame = ff.grabFrame();
                if ((i > middleFrame) && (frame.image != null)) {
                    break;
                }
                i++;
            }

            // 截取的帧图片
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage srcImage = converter.getBufferedImage(frame);
            int srcImageWidth = srcImage.getWidth();
            int srcImageHeight = srcImage.getHeight();

            // 对截图进行等比例缩放(缩略图)
            int width = 1200;
            int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
            BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

            InputStream inputStream=bufferedImageToInputStream(thumbnailImage);
            UploadingUtil.addUploadIo(inputStream,Dict.OBJECTFILEVIDEOCHANGE+picPath);
            objectName=Dict.OBJECTFILEVIDEOCHANGE+picPath;
            ff.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objectName;
    }

    /**
     * 将BufferedImage转换为InputStream
     * @param image
     * @return
     */
    public static InputStream bufferedImageToInputStream(BufferedImage image){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", os);
            InputStream input = new ByteArrayInputStream(os.toByteArray());
            return input;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将秒数转换为基于00:00的时间
     * 如541=9*60+1，表示09:01
     * @param minutes
     * @return
     */
    public static String getTimeByMinutes(int minutes){
        //处理小时
        int hour = minutes / 60;
        String hourTime = "";
        if(hour >= 0 && hour < 10){
            hourTime = "0" + hour;// 1 --> 01
        }else if(hour >= 10 && hour < 24){
            hourTime = "" + hour;
        }else if(hour >= 24){
            hourTime = "0" + (hour-24);
        }
        //处理分钟
        int min = minutes % 60;
        String minTime = "";
        if(min >= 0 && min < 10){
            minTime = "0" + min;// 1 --> 01
        }else{
            minTime = "" + min;
        }
        return hourTime + ":" + minTime;
    }
}
