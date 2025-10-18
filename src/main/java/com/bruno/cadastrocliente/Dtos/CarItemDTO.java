package com.bruno.cadastrocliente.Dtos;

public class CarItemDTO {
    private Long productId;
    private Integer quantity;

    // getters e setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
