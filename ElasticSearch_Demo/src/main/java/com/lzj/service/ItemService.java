package com.lzj.service;

import com.lzj.Item;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {

    /**
     * 保存
     *
     * @param item
     */
    void save(Item item);


    void delete(Item item);

    void saveAll(List<Item> items);

    Iterable<Item> findAll();

    Page<Item> findAllPage(int i, int i1);
}
