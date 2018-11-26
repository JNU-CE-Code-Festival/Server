package com.example.jnucecodefestival;

import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JnucecodefestivalApplicationTests {

    @Test
    public void contextLoads() {
        String help = "ba ba";
        Scanner sc = new Scanner(help);

        String a = sc.next();
        String b = sc.next();

        System.out.println(a + " " + b + "입니다.");
    }

}
