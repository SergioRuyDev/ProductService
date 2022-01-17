package com.sergioruy.ProductService.command.api.commands;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateProductCommand {

    private String name;
    private BigDecimal price;
    private Integer quantity;
}
