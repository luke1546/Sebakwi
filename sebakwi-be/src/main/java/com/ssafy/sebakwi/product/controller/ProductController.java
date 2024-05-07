package com.ssafy.sebakwi.product.controller;

import com.ssafy.sebakwi.product.domain.Product;
import com.ssafy.sebakwi.product.domain.ProductRepository;
import com.ssafy.sebakwi.product.dto.ProductSave;
import com.ssafy.sebakwi.util.exception.CustomException;
import com.ssafy.sebakwi.util.exception.CustomExceptionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/product")
@RestController
public class ProductController {

    private final ProductRepository repository;


    /**
     * Product 정보의 유효성을 검사합니다.
     * 유효하지 않은 경우 @Validated 에 의해
     * @CustomExceptionStatus.VALIDATE_INVALID 예외를 발생시킵니다.
     *
     * @param productSave 검증이 필요한 Product 정보
     * @return 검증 성공시 "validate" 문자열 반환
     */
    @PostMapping
    public Object validateEx(@Validated @RequestBody ProductSave productSave) {
        log.info("productSave={}", productSave);

        return "validate";
    }

    /**
     * Product ID로 Product 정보를 조회합니다.
     * 만약 Product를 찾을 수 없으면 CustomExceptionStatus.PRODUCT_INVALID 예외를 발생시킵니다.
     *
     * @param Id 조회할 Product의 ID
     * @return 조회된 Product 객체
     */
//    @GetMapping
//    public Product searchEx(@RequestParam(name = "Id") Long Id) {
//        Product product =  repository.findById(Id)
//                .orElseThrow(() -> new CustomException(CustomExceptionStatus.PRODUCT_INVALID));
//
//        log.info("Product={}", product);
//
//        return product;
//    }
}
