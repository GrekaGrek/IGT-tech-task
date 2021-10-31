package com.igt.technical.task.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = CustomerDebtCaseDTO.Builder.class)
public class CustomerDebtCaseDTO {

    @ApiModelProperty(hidden = true)
    private final Long id;

    @NotEmpty(message = "Amount cannot be empty")
    private final Double amount;

    @NotEmpty
    @Size(min = 3, max = 3, message = "Currency code must contain 3 letters")
    private final String currency;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate dueDate;

    public CustomerDebtCaseDTO(Builder b) {
        this.id = b.id;
        this.amount = b.amount;
        this.currency = b.currency;
        this.dueDate = b.dueDate;
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

    public Double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    /*
        Equals, hashCode & toString
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDebtCaseDTO that = (CustomerDebtCaseDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(dueDate, that.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, currency, dueDate);
    }

    @Override
    public String toString() {
        return "CustomerDebtCaseDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", currency=" + currency +
                ", dueDate=" + dueDate +
                '}';
    }

    /*
        Builder
     */

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private Long id;
        private Double amount;
        private String currency;
        @JsonDeserialize(using = LocalDateDeserializer.class)
        private LocalDate dueDate;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public CustomerDebtCaseDTO build() {
            return new CustomerDebtCaseDTO(this);
        }
    }
}
