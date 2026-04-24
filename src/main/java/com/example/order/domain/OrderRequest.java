
package com.example.order.domain;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class OrderRequest {
  @NotBlank private String sku;
  @Min(1) private int quantity;
  public String getSku() { return sku; }
  public void setSku(String sku) { this.sku = sku; }
  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) { this.quantity = quantity; }
}
