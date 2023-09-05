package entity;

import base.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "installment")
public class Installment extends BaseEntity<Long> {
    private String installmentNumber;
    private LocalDate payTime;
    private Long installmentAmount;
    private Boolean isPayed;
    @ManyToOne(cascade = CascadeType.ALL)
    private Loan loan;

    public Installment(String installmentNumber, LocalDate payTime, Long installmentAmount, Boolean isPayed, Loan loan) {
        this.installmentNumber = installmentNumber;
        this.payTime = payTime;
        this.installmentAmount = installmentAmount;
        this.isPayed = isPayed;
        this.loan = loan;
    }

    public Installment() {
    }

    public String getInstallmentNumber() {
        return installmentNumber;
    }

    public void setInstallmentNumber(String installmentNumber) {
        this.installmentNumber = installmentNumber;
    }

    public LocalDate getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDate payTime) {
        this.payTime = payTime;
    }

    public Long getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(Long installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public Boolean getPayed() {
        return isPayed;
    }

    public void setPayed(Boolean payed) {
        isPayed = payed;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "Installment{" +
                "id=" + getId() +
                ", installmentNumber='" + installmentNumber + '\'' +
                ", payTime=" + payTime +
                ", installmentAmount=" + installmentAmount +
                ", isPayed=" + isPayed +
                ", payed=" + getPayed() +
                '}';
    }
}
