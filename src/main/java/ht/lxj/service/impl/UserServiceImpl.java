package ht.lxj.service.impl;

import ht.lxj.bean.User;
import ht.lxj.common.Pager;
import ht.lxj.dao.UserDao;
import ht.lxj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/*
* UserService实现类
* */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void save(Object obj) {
        userDao.save(obj);
        User user = (User) obj;
        if (user.getUid()!=null) {
            //保存好数据后直接放在缓存中,方便下次获取值时，直接从缓存中读取数据，不需访问数据库
            ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
            valueOperations.set("user-"+user.getUid(),user);
        }
    }

    @Override
    public void remove(Object obj) {
        userDao.remove(obj);

        User user = (User) obj;
        //先判断缓存中是否存在此键key，若有存在则删除此键
        Boolean key_exist = redisTemplate.hasKey("user-"+user.getUid());
        if(key_exist){
            redisTemplate.delete("user-"+user.getUid());
        }
    }

    @Override
    public void removeById(Long uid) {
        userDao.removeById(uid);

        //先判断缓存中是否存在此键key，若有存在则删除此键
        Boolean key_exist = redisTemplate.hasKey("user-"+uid);
        if(key_exist){
            redisTemplate.delete("user-"+uid);
        }
    }

    @Override
    public void update(Object obj) {
        //先修改数据库中的值
        userDao.update(obj);

        //查询缓存中是否有此数据值
        ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
        User user = (User) obj;
        User user1 = valueOperations.get("user-"+user.getUid());
        //若存在此数据则重新设置缓存值
        if(user1!=null){
            valueOperations.set("user-"+user.getUid(),user);
        }
    }

    @Override
    public Object getById(Long uid) {
        //先获取缓存中的数据
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        User user = valueOperations.get("user-"+uid);
        //判断缓存中是否有此数据，若有直接读出缓存中数据
        if(user!=null){
            System.out.println("从缓存中获取数据"+uid);
            return user;
        } else {
            //若缓存中没有对应值，则从数据库中查询，并将此值设置在缓存中，下一次读取时，就不需要访问数据库
            Object objUser = userDao.getById(uid);
            if(objUser!=null){
                valueOperations.set("user-"+uid,(User) objUser);
            }
            System.out.println("从数据库中获取数据"+uid);
            return objUser;
        }
    }

    @Override
    public List<Object> listAll() {
        List<Object> listRedis = new ArrayList<>();
        List<Object> listUser = new ArrayList<>();
        //先判断缓存中是否存在list集合(查询所有以user-开头的key)
        Set<Object> setRedis = redisTemplate.keys("user-*");
        //判断缓存中list的值是否和数据库中值一样(若size与count值不一致时可能不相同)
        Long countAll = this.count();
        if (setRedis.size() == countAll && setRedis != null) {
            Iterator<Object> it = setRedis.iterator();
            while (it.hasNext()) {
                //遍历set中查询出的key,根据每个key取出value,再设置到list中
                User user = (User) redisTemplate.opsForValue().get(it.next());
                listRedis.add(user);
            }

            System.out.println("从缓存中获取用户列表信息"+countAll);
            return listRedis;
        } else {
            listUser = userDao.listAll();
            if (listUser != null && listUser.size() != 0 ) {
                Iterator iter = listUser.iterator();
                while (iter.hasNext()) {
                    User user = (User) iter.next();
                    redisTemplate.opsForValue().set("user-"+user.getUid(),user);
                }
            }
            System.out.println("从数据库中获取用户列表信息"+listUser.size());
            return listUser;
        }
    }

    @Override
    public List<Object> listPager(Pager pager) {
        return null;
    }

    @Override
    public Long count() {
        return userDao.count();
    }
}
