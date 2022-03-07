package com.lzj;

import java.util.List;

public interface ItemService {

    //根据条件查询数据
    public List<Item> findAll(Item item);

    //保存数据
    public void save(Item item);
}
