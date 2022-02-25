package com.hrq.funds.service.imp;

import cn.itcast.fegin.clients.UserClient;
import cn.itcast.fegin.pojo.User;
import cn.itcast.fegin.pojo.UserBal;
import com.hrq.funds.mapper.UserBalMapper;
import com.hrq.funds.service.UserBalService;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserBalServiceImp implements UserBalService {
    @Autowired
    private UserBalMapper userBalMapper;
    @Autowired
    private UserClient userClient;
    @Transactional
    public void saveBal(UserBal userBal){

        //TransactionManager manager = TransactionManagerHolder.get();
        this.checkUser(userBal);
        //保存或更新用户
        if (userBal.getId()  != null) {
            this.userBalMapper.updateBalById(userBal);
        }else{
            this.userBalMapper.saveBal(userBal);
        }
    }
    @Transactional
    @Override
    public Integer updateBalByUserId(UserBal userBal) {
        String xid = RootContext.getXID();
        System.out.println("资金事务id："+xid);
        checkUser(userBal);
        Integer integer = this.userBalMapper.updateBalByUserId(userBal);
        if (integer == 0) {
            throw new RuntimeException("余额更新失败，请检查用户余额信息是否维护");
        }
        return integer;
    }
    /**
     * 检查用户是否合法
     * @param userBal
     */
    private void checkUser(UserBal userBal) {
        //校验用户合法性
        User userInfo = userClient.findUserById(userBal.getUserId());
        if (userInfo == null) {
            throw new RuntimeException("用户不存在");
        } else if (userInfo.getId() == null) {
            throw new RuntimeException("查询用户异常");
        }
    }

}
