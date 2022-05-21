package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaHibernate")//自定义bean的名字
public class AlphaDaoHibnernateImpl implements AlphaDao{
    @Override
    public String select() {
        return "Hibernate";
    }

}
