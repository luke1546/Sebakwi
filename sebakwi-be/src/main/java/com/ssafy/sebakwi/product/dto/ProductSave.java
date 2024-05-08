package com.ssafy.sebakwi.product.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
@Getter
@ToString
public class ProductSave {

    @NotNull(message = "이름이없으면안대요!!")
    private String name;

    @Max(value = 50)
    private int quantity;
}
