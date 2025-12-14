package najib.io.modules.user.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import najib.io.common.BaseResDto;

@JsonPropertyOrder(
        {
                "id",
                "firstName",
                "lastName",
                "address",
                "age",
                "gender",
                "created_at",
                "updated_at"
        })
public class UserResDto extends BaseResDto {
    private String firstName;
    private String lastName;
    private String address;
    private Integer age;
    private String gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
