package com.soyoung.core.dao;

import com.soyoung.core.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
*  Dao接口
*/
@Repository
public interface UserDao extends BaseMapper<User> {

}
