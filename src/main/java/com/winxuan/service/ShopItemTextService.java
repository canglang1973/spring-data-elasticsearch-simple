package com.winxuan.service;

import com.winxuan.model.ShopItemText;

import java.util.List;

/**
 * @author leitao.
 * @category
 * @time: 2019/7/23 0023-11:52
 * @version: 1.0
 * @description:
 **/
public interface ShopItemTextService {

    ShopItemText findByShopItemText(String id);

    /**
     * 普通分页只能查询10000条
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<ShopItemText> findByShopItemTextByPage(int pageNo,int pageSize);

    List<ShopItemText> findByShopItemTextByScroll(int pageNo,int pageSize);
}
