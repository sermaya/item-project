package com.category.item.repository.jpaentity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemJpaEntity {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "category_id")
    private Long categoryId;

    private int price;

    public ItemJpaEntity(Long brandId, Long categoryId, int price) {
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.price = price;
    }
}
