package com.hrq.funds.mapper;

import cn.itcast.fegin.pojo.UserBal;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalMapper {

//    @Select("select * from user_bal where user_id = #{userId}")
//    UserBal findByUserId(Long userId);
    //@Insert("insert into user_bal(user_id,balance) " +
    //        "values(#{userId},#{balance})")
    void saveBal(UserBal userBal);

    void updateBalById(UserBal userBal);
    Integer updateBalByUserId(UserBal userBal);

}
