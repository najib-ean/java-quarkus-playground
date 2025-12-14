package najib.io.modules.user.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import najib.io.utils.validation.OnCreate;
import najib.io.utils.validation.OnUpdate;
import najib.io.utils.validation.custom.AlphabetOnly;
import najib.io.utils.validation.custom.OptionalNotBlank;
import najib.io.utils.validation.custom.ValidGender;

public class UserReqDto {
    @NotBlank(message = "required and must not blank", groups = OnCreate.class)
    @OptionalNotBlank(groups = OnUpdate.class)
    @AlphabetOnly(groups = {OnCreate.class, OnUpdate.class})
    private String firstName;

    @NotBlank(message = "required and must not blank", groups = OnCreate.class)
    @OptionalNotBlank(groups = OnUpdate.class)
    @AlphabetOnly(groups = {OnCreate.class, OnUpdate.class})
    private String lastName;

    @NotBlank(message = "required and must not blank", groups = OnCreate.class)
    @OptionalNotBlank(groups = OnUpdate.class)
    private String address;

    @NotNull(message = "required", groups = OnCreate.class)
    @Min(value = 1, groups = {OnCreate.class, OnUpdate.class})
    private Integer age;

    @NotBlank(message = "required and must not blank", groups = OnCreate.class)
    @OptionalNotBlank(groups = OnUpdate.class)
    @ValidGender(groups = {OnCreate.class, OnUpdate.class})
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
