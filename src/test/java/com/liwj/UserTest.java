package com.liwj;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by liwan on 2017/7/5.
 */
@SpringBootTest
@RunWith(SpringRunner.class)

public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAdd() {
        User user = new User();
        user.setEmail("222222@qq.com");
        user.setUserName("liwj");
        user.setNickName("lwj");
        user.setPassword("111111");
        userRepository.save(user);

    }


    private void print(Page<User> datas) {
        System.out.println("总条数：" + datas.getTotalElements());
        System.out.println("总页数：" + datas.getTotalPages());
        for (User u : datas) {
            System.out.println(u.getId() + "====" + u.getUserName());
        }
    }

    @Test
    public void testFind() {
        User user = userRepository.findOne(1);
        System.out.println(user.getEmail() + user.getUserName() + user.getNickName());
    }

    @Test
    public void testUpdate() {
        User user = userRepository.findOne(1);
        user.setNickName("LIWJJJJ");
        userRepository.save(user);
        System.out.println(user.getEmail() + user.getUserName() + user.getNickName());

    }

    @Test
    public void testDelete() {
        userRepository.delete(1);
    }

    @Test
    public void testAddBatch() {
        for (Integer i = 0; i < 5; i++) {
            User user = new User();
            user.setNickName("昵称" + i);
            user.setUserName("user" + i);
            user.setPassword("pwd" + i);
            user.setEmail("email" + i + "@domain.com");
            userRepository.save(user);
        }
    }

    @Test
    public void testFindAll() {
        List<User> list = userRepository.findAll();
        for (User u : list) {
            System.out.println("nickName : " + u.getNickName() + ", email : " + u.getEmail());
        }
    }

    @Test
    public void testFindById() {
        User user = userRepository.findById(3);
        System.out.println(user.getEmail() + user.getUserName() + user.getNickName());

    }

    @Test
    public void testFindByUserName() {
        User user = userRepository.findByUserName("user4");
        System.out.println(user.getEmail() + user.getUserName() + user.getNickName());

    }

    @Test
    public void testUpdPwd() {
        userRepository.updatePwd("user3", "123456");

    }

    @Test
    public void testUpdEmail() {
        userRepository.updateEmail("user2", "user2@139.com");

    }

    @Test
    public void testPage() {
        Sort sort = new Sort(Sort.Direction.DESC, "userName");
        List<User> list = userRepository.findAll(sort);
        for (User u : list) {
            System.out.println((u.getUserName()));
        }
    }

    @Test
    public void testSort2() {
        List<User> list = userRepository.findAll(SortTools.basicSort());
        for (User u : list) {
            System.out.println(u.getUserName());
        }
    }

    @Test
    public void testSort3() {
        List<User> list = userRepository.findAll(SortTools.basicSort("desc", "userName"));
        for (User u : list) {
            System.out.println(u.getUserName());
        }
    }

    @Test
    public void testSort4() {
        List<User> list = userRepository.findAll(SortTools.basicSort(new SortDto("desc", "userName"), new SortDto("id")));
        for (User u : list) {
            System.out.println(u.getId() + "====" + u.getUserName());
        }
    }

    @Test
    public void test11() {
        Pageable pageable = new PageRequest(1, 2);
        Page<User> datas = userRepository.findAll(pageable);
        System.out.println("总条数：" + datas.getTotalElements());
        System.out.println("总页数：" + datas.getTotalPages());
        for (User u : datas) {
            System.out.println(u.getId() + "====" + u.getUserName());
        }
    }

    @Test
    public void test21() {
        Page<User> datas = userRepository.findAll(PageableTools.basicPage(0));
        print(datas);
    }

    @Test
    public void test22() {
        Page<User> datas = userRepository.findAll(PageableTools.basicPage(1, 2));
        print(datas);
    }

    @Test
    public void test23() {
        Page<User> datas = userRepository.findAll(PageableTools.basicPage(1, 2, new SortDto("id")));
        print(datas);

        Page<User> datas2 = userRepository.findAll(PageableTools.basicPage(1, 2, new SortDto("ASC", "id")));
        print(datas2);
    }

    private void print(List<User> list) {
        for (User u : list) {
            System.out.println(u.getId() + "===" + u.getUserName());
        }

    }

    @Test
    public void test31() {
        List<User> list = userRepository.findAll(new BaseSearch<User>(new SearchDto("userName", "eq", "user1")));
        print(list);
    }


    @Test
    public void test32() {
        List<User> list = userRepository.findAll(SearchTools.buildSpecification(
                SearchTools.buildSpeDto("and", new SearchDto("and", "id", "gt", 4)),
                SearchTools.buildSpeDto("and", new SearchDto("userName", "ne", "user5"),
                        new SearchDto("or", "userName", "ne", "user3"))
        ));
        print(list);
    }

    @Test
    public void test41() {
        long lvar = userRepository.getTotalCount();
        System.out.println(lvar);
    }

    @Test
    public void test51() {
        User user = userRepository.findQuery();
        System.out.println(user.toString());
    }
}

