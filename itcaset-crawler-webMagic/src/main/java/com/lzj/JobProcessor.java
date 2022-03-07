package com.lzj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Component
public class JobProcessor implements PageProcessor {

    @Autowired
    private SpringDataPipeline springDataPipeline;


    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 100)
    public void process() {
        Spider.create(new JobProcessor())
                //初始访问url地址
                .addUrl("https://www.jd.com/")
                .addPipeline(this.springDataPipeline)
                .setScheduler(new QueueScheduler()
                        .setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
                .thread(5)
                .run();

    }
    @Override
    public void process(Page page) {
        List<String > img_urls = page.getHtml().xpath("//img/@src").all();
        List<JobInfo> jobInfo_list = new ArrayList<>();
        for (int i = 0; i <img_urls.size() ; i++) {
            JobInfo jobInfo = new JobInfo();
            String url = img_urls.get(i);
            //职位详情url
            jobInfo.setUrl(url);
            // 初始化 Date 对象
            Date date = new Date();
//            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            jobInfo.setTime(ft.format(date));
            jobInfo_list.add(jobInfo);
        }
        //保存数据
        page.putField("jobInfo", jobInfo_list);


    }
    private Site site = Site.me();
    @Override
    public Site getSite() {
        return site;
    }
//    public static void main(String[] args) {
//
//    }


}
