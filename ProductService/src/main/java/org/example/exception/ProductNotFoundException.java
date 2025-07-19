package org.example.exception;

import java.util.function.Supplier;

public class ProductNotFoundException extends Exception implements Supplier<ProductNotFoundException> {
    public ProductNotFoundException(String message) {
        super(message);
    }

    @Override
    public ProductNotFoundException get() {
        return ProductNotFoundException.this;
    }
}
