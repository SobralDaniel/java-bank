package org.academiadecodigo.javabank.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerDTO extends AbstractModel{

    @NotBlank(message = "first Name is mandatory")
    @Size(min = 3, max = 64)
    private String firstName;

    @NotBlank(message = "last name is mandatory")
    @Size(min = 3, max = 64)
    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "[+0-9]*", message = "Phone has invalid characters")
    @Size(min = 9, max = 16)
    private String phone;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
