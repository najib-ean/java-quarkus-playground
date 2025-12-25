package najib.io.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import najib.io.common.BaseEntity;

@Entity(name = "roles")
public class RoleEntity extends BaseEntity {
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
