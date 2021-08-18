package com.atguigu.time;

import org.junit.Test;

public class IntTest {
    @Test
    public void tesy01( ) {
        Integer a = 100, b = 100, c = 150, d = 150;
        System.out.println(a == b);
        System.out.println(c == d);
    }

    @Test
    public void testSring(){
        String smsContentTemplate ="您的短信验证码是validCode.您正在通过手机号phone进行messageType操作.如非本人操作，请忽略该短信.";
        String messageContent = smsContentTemplate.replace("validCode","1234")
                .replace("phone","13051813722")
                .replace("messageType","登陆");

    }
}
