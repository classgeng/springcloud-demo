package com.demo;

import com.demo.service.IDemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoServiceTests {

    @Autowired
    private IDemoService IDemoService;

    @Test
    void test() {
        IDemoService.test("class");
    }


}
