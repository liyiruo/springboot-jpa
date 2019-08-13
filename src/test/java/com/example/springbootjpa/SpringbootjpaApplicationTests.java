package com.example.springbootjpa;

import com.example.springbootjpa.dao.TestUserDao;
import com.example.springbootjpa.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootjpaApplicationTests {
    @Test
    public void contextLoads() {

    }

    @Autowired
    private TestUserDao testUserDao;

    @Test
    public void insert() {
        User user= new User();
        user.setUsername("zhangsan");
        user.setAge(23);
        testUserDao.save(user);
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(1);
        user.setAge(18);
        user.setUsername("李四");
        testUserDao.save(user);
    }

    @Test
    public void select() {
        Optional<User> user = testUserDao.findById(1);
        System.out.println(user);
    }

    @Test
    public void delete() {
        testUserDao.deleteById(1);
    }

    @Test
    public void findByUsername() {
        User user =  testUserDao.findByUsername("zhangsan");
        System.out.println("findByUsername======================>>"+user);
    }

    @Test
    public void findByUsernameAAndAge() {

        User user = testUserDao.findByUsernameAAndAge("李四", 18);

        System.out.println("18岁的user==================>" + user.getUsername()+" "+user.getAge());

    }

}
