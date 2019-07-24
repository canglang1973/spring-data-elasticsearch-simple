package com.winxuan.service;

import com.winxuan.model.ShopItemText;
import com.winxuan.repository.ShopItemTextRepository;
import com.winxuan.support.Constants;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ScrolledPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.util.CloseableIterator;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author leitao.
 * @category
 * @time: 2019/7/23 0023-11:53
 * @version: 1.0
 * @description:
 **/
@Slf4j
@Service("shopItemTextService")
public class ShopItemTextServiceImpl implements ShopItemTextService {

    @Autowired
    private ShopItemTextRepository shopItemTextRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public ShopItemText findByShopItemText(String id) {
        Optional<ShopItemText> shopItemTextOptional = shopItemTextRepository.findById(id);
        return shopItemTextOptional.get();
    }

    @Override
    public List<ShopItemText> findByShopItemTextByPage(int pageNo, int pageSize) {
        //普通分页只能查询10000条
        Page<ShopItemText> all = shopItemTextRepository.findAll(PageRequest.of(pageNo, pageSize));
        return all.getContent();
    }

    @Override
    public List<ShopItemText> findByShopItemTextByScroll(int page, int pageSize) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("text", "编:(加)泰德.纳斯密斯"))
                .withIndices(ShopItemText.INDEX)
                .withTypes("doc")
                .withPageable(PageRequest.of(page, pageSize))
                .build();

        Page<ShopItemText> scroll = elasticsearchTemplate.startScroll(1000, searchQuery, ShopItemText.class);

        String scrollId = ((ScrolledPage) scroll).getScrollId();
        List<ShopItemText> shopItemTexts = new ArrayList<>();
        int i=0;
        while (scroll.hasContent()) {
            shopItemTexts.addAll(scroll.getContent());
            scrollId = ((ScrolledPage) scroll).getScrollId();
            scroll = elasticsearchTemplate.continueScroll(scrollId, 1000, ShopItemText.class);
            log.info(scrollId);
        }
        elasticsearchTemplate.clearScroll(scrollId);
        return shopItemTexts;
    }

    @Override
    public List<ShopItemText> findByShopItemTextByStream(int page, int pageSize) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("shop_item_text")
                .withTypes("doc")
                .withPageable(PageRequest.of(page, pageSize))
                .build();

        CloseableIterator<ShopItemText> stream = elasticsearchTemplate.stream(searchQuery, ShopItemText.class);

        List<ShopItemText> shopItemTexts = new ArrayList<>();
        while (stream.hasNext()) {
            shopItemTexts.add(stream.next());
        }
        return shopItemTexts;
    }

    @Override
    public long countShopItemText() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("type", "DESC"))
                .withIndices(ShopItemText.INDEX)
                .withTypes("doc")
                .build();
        long count = elasticsearchTemplate.count(searchQuery);
        return count;
    }

    @Override
    public List<ShopItemText> findByTsBetween(Date start, Date end) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.ES_DATEFORMAT_PATTERN);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC_TIME_ZONE));
        String startStr = simpleDateFormat.format(start);
        String endStr = simpleDateFormat.format(end);
        List<ShopItemText> byTsBetween = shopItemTextRepository.findByTsBetween(startStr, endStr);
        return byTsBetween;
    }


}
