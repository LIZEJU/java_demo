package com.lzj.dao;

import com.lzj.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ItemRepository extends ElasticsearchRepository<Item, Integer> {
}
