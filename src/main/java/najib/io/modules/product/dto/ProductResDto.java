package najib.io.modules.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import najib.io.common.BaseResDto;
import najib.io.modules.user.dto.UserResDto;

public class ProductResDto extends BaseResDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("user")
    private UserResDto user;

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

    public UserResDto getUser() {
        return user;
    }

    public void setUser(UserResDto user) {
        this.user = user;
    }
}
