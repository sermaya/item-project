package com.category.item;

import com.category.item.service.InitDbService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitDbService initDbService;

    @PostConstruct
    public void init() {
        initDbService.brandDataInit();
        initDbService.categoryDataInit();
        initDbService.itemDataInit();
    }

}
