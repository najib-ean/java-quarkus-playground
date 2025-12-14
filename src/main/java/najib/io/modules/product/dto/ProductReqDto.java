package najib.io.modules.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import najib.io.utils.validation.OnCreate;
import najib.io.utils.validation.OnUpdate;
import najib.io.utils.validation.custom.AlphabetOnly;
import najib.io.utils.validation.custom.OptionalNotBlank;

public class ProductReqDto {
    @NotBlank(message = "required and must not blank", groups = OnCreate.class)
    @OptionalNotBlank(groups = OnUpdate.class)
    @AlphabetOnly(groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotNull(message = "required", groups = OnCreate.class)
    @Min(value = 1, groups = {OnCreate.class, OnUpdate.class})
    private Integer quantity;

    @NotNull(message = "required", groups = OnCreate.class)
    private Long userId;

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getUserId() {
        return userId;
    }
}
