package com.category.item.service;

import com.category.item.domain.Brand;
import com.category.item.domain.Category;
import com.category.item.domain.Item;
import com.category.item.repository.BrandJpaAdapter;
import com.category.item.repository.CategoryJpaAdapter;
import com.category.item.repository.ItemJpaAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class InitDbService {
    private final BrandJpaAdapter brandJpaAdapter;
    private final CategoryJpaAdapter categoryJpaAdapter;
    private final ItemJpaAdapter itemJpaAdapter;

    public void brandDataInit() {
        List<Brand> brands = new LinkedList<>();
        brands.add(new Brand("A"));
        brands.add(new Brand("B"));
        brands.add(new Brand("C"));
        brands.add(new Brand("D"));
        brands.add(new Brand("E"));
        brands.add(new Brand("F"));
        brands.add(new Brand("G"));
        brands.add(new Brand("H"));
        brands.add(new Brand("I"));

        for (Brand brand : brands) {
            brandJpaAdapter.save(brand);
        }
    }

    public void categoryDataInit() {
        List<Category> categories = new LinkedList<>();
        categories.add(new Category("상의"));
        categories.add(new Category("아우터"));
        categories.add(new Category("바지"));
        categories.add(new Category("스니커즈"));
        categories.add(new Category("가방"));
        categories.add(new Category("모자"));
        categories.add(new Category("양말"));
        categories.add(new Category("액세서리"));

        for (Category category : categories) {
            categoryJpaAdapter.save(category);
        }
    }

    public void itemDataInit() {
        int[][] priceArr = {
            {11200, 5500, 4200, 9000, 2000, 1700, 1800, 2300},
            {10500, 5900, 3800, 9100, 2100, 2000, 2000, 2200},
            {10000, 6200, 3300, 9200, 2200, 1900, 2200, 2100},
            {10100, 5100, 3000, 9500, 2500, 1500, 2400, 2000},
            {10700, 5000, 3800, 9900, 2300, 1800, 2100, 2100},
            {11200, 7200, 4000, 9300, 2100, 1600, 2300, 1900},
            {10500, 5800, 3900, 9000, 2200, 1700, 2100, 2000},
            {10800, 6300, 3100, 9700, 2100, 1600, 2000, 2000},
            {11400, 6700, 3200, 9500, 2400, 1700, 1700, 2400},
        };
        
        for (int brandIndex = 0; brandIndex < priceArr.length; brandIndex++) {
            for (int categoryIndex = 0; categoryIndex < priceArr[brandIndex].length; categoryIndex++) {
                int price = priceArr[brandIndex][categoryIndex];
                Item item = new Item((long) brandIndex+1, (long) categoryIndex+1, price);
                itemJpaAdapter.save(item);
            }
        }
    }
}
