package com.soyoung.photo.freeanglequeen.service.impl;

import com.soyoung.photo.freeanglequeen.dao.DictionaryMapper;
import com.soyoung.photo.freeanglequeen.dao.ProductionMapper;
import com.soyoung.photo.freeanglequeen.dao.ReportMapper;
import com.soyoung.photo.freeanglequeen.entity.Dictionary;
import com.soyoung.photo.freeanglequeen.entity.Production;
import com.soyoung.photo.freeanglequeen.entity.Report;
import com.soyoung.photo.freeanglequeen.service.DictionaryService;
import com.soyoung.photo.freeanglequeen.service.FacilityService;
import com.soyoung.photo.freeanglequeen.service.ProductionService;
import com.soyoung.photo.freeanglequeen.util.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.soyoung.photo.freeanglequeen.util.Constants.COPYRIGHT;
import static com.soyoung.photo.freeanglequeen.util.Constants.PRIVACY;
import static com.soyoung.photo.freeanglequeen.util.Constants.PRODUCTION_TYPE;

@Service
public class ProductionServiceImpl implements ProductionService {

    @Resource
    public ProductionMapper productionMapper;
    @Resource
    public DictionaryService dictionaryService;
    @Resource
    public FacilityService facilityService;


    /*
    * 范宏伦
    * */
    @Override
    public Production selProductionId(Integer id) {
        Production production = productionMapper.selProductionId(id);
        Dictionary dictionary = new Dictionary();
        Integer pt = production.getProtypeId();
        System.out.println("aaa:"+pt);
        Integer c = production.getCopyrightId();
        System.out.println("bbb:"+c);
        Integer p = production.getPrivacyId();
        System.out.println("ccc:"+p);
        List<Dictionary> productionType = dictionaryService.selDictionaryListTypeCode(PRODUCTION_TYPE);
        //作品类型
        for (Dictionary dic: productionType
             ) {
            System.out.println("a:"+dic.getId());
            if (pt == dic.getId()){
                dictionary = dic;
            }
            //System.out.println("PRODUCTION_TYPE:"+dic);
        }
        production.setDictionaryProtype(dictionary);
        //版权拥有
        for (Dictionary dic:dictionaryService.selDictionaryListTypeCode(COPYRIGHT)
             ) {
            System.out.println("b:"+dic.getId());
            if (c == dic.getId()){
                dictionary = dic;
            }
            //System.out.println("COPYRIGHT"+dic);
        }
        production.setDictionaryCopyright(dictionary);
        //个人拥有
        for (Dictionary dic:dictionaryService.selDictionaryListTypeCode(PRIVACY)
             ) {
            System.out.println("c:"+dic.getId());
            if (p == dic.getId()){
                dictionary = dic;
            }
        }
        production.setDictionaryPrivacy(dictionary);
        production.setFacility(facilityService.selFacilityID(production.getFacilityId()));

        return production;
    }

    @Override
    public Integer delProductionId(Integer id) {
        return productionMapper.delProductionId(id);
    }


    /*
    * 陈海嘉
    * */
    @Override
    public Page getProductionList(Map<String, Object> production) {
        Page p=new Page();
        p.setCurrPage((int) production.get("currentPage"));
        p.setTotalCount(productionMapper.getProductionSum(production));
        production.put("pageSize",p.getPageSize());
        production.put("currentPage",((int)production.get("currentPage")-1)*p.getPageSize());
        p.setLists(productionMapper.getProductionList(production));
        return p;
    }

    @Override
    public Map<String, Object> getProductionById(Integer id) {
        return productionMapper.getProductionById(id);
    }

    @Override
    public Page everyDayProductionList(Map<String, Object> parmmap) {
        Page p=new Page();
        p.setCurrPage((int) parmmap.get("currentPage1"));
        p.setTotalCount(productionMapper.getProductionSumHome(parmmap));
        parmmap.put("pageSize",p.getPageSize());
        parmmap.put("currentPage1",((int)parmmap.get("currentPage1")-1)*p.getPageSize());
        p.setLists(productionMapper.everyDayProductionList(parmmap));
        return p;
    }

    @Override
    public List<Map<String, Object>> getProductionByCreatetime() {
        return productionMapper.getProductionByCreatetime();
    }

    @Override
    public int shouXun(Map<String, Object> map) {
        return productionMapper.shouXun(map);
    }

    @Override
    public List<Map<String, Object>> getProductionByType(Map<String, Object> map) {
        return productionMapper.getProductionByType(map);
    }
}
