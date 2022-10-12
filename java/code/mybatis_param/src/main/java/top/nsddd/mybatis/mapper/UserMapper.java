package top.nsddd.mybatis.mapper;

import top.nsddd.mybatis.pojo.User;

interface UserMapper {
    /*根据用户名来查询用户信息*/
    User getUserByUsername(String username);


}