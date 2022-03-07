package com.lzj;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//import org.jsoup.nodes.Element;

import java.io.*;

public class Test5 {

    public static void main(String[] args) throws IOException {
//        获取数据get
            String content = doGet();
//            writer(content);


//        jsoup解析html
        //    解析字符串
        Document document = Jsoup.parse(content);

        //获取title的内容
        Element title = document.getElementsByTag("title").first();
        System.out.println(title.text());

        //tagname: 通过标签查找元素，比如：span
        Elements span =  document.select("div");
        for (Element element : span) {
            System.out.println(element.text());
        }

    }

    private static void writer(String content) throws IOException {
        File f= new File("F:\\1\\code\\itcast-crawler-first\\src\\main\\resources\\baidu.html");
        OutputStream out = new FileOutputStream(f);
        OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
        // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk
        writer.write(content);
        writer.close();
        out.close();
    }

    public  static String doGet() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://www.baidu.com/");
        CloseableHttpResponse response=null;
        String content=null;
        try {
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
//                System.out.println(content);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (response == null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                httpClient.close();
            }

        }
        return content;
    }
}
