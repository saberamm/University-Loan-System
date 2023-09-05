package entity;

import base.entity.BaseEntity;
import entity.enumertion.Grade;
import entity.enumertion.LoanType;
import entity.enumertion.Semester;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "loan")
public class Loan extends BaseEntity<Long> {
    private String loanNumber;
    @Enumerated(EnumType.STRING)
    private LoanType loanType;
    @Enumerated(EnumType.STRING)
    private Semester semester;
    private Long loanAmount;
    @ManyToOne(cascade = CascadeType.ALL)
    private CreditCard creditCard;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
    @OneToMany(mappedBy = "loan")
    private List<Installment> installments;

    public Loan(String loanNumber, LoanType loanType, Semester semester, Long loanAmount, CreditCard creditCard, Grade grade, Student student, List<Installment> installments) {
        this.loanNumber = loanNumber;
        this.loanType = loanType;
        this.semester = semester;
        this.loanAmount = loanAmount;
        this.creditCard = creditCard;
        this.grade = grade;
        this.student = student;
        this.installments = installments;
    }

    public Loan() {
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Long getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Long loanAmount) {
        this.loanAmount = loanAmount;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + getId() +
                ", loanNumber='" + loanNumber + '\'' +
                ", loanType=" + loanType +
                ", semester=" + semester +
                ", loanAmount=" + loanAmount +
                ", creditCard=" + creditCard +
                ", grade=" + grade +
                ", installments=" + installments +
                '}';
    }
}
