package com.lzj;

import com.lzj.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringDataESTest {


    @Autowired
    private ItemService itemService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 创建索引和映射
     */
    @Test
    public void createIndex() {
        this.elasticsearchTemplate.createIndex(Item.class);
//        this.elasticsearchTemplate.putMapping(Item.class);
    }

    /**
     * 测试保存文档
     */
    @Test
    public void saveArticle() {
        Item item = new Item();
        item.setId(100);
        item.setTitle("测试SpringData ElasticSearch");
        item.setContent("Spring Data ElasticSearch 基于 spring data API 简化操作，实现搜索引擎功能");
        this.itemService.save(item);
    }


    /**
     * 测试更新
     */
    @Test
    public void update() {
        Item item = new Item();
        item.setId(100);
        item.setTitle("elasticSearch 3.0版本发布...更新");
        item.setContent("ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口");
        this.itemService.save(item);
    }

    /**
     * 测试删除
     *
     */
    @Test
    public void delete() {
        Item item = new Item();
        item.setId(100);
        this.itemService.delete(item);
    }
    /**
     * 批量保存
     *
     */
    @Test
    public void saveAll(){
        List<Item> items = new ArrayList<>();
        for(int i=1;i<=100;i++){
            Item item = new Item();
            item.setId(i);
            item.setTitle(i+"elasticSearch 3.0版本发布..，更新");
            item.setContent(i+"ElasticSearch批量插入"+i);
            items.add(item);
        }
        this.itemService.saveAll(items);
    }
    /**
     * 查询所有
     */
    @Test
    public void findAll(){
        Iterable<Item> list = itemService.findAll();
        for(Item article:list){
            System.out.println(article);
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void findAllPage(){
        Page<Item> page = itemService.findAllPage(1,10);
        for(Item article:page.getContent()){
            System.out.println(article);
        }
    }


}
