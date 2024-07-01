package com.category.item.service;

import com.category.item.controller.responsedto.*;
import com.category.item.domain.Brand;
import com.category.item.domain.Category;
import com.category.item.domain.Item;
import com.category.item.exception.ItemNotFoundException;
import com.category.item.repository.BrandJpaAdapter;
import com.category.item.repository.CategoryJpaAdapter;
import com.category.item.repository.ItemJpaAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CompositeServiceImpl implements CompositeService {
    private final ItemJpaAdapter itemJpaAdapter;
    private final BrandJpaAdapter brandJpaAdapter;
    private final CategoryJpaAdapter categoryJpaAdapter;

    private DecimalFormat currencyFormatter = new DecimalFormat("###,###");

    // 카테고리별 최저가격 브랜드와 상품 가격, 총액을 조회하는 서비스
    @Override
    public CategoryBrandMinPriceResponse findCategoryMinPrice() {

        CategoryBrandMinPriceResponse result = new CategoryBrandMinPriceResponse();
        List<CategoryBrandPriceDto> categoryBrandPriceDtos = new LinkedList<>();
        int totalPrice = 0;

        List<Brand> allBrands = brandJpaAdapter.findAll();
        List<Category> allCategories = categoryJpaAdapter.findAll();
        for (Category category : allCategories) {
            // 카테고리별 상품 조회
            List<Item> items = itemJpaAdapter.findByCategoryId(category.getId());
            // 카테고리별 최저가 조회
            Optional<Item> minItem = items.stream().min(Comparator.comparing(Item::getPrice));
            if (minItem.isPresent()) {
                Item item = minItem.get();
                Brand brand = allBrands.stream().filter(loopBrand -> item.getBrandId() == loopBrand.getId()).findAny().orElse(null);
                CategoryBrandPriceDto dto
                        = new CategoryBrandPriceDto(category.getName(), brand.getName(), currencyFormatter.format(item.getPrice()));
                categoryBrandPriceDtos.add(dto);
                totalPrice += item.getPrice();
            }
        }

        // 결과 저장
        result.setItems(categoryBrandPriceDtos);
        result.setTotalPrice(currencyFormatter.format(totalPrice));

        return result;
    }

    // 브랜드로 모든 카테고리 상품을 구매할 때 최저 가격의 브랜드 조회하는 서비스
    @Override
    public BrandMinPriceResponse findBrandMinPrice() {
        BrandMinPriceResponse result = new BrandMinPriceResponse();
        List<CategoryPriceDto> categoryBrandPriceDtos = new LinkedList<>();
        int totalPrice = 0;

        Map<Long, List<Item>> itemMap = new HashMap<>(); // 브랜드 Id와 Item을 저장한다. (DB 조회보다는 메모리에 올려서 찾기)
        List<BrandSumPriceDto> brandSumPriceDtoList = new ArrayList<>(); // 브랜드 Id와 price를 저장한다. (최저가 가져오기 위한 객체)

        List<Brand> allBrands = brandJpaAdapter.findAll();
        List<Category> allCategories = categoryJpaAdapter.findAll();
        for (Brand brand : allBrands) {
            List<Item> items = itemJpaAdapter.findByBrandId(brand.getId());
            int brandSum = items.stream().mapToInt(item -> item.getPrice()).sum();

            itemMap.put(brand.getId(), items);
            brandSumPriceDtoList.add(new BrandSumPriceDto(brand.getId(), brandSum));
        }

        // 최저가 조회
        Optional<BrandSumPriceDto> minSumPriceBrand = brandSumPriceDtoList.stream().min(Comparator.comparing(BrandSumPriceDto::getSumPrice));
        if (minSumPriceBrand.isPresent()) {
            Long brandId = minSumPriceBrand.get().getBrandId();
            Brand brand = allBrands.stream().filter(loopBrand -> brandId == loopBrand.getId()).findAny().orElse(null);
            List<Item> items = itemMap.get(brandId);
            for (Item item : items) {
                Category category = allCategories.stream().filter(loopCategory -> item.getCategoryId() == loopCategory.getId()).findAny().get();

                categoryBrandPriceDtos.add(new CategoryPriceDto(category.getName(), currencyFormatter.format(item.getPrice())));
            }

            // 결과 저장
            result.setBrandName(brand.getName());
            result.setItems(categoryBrandPriceDtos);
            result.setTotalPrice(currencyFormatter.format(minSumPriceBrand.get().getSumPrice()));
        }

        return result;
    }

    // 카테고리 이름으로 최저, 최고 가격 브랜드 조회
    @Override
    public BrandItemByCategoryNameResponse findBrandItemByCategoryName(String categoryName) {
        BrandItemByCategoryNameResponse result = new BrandItemByCategoryNameResponse();
        List<BrandItemDto> minBrandItems = new ArrayList<>();
        List<BrandItemDto> maxBrandItems = new ArrayList<>();


        Category category = categoryJpaAdapter.findByName(categoryName);
        if (category == null) {
            throw new ItemNotFoundException();
        }

        Map<Long, String> allBrandMap = brandJpaAdapter.findAllWithMap();

        List<Item> categoryItems = itemJpaAdapter.findByCategoryId(category.getId());
        Optional<Item> minItem = categoryItems.stream().min(Comparator.comparing(Item::getPrice));
        Optional<Item> maxItem = categoryItems.stream().max(Comparator.comparing(Item::getPrice));
        if (minItem.isPresent()) {
            Item item = minItem.get();
            String brandName = allBrandMap.get(item.getBrandId());
            BrandItemDto brandItem = new BrandItemDto(brandName, currencyFormatter.format(item.getPrice()));
            minBrandItems.add(brandItem);
        }

        if (maxItem.isPresent()) {
            Item item = maxItem.get();
            String brandName = allBrandMap.get(item.getBrandId());
            BrandItemDto brandItem = new BrandItemDto(brandName, currencyFormatter.format(item.getPrice()));
            maxBrandItems.add(brandItem);
        }

        // 결과 저장
        result.setCategoryName(category.getName());
        result.setMinPriceItems(minBrandItems);
        result.setMaxPriceItems(maxBrandItems);

        return result;
    }
}
