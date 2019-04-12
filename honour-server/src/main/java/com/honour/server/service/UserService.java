package com.honour.server.service;

import com.honour.server.dao.UserMapper;
import com.honour.server.entity.pojo.Role;
import com.honour.server.entity.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserInfoAndRolesByAccount(String account) {
        User user = userMapper.findByAccountName(account);
        List<Role> roleList = userMapper.getUserRolesByUid(user.getPid());
        user.setRoleList(roleList);
        return user;
    }

    //告诉SpringBoot这是一个异步任务，SpringBoot会自动开启一个线程去执行
    @Async
    public void testAsyncService() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行异步成功");
    }

    /* {秒数} {分钟} {小时} {日期} {月份} {星期} {年份(可为空)}
     *  cron的六个符号分别对应以上时间单位，空格隔开
     *  * 表示所有值；
     *  ? 表示未说明的值，即不关心它为何值；
     *  - 表示一个指定的范围；
     *  , 表示附加一个可能值；
     *   / 符号前表示开始时间，符号后表示每次递增的值；
     */
    @Scheduled(cron = "0 * * * * MON-SAT")
    public void testSchedule() {
        System.out.println("测试定时任务成功");
    }
}
