package najib.io.modules.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

public class ProductReqDto {

    private final Set<String> presentFields = new HashSet<>();

    @JsonProperty("name")
    private String name;

    @JsonProperty("qty")
    private Integer quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        presentFields.add("name");
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isFieldPresent(String field) {
        return presentFields.contains(field);
    }
}
