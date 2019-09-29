package com.tuguri8.seoulphone.api.infrastructure.persistence.jpa.entity;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Category {
    BAG("가방", "PRA000"),
    JEWEL("귀금속", "PRO000"),
    BOOK("도서용품", "PRB000"),
    OFFICE("사무용품", "PRC000"),
    INDUSTRIAL("산업용품", "PRD000"),
    SHOPPINGBAG("쇼핑백", "PRQ000"),
    SPORTS("스포츠용품", "PRE000"),
    MUSIC("악기", "PRR000"),
    STOCK("유가증권", "PRM000"),
    CLOTHES("의류", "PRK000"),
    CAR("자동차", "PRF000"),
    ELECTRONIC("전자기기", "PRG000"),
    WALLET("지갑", "PRH000"),
    CERTIFICATE("증명서", "PRN000"),
    COMPUTER("컴퓨터", "PRI000"),
    CARD("카드", "PRP000"),
    CASH("현금", "PRL000"),
    PHONE("휴대폰", "PRJ000"),
    ETC("기타물품", "PRZ000");

    private String name;
    private String code;

    Category(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static Category getCategoryFromName(String categoryName) {
        return Arrays.stream(Category.values())
                     .filter(x -> x.getName().equals(categoryName))
                     .findFirst()
                     .orElse(ETC);
    }
}
