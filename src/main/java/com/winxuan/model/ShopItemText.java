package com.winxuan.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author leitao.
 * @category
 * @time: 2019/7/23 0023-11:44
 * @version: 1.0
 * @description:
 * @Document(indexName="blob3",type="article")： indexName：索引的名称（必填项）
 * type：索引的类型
 * @Id：主键的唯一标识
 * @Field(index=true,analyzer="ik_smart",store=true,searchAnalyzer="ik_smart",type = FieldType.text)
 * index：是否设置分词
 * analyzer：存储时使用的分词器
 * searchAnalyze：搜索时使用的分词器
 * store：是否存储
 * type: 数据类型
 * ---------------------
 **/
@Data
@Document(indexName = ShopItemText.INDEX, type = "doc")
public class ShopItemText implements Serializable {

    public static final String INDEX = "shop_item_text";

    //@Id 文档主键 唯一标识
    @Id
    //@Field 每个文档的字段配置（类型、是否分词、是否存储、分词器 ）
    @Field(store = false, index = false, type = FieldType.Text)
    private String id;

    @Field(index = true, analyzer = "ik_smart", store = true, searchAnalyzer = "ik_smart", type = FieldType.Long)
    private Long shop;

    @Field(index = true, analyzer = "ik_smart", store = true, searchAnalyzer = "ik_smart", type = FieldType.Long)
    private Long productsaleid;

    @Field(index = true, analyzer = "ik_smart", store = true, searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String digest;

    @Field(index = true, analyzer = "ik_smart", store = true, searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String text;

    @Field(index = true, analyzer = "ik_smart", store = true, searchAnalyzer = "ik_smart", type = FieldType.Text)
    private String type;

    @Field(index = true, analyzer = "ik_smart", store = true, searchAnalyzer = "ik_smart", type = FieldType.Date)
    private Date createtime;

    @Field(index = true, analyzer = "ik_smart", store = true, searchAnalyzer = "ik_smart", type = FieldType.Date)
    private Date ts;

}
