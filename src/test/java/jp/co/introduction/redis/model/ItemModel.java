package jp.co.introduction.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel {

    private String itemCode;

    private String itemName;

    private String shopName;

    private String shopCode;
}