package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
    }

    @Override
    public void setApplicationContext (ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void testApplicationContext() {
        System.out.println(applicationContext);
        //调用方和实现类没有直接联系，可以直接增加实现类不修改接口
        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao.select());

        //通过名字获取bean
        alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class);
        System.out.println(alphaDao.select());
    }

    @Test
    public void testBeanManagement() {
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);

        //容器实例化bean只实例化一次，是单例的
        alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
    }

    @Test
    public void testBeanConfig() {
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    //更简单使用bean的方法
    @Autowired
    @Qualifier("alphaHibernate")
    private AlphaDao alphaDao;

    @Autowired
    private AlphaService alphaService;

    @Autowired
    private  SimpleDateFormat simpleDateFormat;

    @Test
    public void testDI() {
        System.out.println(alphaDao);
        System.out.println(alphaService);
        System.out.println(simpleDateFormat);

    }
}
