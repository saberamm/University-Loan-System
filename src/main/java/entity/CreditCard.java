package entity;

import base.entity.BaseEntity;
import entity.enumertion.BankName;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "creditcard")
public class CreditCard extends BaseEntity<Long> {
    @Enumerated(EnumType.STRING)
    private BankName bankName;
    private String NationalCode;
    private String creditCardNumber;
    private LocalDate expire;
    private String cvv2;
    @OneToMany(mappedBy = "creditCard")
    private List<Loan> loans;

    public CreditCard(BankName bankName, String nationalCode, String creditCardNumber, LocalDate expire, String cvv2, List<Loan> loans) {
        this.bankName = bankName;
        NationalCode = nationalCode;
        this.creditCardNumber = creditCardNumber;
        this.expire = expire;
        this.cvv2 = cvv2;
        this.loans = loans;
    }

    public CreditCard() {

    }

    public BankName getBankName() {
        return bankName;
    }

    public void setBankName(BankName bankName) {
        this.bankName = bankName;
    }

    public String getNationalCode() {
        return NationalCode;
    }

    public void setNationalCode(String nationalCode) {
        NationalCode = nationalCode;
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
                ", NationalCode='" + NationalCode + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", expire=" + expire +
                ", cvv2='" + cvv2 + '\'' +
                ", nationalCode='" + getNationalCode() + '\'' +
                '}';
    }
}

