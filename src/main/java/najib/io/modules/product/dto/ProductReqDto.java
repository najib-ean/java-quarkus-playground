package najib.io.modules.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import najib.io.utils.validation.OnCreate;
import najib.io.utils.validation.OnUpdate;
import najib.io.utils.validation.custom.AlphabetOnly;
import najib.io.utils.validation.custom.OptionalNotBlank;

public class ProductReqDto {
    @JsonProperty("name")
    @NotBlank(message = "required and must not blank", groups = OnCreate.class)
    @OptionalNotBlank(groups = OnUpdate.class)
    @AlphabetOnly(groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @JsonProperty("quantity")
    @NotNull(message = "required", groups = OnCreate.class)
    @Min(value = 1, groups = {OnCreate.class, OnUpdate.class})
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
