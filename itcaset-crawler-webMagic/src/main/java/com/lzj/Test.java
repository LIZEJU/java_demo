package com.lzj;

import com.lou.simhasher.SimHasher;
import org.apache.commons.io.IOUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

//@Component
public class Test {




        @Scheduled(cron = "0/5 * * * * *")
        public void test() {
//            System.out.println(LocalDateTime.now()+"任务执行了");
            System.out.println(LocalDateTime.now()+"任务执行了");

            String str1 = readAllFile("F:\\1\\snow3\\07-爬虫\\课前资料\\simhash\\src\\main\\resources\\testin.txt");
            SimHasher hash1 = new SimHasher(str1);
            //打印simhash签名
            System.out.println(hash1.getSignature());
            System.out.println("============================");

            String str2 = readAllFile("F:\\1\\snow3\\07-爬虫\\课前资料\\simhash\\src\\main\\resources\\testin2.txt");
            //打印simhash签名
            SimHasher hash2 = new SimHasher(str2);
            System.out.println(hash2.getSignature());
            System.out.println("============================");

//            打印海明距离
            System.out.println(hash1.getHammingDistance(hash2.getSignature()));
        }

    public static String readAllFile(String filename) {
        String everything = "";
        try {
            FileInputStream inputStream = new FileInputStream(filename);
            everything = IOUtils.toString(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return everything;
    }



}
