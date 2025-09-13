package najib.io.modules.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import najib.io.common.BaseEntity;

@Entity(name = "product")
public class ProductEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
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
