package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")//让这个bean不是单例的
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    public AlphaService() {
        System.out.println("实例化alphaservice");
    }

    @PostConstruct //在构造器之后调用这个方法
    public void init() {
        System.out.println("初始化alphaservice");
    }

    @PreDestroy //在销毁之前调用这个方法
    public void destory() {
        System.out.println("销毁alphaservice");
        return;
    }

    public String find() {
        return alphaDao.select();
    }
}
