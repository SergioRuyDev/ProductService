package com.sergioruy.ProductService.command.api.aggregate;

import com.sergioruy.ProductService.command.api.commands.CreateProductCommand;
import com.sergioruy.ProductService.command.api.events.ProductCreateEvent;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate(CreateProductCommand createProductCommand) {
        //Can perform all the validations
        ProductCreateEvent productCreateEvent =
                new ProductCreateEvent();

        BeanUtils.copyProperties(createProductCommand, productCreateEvent);

        AggregateLifecycle.apply(productCreateEvent);
    }

    public ProductAggregate() {}

    @EventSourcingHandler
    public void on(ProductCreateEvent productCreateEvent) {
        this.quantity = productCreateEvent.getQuantity();
        this.productId = productCreateEvent.getProductId();
        this.price = productCreateEvent.getPrice();
        this.name = productCreateEvent.getName();
    }
}
