package com.soyoung.photo.freeanglequeen.util;

import com.soyoung.photo.freeanglequeen.entity.User;
import com.soyoung.photo.freeanglequeen.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class Scheduler {
    @Resource
    public UserService userService;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    int countNum = 10;
    @Scheduled(fixedRate = 6000)//24h==86400s
    public void testTasks(){
        try {
            List<User> userList = userService.selUserSealList();
            for (User user:userList
            ) {
                Integer id = user.getId();
                System.out.println("id------>"+id);
                Integer seal = user.getSeal();
                System.out.println("seal------>"+seal);
                if (seal>0){
                    Integer i = userService.updUserId(seal-1,id);
                    System.out.println("定时任务："+sdf.format(new Date()));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
