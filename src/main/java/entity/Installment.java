package entity;

import base.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "installment")
public class Installment extends BaseEntity<Long> {
    private String installmentNumber;
    private LocalDate payTime;
    private Double installmentAmount;
    @NotNull(message = "isPayed cannot be null")
    private Boolean isPayed;
    @ManyToOne(cascade = CascadeType.ALL)
    private Loan loan;

    public Installment(String installmentNumber, LocalDate payTime, Double installmentAmount, Boolean isPayed, Loan loan) {
        this.installmentNumber = installmentNumber;
        this.payTime = payTime;
        this.installmentAmount = installmentAmount;
        this.isPayed = isPayed;
        this.loan = loan;
    }

    public Installment(String installmentNumber, LocalDate payTime, Double installmentAmount, Boolean isPayed) {
        this.installmentNumber = installmentNumber;
        this.payTime = payTime;
        this.installmentAmount = installmentAmount;
        this.isPayed = isPayed;
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

    public Double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(Double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public Boolean getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Boolean payed) {
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
                ", installmentAmount=" + String.format("%.1f", installmentAmount) +
                ", isPayed=" + isPayed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Installment that = (Installment) o;
        return Objects.equals(installmentNumber, that.installmentNumber) && Objects.equals(payTime, that.payTime) && Objects.equals(installmentAmount, that.installmentAmount) && Objects.equals(isPayed, that.isPayed) && Objects.equals(loan, that.loan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(installmentNumber, payTime, installmentAmount, isPayed, loan);
    }
}
