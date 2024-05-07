package com.ssafy.sebakwi.util;

import com.ssafy.sebakwi.product.domain.Product;
import com.ssafy.sebakwi.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner, DisposableBean {

    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Product product1 = Product.builder().
            name("삼성 갤럭시북")
            .quantity(10)
            .build();

        Product product2 = Product.builder().
            name("삼성 세탁기")
            .quantity(5)
            .build();

        productRepository.saveAll(Arrays.asList(product1, product2));
        log.info("dummy data 생성");
    }

    @Override
    public void destroy() throws Exception {
        productRepository.deleteAll();
        log.info("dummy data ");
    }
}
