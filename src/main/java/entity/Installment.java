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
    @NotNull(message = "installmentNumber cannot be null")
    private String installmentNumber;
    @NotNull(message = "payTime cannot be null")
    private LocalDate payTime;

    private LocalDate timePayed;
    @NotNull(message = "installmentAmount cannot be null")
    private Double installmentAmount;
    @NotNull(message = "isPayed cannot be null")
    private Boolean isPayed;
    @ManyToOne(cascade = CascadeType.ALL)
    private Loan loan;

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

    public LocalDate getTimePayed() {
        return timePayed;
    }

    public void setTimePayed(LocalDate timePayed) {
        this.timePayed = timePayed;
    }

    @Override
    public String toString() {
        return "Installment{" +
                "id=" + getId() +
                ", installmentNumber='" + installmentNumber + '\'' +
                ", payTime=" + payTime +
                ", installmentAmount=" + String.format("%.1f", installmentAmount) +
                ", isPayed=" + isPayed +
                ", time installment payed=" + timePayed +
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
