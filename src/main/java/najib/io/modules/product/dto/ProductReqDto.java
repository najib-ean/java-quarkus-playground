package najib.io.modules.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import najib.io.utils.validation.OnCreate;
import najib.io.utils.validation.OnUpdate;

public class ProductReqDto {
    @JsonProperty("name")
    @NotBlank(message = "must not blank", groups = OnCreate.class)
    @Pattern(
            regexp = "^\\s*\\S.*$",
            message = "name must not be blank",
            groups = OnUpdate.class
    )
    @Pattern(
            regexp = "^[\\p{L} ]+$",
            message = "must contain only letters",
            groups = {OnCreate.class, OnUpdate.class}
    )
    private String name;

    @JsonProperty("quantity")
    @NotNull(message = "required")
    @Min(1)
    @Min(value = 1, groups = OnUpdate.class)
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
