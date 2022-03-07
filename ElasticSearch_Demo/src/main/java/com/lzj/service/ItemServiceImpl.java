package com.lzj.service;

import com.lzj.Item;
import com.lzj.dao.ItemRepository;
import com.lzj.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public void save(Item item) {
        this.itemRepository.save(item);
    }

    @Override
    public void delete(Item item) {
        this.itemRepository.delete(item);
    }

    @Override
    public void saveAll(List<Item> items) {
        this.itemRepository.saveAll(items);
    }

    @Override
    public Iterable<Item> findAll() {
        return  this.itemRepository.findAll();
    }

    @Override
    public Page<Item> findAllPage(int page, int rows) {
        Page<Item> result = this.itemRepository.findAll(PageRequest.of(page,rows));
        return result;
    }
}
