package com.winxuan.service;

import com.winxuan.model.ShopItemText;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class ShopItemTextServiceImplTest {

    @Autowired
    private ShopItemTextService shopItemTextService;

    @Test
    public void findByShopItemText() {
        ShopItemText byShopItemText = shopItemTextService.findByShopItemText("360072669414367232");
        System.out.println(byShopItemText);
    }
    @Test
    public void findByShopItemTextByPage() {
        List<ShopItemText> byShopItemText = shopItemTextService.findByShopItemTextByPage(1000,1000);
        System.out.println(byShopItemText);
    }

    @Test
    public void findByShopItemTextByScrollTest(){
        List<ShopItemText> byShopItemTextByScroll = shopItemTextService.findByShopItemTextByScroll(0, 10);
        System.out.println(byShopItemTextByScroll);
    }

    @Test
    public void countShopItemText(){
        long l = shopItemTextService.countShopItemText();
        System.out.println();
    }
}