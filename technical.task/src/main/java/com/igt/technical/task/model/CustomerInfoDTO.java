package com.igt.technical.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.igt.technical.task.model.validation.ValidPassword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = CustomerInfoDTO.Builder.class)
public class CustomerInfoDTO {

    @ApiModelProperty(hidden = true)
    private final Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min=2, max=50)
    private final String username;

    @NotBlank(message = "Surname cannot be empty")
    @Size(min=2, max=50)
    private final String surname;

    @NotBlank(message = "Password must be valid")
    @ValidPassword
    private final String password;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not valid", regexp =
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private final String email;

    @NotBlank(message = "Country cannot be empty")
    private final String country;

    private final List<CustomerDebtCaseDTO> customerDebtCases;

    public CustomerInfoDTO(Builder b) {
        this.id = b.id;
        this.username = b.username;
        this.surname = b.surname;
        this.password = b.password;
        this.email = b.email;
        this.country = b.country;
        this.customerDebtCases = b.customerDebtCases;
    }

    public static Builder builder() {
        return new Builder();
    }

    /*
       Getters
     */

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public List<CustomerDebtCaseDTO> getCustomerDebtCases() {
        return customerDebtCases;
    }

    /*
        Equals, hashCode & toString
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerInfoDTO that = (CustomerInfoDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(country, that.country) &&
                Objects.equals(customerDebtCases, that.customerDebtCases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, surname, password, email, country, customerDebtCases);
    }

    @Override
    public String toString() {
        return "CustomerInfoDTO{" +
                "id=" + id +
                ", username=" + username +
                ", surname=" + surname +
                ", password=" + password +
                ", email=" + email +
                ", country=" + country +
                ", customerDebtCases=" + customerDebtCases +
                '}';
    }

    /*
        Builder
     */

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private Long id;
        private String username;
        private String surname;
        private String password;
        private String email;
        private String country;
        private List<CustomerDebtCaseDTO> customerDebtCases = new ArrayList<>();

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder customerDebtCases(List<CustomerDebtCaseDTO> customerDebtCases) {
            this.customerDebtCases = customerDebtCases;
            return this;
        }

        public CustomerInfoDTO build() {
            return new CustomerInfoDTO(this);
        }
    }
}
