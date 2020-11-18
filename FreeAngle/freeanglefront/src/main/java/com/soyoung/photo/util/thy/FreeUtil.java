package com.soyoung.photo.util.thy;

import com.soyoung.photo.entity.Attention;
import com.soyoung.photo.entity.Collect;
import com.soyoung.photo.entity.Likes;
import com.soyoung.photo.entity.Production;

import java.util.List;

public class FreeUtil {

    public static Integer uploadNums;

    //查询粉丝集合里是否包含Id
    public Boolean getListIsId(List<Attention> list, Integer id){
        System.out.println(list.toString());
        System.out.println(id);
        boolean isNot=false;
        if(list==null || id==null){
            isNot=false;
        }else{
            for (Attention attention:list){
                if(attention.getUid()==id){
                    isNot=true;
                }
            }
        }
        return isNot;
    }

    //查询关注集合里是否包含Id
    public Boolean getAttListIsId(List<Attention> list, Integer id){
        System.out.println(list.toString());
        System.out.println(id);
        boolean isNot=false;
        if(list==null || id==null){
            isNot=false;
        }else{
            for (Attention attention:list){
                if(attention.getByuid()==id){
                    isNot=true;
                }
            }
        }
        return isNot;
    }

    //计算作品点赞数
    public Integer getLikeNum(List<Production> productions){
        Integer number=0;
        for (Production production:productions){
            number+=production.getLikesList().size();
        }
        return number;
    }

    //查询点赞是否包含当前用户
    public Boolean getUidIsLikes(List<Likes> list, Integer id){
        boolean isNot=false;
        if(list==null || id==null){
            isNot=false;
        }else{
            for (Likes likes:list){
                if(likes.getUid()==id){
                    isNot=true;
                }
            }
        }
        return isNot;
    }

    //查询收藏是否包含当前用户
    public Boolean getUidIsCollect(List<Collect> list, Integer id){
        boolean isNot=false;
        if(list==null || id==null){
            isNot=false;
        }else{
            for (Collect collect:list){
                if(collect.getUid()==id){
                    isNot=true;
                }
            }
        }
        return isNot;
    }
}
