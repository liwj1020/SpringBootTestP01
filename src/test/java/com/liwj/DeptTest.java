package com.liwj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liwan on 2017/7/6.
 */

@SpringBootTest
@RunWith(SpringRunner.class)

public class DeptTest {

    @Autowired
    DeptRepository deptRepository;

    @Test
    public void test01() {
        Dept dept = new Dept();
        dept.setDeptName("user3");
        deptRepository.save(dept);

    }
}
