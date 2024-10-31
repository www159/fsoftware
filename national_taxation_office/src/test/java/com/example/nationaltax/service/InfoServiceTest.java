package com.example.nationaltax.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.example.nationaltax.bean.Complaint;
import com.example.nationaltax.bean.Info;
import com.example.nationaltax.util.MockObject;

@SpringBootTest
public class InfoServiceTest {
    @Autowired
    private InfoService infoService;

    private static Logger logger = LoggerFactory.getLogger(InfoServiceTest.class);

    @BeforeTestClass
    public static void runBeforeTestInfo() {

    }

    @Test
    public void saveTest() {
        Info info = MockObject.mockInfo("some");

        boolean result = infoService.save(info);

        long infoId = info.getInfoId();

        logger.info("id is {}", infoId);

        assertTrue(result);
    }

    @Test
    public void getByIdTest() {
        Info info = MockObject.mockInfo("get by id");

        infoService.save(info);

        Info result = infoService.getById(info.getInfoId());

        assertTrue(result.getContent().equals(new String("get by id content")));
    }

    @Test
    public void updateByIdTest() {
        Info info = MockObject.mockInfo("update by id");

        infoService.save(info);

        info.setContent("updated");

        boolean result = infoService.updateById(info);

        assertTrue(result);

        info = infoService.getById(info.getInfoId());

        assertTrue(info.getContent().equals(new String("updated")));
    }

    @Test
    public void removeByIdTest() {
        Info info = MockObject.mockInfo("another");
        boolean result = infoService.save(info);
        long id = info.getInfoId();
        infoService.removeById(id);
        
        assertTrue(result);
    }


}
