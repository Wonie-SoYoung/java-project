package com.soyoung.photo.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soyoung.photo.entity.Comment;
import com.soyoung.photo.entity.Hotlabels;
import com.soyoung.photo.entity.Medal;
import com.soyoung.photo.entity.Production;
import com.soyoung.photo.mapper.*;
import com.soyoung.photo.service.ProductionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.soyoung.photo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>
 * 作品表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
@Service
public class ProductionServiceImpl extends ServiceImpl<ProductionMapper, Production> implements ProductionService {
    @Autowired
    private ProductionMapper productionMapper;
    @Autowired
    private MedalMapper medalMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UploadingUtil uploadingUtil;
    @Autowired
    private LikesMapper likesMapper;
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public FreePage gethomeProListPage(String label, Integer pageSize) {
        String key="homepro"+label;
        FreePage freePage=new FreePage();
        freePage.setPageSize(pageSize);
        List<Map<String,Object>> mapList=new ArrayList<>();
        //判断noSQL是否存在
        if(redisUtil.hasKey(key)){
            //存在，获取
            List<Object> list=redisUtil.lGet(key,0,-1);
            for (Object object:list){
                mapList.add(JSON.parseObject(object.toString()));
            }
        }else{
            //不存在，查询
            String engStr=Translate.gainresult(label,Dict.ENGLISHLANGUGE);
            if("".equals(engStr)) engStr=null;
            mapList=productionMapper.gethomeProList(label,engStr,pageSize);
            for (Map<String,Object> map:mapList) {
                redisUtil.lSet(key, JSON.toJSON(map),86400);
            }
        }
        freePage.setList(mapList);
        return freePage;
    }

    @Override
    public FreePage getTermProListPage(String label, String pName, Integer proType, Integer currPage, Integer pageSize) {
        //翻译
        String engStr=null;
        if(label!=null) Translate.gainresult(label,Dict.ENGLISHLANGUGE);
        String engpName=null;
        if(pName!=null) Translate.gainresult(pName,Dict.ENGLISHLANGUGE);
        //判断
        FreePage freePage=new FreePage();
        freePage.setCurrPageNo(currPage);
        freePage.setPageSize(pageSize);
        freePage.setList(FormatUtil.getMapDivision(productionMapper.getTermProList(label,engStr,pName,engpName,proType,currPage,pageSize)));
        return freePage;
    }

    @Override
    public Production getisByPro(Integer pid) {
        Production production=productionMapper.getisPro(pid);
        if(production!=null){
            production.setLabels(production.getLabel().split("\\+"));
            production.setProURLs(production.getProURL().split("\\+"));
            return production;
        }else{
            return null;
        }
    }

    @Override
    public FreePage getProByUserPage(Integer uid, Integer currPage,Integer pageSieze) {
        FreePage freePage=new FreePage();
        freePage.setCurrPageNo(currPage);
        freePage.setPageSize(pageSieze);
        List<Production> productionList=productionMapper.getuidByPro(uid,null,null);
        /**
         * 封装其他数据
         */
        Map<String,Object> map=new HashMap<>();
        //作品数量
        map.put("proNum",productionList.size());
        //总点赞数+勋章列表
        int likeNum=0;
        List<Medal> medalList=medalMapper.selectList(null);
        for (Production production:productionList){
            likeNum+=production.getLikesList().size();
            for (Medal medal:medalList){
                for (Medal medal1:production.getModelList()){
                    if(medal.getmId()==medal1.getmId()){
                        medal.setNumber(medal.getNumber()+1);
                    }
                }
            }
        }
        map.put("likeNum",likeNum);
        map.put("medalList",medalList);
        freePage.setRest(map);
        /**
         * 封装作品分页数据
         */
        productionList=productionMapper.getuidByPro(uid,currPage,pageSieze);
        List<List<Production>> lists=new ArrayList<>();
        boolean isNot=true;
        List<Production> list1=new ArrayList<>();
        List<Production> list2=new ArrayList<>();
        List<Production> list3=new ArrayList<>();
        Iterator<Production> iter=productionList.iterator();
        while (isNot){
            if(iter.hasNext()){
                list1.add(iter.next());
            }else{
                isNot=false;
            }
            if(iter.hasNext()){
                list2.add(iter.next());
            }else{
                isNot=false;
            }
            if(iter.hasNext()){
                list3.add(iter.next());
            }else{
                isNot=false;
            }
        }
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        freePage.setList(lists);
        return freePage;
    }

    @Override
    public Integer insertPro(Production production) {
        try{
            if(productionMapper.insert(production)<1){
                production.setId(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return production.getId();
    }

    @Override
    public List<Production> getIdIsLikeByPros(String[] labels,Integer pid) {
        List<String> stringList=null;
        if(labels!=null && labels.length<0){
            stringList=new ArrayList<>();
            for (String label:labels){
                stringList.add(label);
                stringList.add(Translate.gainresult(label,Dict.CHINESELANGUAGE));
            }
        }
        return productionMapper.getIdIsLikeByPros(stringList,pid);
    }

    @Override
    public FreePage getSeekPro(String value, Integer type, Integer currPage, Integer pageSize) {
        String finevalue=Translate.gainresult(value,Dict.ENGLISHLANGUGE);
        FreePage freePage=new FreePage();
        freePage.setCurrPageNo(currPage);
        freePage.setPageSize(pageSize);
        try{
            freePage.setList(FormatUtil.getMapDivision(productionMapper.getHuntPro(value,finevalue,type,currPage,pageSize)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return freePage;
    }

    @Override
    public Boolean delProduction(Integer pid) {
        Boolean isNot=true;
        /**
         * 删除作品相关内容
         */
        try{
            //获取作品
            Production production=productionMapper.selectById(pid);
            //异步删除oss对应文件
            uploadingUtil.delUpload(production.getProURL());
            uploadingUtil.delUpload(production.getHiapkURL());
            //删除评论
            commentMapper.delete(
                    new EntityWrapper<Comment>()
                            .eq("pid",pid)
            );
            //删除作品下的勋章
            medalMapper.delProAllMedal(pid);
            //删除作品下的点赞
            likesMapper.delProByLikes(pid);
            //删除作品下的收藏
            collectMapper.delProByCollect(pid);
            //删除作品
            productionMapper.deleteById(pid);
        }catch (Exception e){
            isNot=false;
            e.printStackTrace();
        }
        return isNot;
    }

    @Override
    public Boolean updateProduction(Production production) {
        Boolean isNot=false;
        production.setUpdateTime(new Date());
        if(StringUtils.isEmpty(production.getDuration())) production.setDuration(null);
        if(productionMapper.updateAllColumnById(production)>0){
            isNot=true;
        }
        return isNot;
    }

    @Override
    public FreePage getFocusProductionPage(List<Integer> uidList, Integer currPage) {
        FreePage freePage=new FreePage();
        freePage.setCurrPageNo(currPage);
        freePage.setPageSize(Dict.HOMEFOCUSPAGESIZE);
        if(uidList!=null && uidList.size()>0){
            freePage.setList(productionMapper.getFocusProductionList(uidList,currPage,freePage.getPageSize()));
        }else{
            freePage.setList(null);
        }
        return freePage;
    }

    @Override
    public FreePage getCollectProductionPage(Integer uid, Integer currPage) {
        //判断
        FreePage freePage=new FreePage();
        freePage.setCurrPageNo(currPage);
        freePage.setPageSize(Dict.PROTERMSIZE);
        freePage.setList(FormatUtil.getMapDivision(productionMapper.getCollectProductionList(uid,currPage,freePage.getPageSize())));
        return freePage;
    }

    @Override
    @Async
    public Integer updateviewNum(Integer pid) {
        return productionMapper.updateNumBer(pid);
    }

}
