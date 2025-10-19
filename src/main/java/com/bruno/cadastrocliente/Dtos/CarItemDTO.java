package com.bruno.cadastrocliente.Dtos;

public class CarItemDTO {
    private Long productId;
    private Integer quantidade;

    // getters e setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}
