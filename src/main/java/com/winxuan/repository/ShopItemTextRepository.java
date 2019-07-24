package com.winxuan.repository;

import com.winxuan.model.ShopItemText;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;
import java.util.List;

/**
 * @author leitao.
 * @category
 * @time: 2019/7/23 0023-11:48
 * @version: 1.0
 * @description:
 *
 *   第一种方式，类似于JPA，编写一个ElasticsearchRepository
 *   第一个泛型为Bean的类型
 *   第二个泛型为Bean的主键类型
 *
 **/
public interface ShopItemTextRepository extends ElasticsearchRepository<ShopItemText,String> {

    ShopItemText findByProductsaleidAndType(Long productsaleid,String type);

    List<ShopItemText> findByProductsaleid(Long productsaleid);

    List<ShopItemText> findByTsBetween(String start,String end);

}
