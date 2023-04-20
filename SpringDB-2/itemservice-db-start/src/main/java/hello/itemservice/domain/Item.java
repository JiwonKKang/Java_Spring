package hello.itemservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@Table(name = "item") 객체명이랑 테이블명이 같으면 생략가능
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", length = 10)//카멜케이스를 자동으로 변환해주기때문에 생략가능하다.
    private String itemName;

    private Integer price;//테이블 컬럼명하고 이름이 같기때문에 컬럼 어노테이션을 안써도된다.
    private Integer quantity;

    public Item() {//기본 생성자는 JPA 필수
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
