package com.demo;

import com.demo.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoServiceTests {

    @Autowired
    private DemoService demoService;

    @Test
    void test() {
        demoService.test("class");
    }


}
