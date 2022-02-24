package com.hrq.funds.web;

import cn.itcast.fegin.pojo.UserBal;
import com.hrq.funds.service.UserBalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userBal")
public class UserBalController {
    @Autowired
    private UserBalService userBalService;
    @PostMapping("/save")
    public String saveBal(UserBal userBal) {
        String msg = "保存成功";
        try {
            if (userBal.getId() != null) {
                msg = "更新成功";
            }
            userBalService.saveBal(userBal);
        } catch (Exception e) {
            msg = e.getMessage();
        }
        return msg;
    }

    @PutMapping("/updateBalByUserId")
    public Integer updateBalByUserId(@RequestBody UserBal userBal) {
        return this.userBalService.updateBalByUserId(userBal);
    }
}
