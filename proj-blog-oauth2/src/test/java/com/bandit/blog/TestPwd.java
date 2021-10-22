package com.bandit.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestPwd {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void genPwd() {
        String str = "bandit";
        System.out.println("密文：" + passwordEncoder.encode(str));
    }
}
