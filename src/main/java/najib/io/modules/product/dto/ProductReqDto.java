package najib.io.modules.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductReqDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("qty")
    private Integer quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
