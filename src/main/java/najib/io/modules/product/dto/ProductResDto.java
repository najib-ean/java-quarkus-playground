package najib.io.modules.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import najib.io.common.BaseResDto;

@JsonPropertyOrder({"id", "name", "qty", "created_at", "updated_at"})
public class ProductResDto extends BaseResDto {
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
