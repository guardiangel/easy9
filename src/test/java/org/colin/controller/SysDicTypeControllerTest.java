package org.colin.controller;

import org.colin.OaApplication;
import org.colin.model.entity.SysDicType;
import org.colin.service.SysDicTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 随着工厂环境的运行
@SpringBootTest(classes = OaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SysDicTypeControllerTest {

    @Autowired
    private SysDicTypeService sysDicTypeService;

    @Test
    public void add() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void detail() {
        SysDicType sysDicType = sysDicTypeService.findById("1001");
        System.out.println("sysDicType=" + sysDicType.toString());
    }

    @Test
    public void list() {
    }
}