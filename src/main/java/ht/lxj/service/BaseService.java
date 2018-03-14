package ht.lxj.service;

import ht.lxj.common.Pager;

import java.util.List;

/*
* 基本Service方法接口
* */
public interface BaseService {
    void save(Object obj);  //增加数据
    void remove(Object obj);  //可删除多个数据
    void removeById(Long uid);  //根据id删除数据
    void update(Object obj);  //修改数据

    Object getById(Long uid);  //根据id查询数据
    List<Object> listAll();  //查询列表中所有数据
    List<Object> listPager(Pager pager);  //分页查询
    Long count();  //查询数据表中有多少条数据

    //List<Object> listPagerCriteria(Pager pager, Object obj);
    //Long countCriteria(Object obj);
}
