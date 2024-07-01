package com.category.item.repository.jpaentity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brand")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandJpaEntity {
    @Id @GeneratedValue
    @Column(name = "brand_id")
    private Long id;

    private String name;

    public BrandJpaEntity(String name) {
        this.name = name;
    }
}
