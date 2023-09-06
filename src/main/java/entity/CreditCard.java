package entity;

import base.entity.BaseEntity;
import entity.enumertion.BankName;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "creditcard")
public class CreditCard extends BaseEntity<Long> {
    @Enumerated(EnumType.STRING)
    @NotNull(message = "bankName cannot be null")
    private BankName bankName;
    @NotNull(message = "creditCardNumber cannot be null")
    @Size(min = 16, max = 16, message = "creditCardNumber must be 16 characters")
    private String creditCardNumber;
    @NotNull(message = "expire date cannot be null")
    private LocalDate expire;
    @Size(min = 4, max = 4, message = "cvv2 must be 4 characters")
    @NotNull(message = "cvv2 date cannot be null")
    private String cvv2;
    @OneToMany(mappedBy = "creditCard")
    private List<Loan> loans;

    public CreditCard(BankName bankName, String creditCardNumber, LocalDate expire, String cvv2, List<Loan> loans) {
        this.bankName = bankName;
        this.creditCardNumber = creditCardNumber;
        this.expire = expire;
        this.cvv2 = cvv2;
        this.loans = loans;
    }

    public CreditCard(BankName bankName, String creditCardNumber, LocalDate expire, String cvv2) {
        this.bankName = bankName;
        this.creditCardNumber = creditCardNumber;
        this.expire = expire;
        this.cvv2 = cvv2;
    }

    public CreditCard() {

    }

    public BankName getBankName() {
        return bankName;
    }

    public void setBankName(BankName bankName) {
        this.bankName = bankName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public LocalDate getExpire() {
        return expire;
    }

    public void setExpire(LocalDate expire) {
        this.expire = expire;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + getId() +
                ", bankName=" + bankName +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", expire=" + expire +
                ", cvv2='" + cvv2 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return bankName == that.bankName && Objects.equals(creditCardNumber, that.creditCardNumber) && Objects.equals(expire, that.expire) && Objects.equals(cvv2, that.cvv2) && Objects.equals(loans, that.loans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, creditCardNumber, expire, cvv2, loans);
    }
}

