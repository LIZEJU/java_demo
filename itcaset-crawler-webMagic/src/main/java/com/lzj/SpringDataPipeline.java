package com.lzj;

import org.apache.catalina.Container;

import org.apache.catalina.Valve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Set;

@Component
public class SpringDataPipeline implements Pipeline {
    @Autowired
    private JobInfoService jobInfoService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取需要保存到MySQL的数据
        List<JobInfo> jobInfos = resultItems.get("jobInfo");

        //判断获取到的数据不为空
        if(jobInfos!=null) {
            //如果有值则进行保存
            for (int i = 0; i < jobInfos.size(); i++) {
                this.jobInfoService.save(jobInfos.get(i));
            }

        }
    }
}
