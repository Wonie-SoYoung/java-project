package com.soyoung.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soyoung.core.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*  Dao接口
*/
@Repository
public interface UserDao extends BaseMapper<User> {

    IPage<User> listUser(Page<User> entityPage);
}
