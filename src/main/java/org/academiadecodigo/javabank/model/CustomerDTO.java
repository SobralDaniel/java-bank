package org.academiadecodigo.javabank.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerDTO extends AbstractModel{
    @NotBlank(message = "First Name is mandatory")
    @Size(min = 3, max = 64, message = "Must contain between 3 and 64 characters")
    private String firstName;

    @Size(min = 3, max = 64, message = "Must contain between 3 and 64 characters")
    private String lastName;

    @Email(message = "Must be a valid e-mail format")
    private String email;

    @Pattern(regexp = "[+0-9]*", message = "Phone has invalid characters")
    @Size(min = 9, max = 16, message = "Must contain between 9 and 16 numbers ")
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
