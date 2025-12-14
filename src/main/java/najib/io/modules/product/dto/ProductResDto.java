package najib.io.modules.product.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import najib.io.common.BaseResDto;
import najib.io.modules.user.dto.UserResDto;

@JsonPropertyOrder({"id", "name", "qty", "user", "created_at", "updated_at"})
public class ProductResDto extends BaseResDto {
    private String name;
    private Integer quantity;
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
