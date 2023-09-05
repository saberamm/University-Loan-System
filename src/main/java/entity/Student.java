package entity;

import entity.enumertion.Grade;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "student")
public class Student extends User{
    private String fatherName;
    private String motherName;
    private String birthCertificateNumber;
    private String nationalCode;
    private String studentNumber;
    private String spouseNationalCode;
    private LocalDate enterYear;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    private Boolean dormitoryResident;
    @ManyToOne(cascade = CascadeType.ALL)
    private University university;
    @OneToOne(cascade = CascadeType.ALL)
    private HouseInfo houseInfo;
    @OneToMany(mappedBy = "student")
    private List<Loan> loans;

    public Student(String fatherName, String motherName, String birthCertificateNumber, String nationalCode, String studentNumber, String spouseNationalCode, LocalDate enterYear, Grade grade, Boolean dormitoryResident, University university, HouseInfo houseInfo, List<Loan> loans) {
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.birthCertificateNumber = birthCertificateNumber;
        this.nationalCode = nationalCode;
        this.studentNumber = studentNumber;
        this.spouseNationalCode = spouseNationalCode;
        this.enterYear = enterYear;
        this.grade = grade;
        this.dormitoryResident = dormitoryResident;
        this.university = university;
        this.houseInfo = houseInfo;
        this.loans = loans;
    }

    public Student(String firstName, String lastName, String username, String password, LocalDate birthDate, String fatherName, String motherName, String birthCertificateNumber, String nationalCode, String studentNumber, String spouseNationalCode, LocalDate enterYear, Grade grade, Boolean dormitoryResident, University university, HouseInfo houseInfo, List<Loan> loans) {
        super(firstName, lastName, username, password, birthDate);
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.birthCertificateNumber = birthCertificateNumber;
        this.nationalCode = nationalCode;
        this.studentNumber = studentNumber;
        this.spouseNationalCode = spouseNationalCode;
        this.enterYear = enterYear;
        this.grade = grade;
        this.dormitoryResident = dormitoryResident;
        this.university = university;
        this.houseInfo = houseInfo;
        this.loans = loans;
    }

    public Student() {
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getBirthCertificateNumber() {
        return birthCertificateNumber;
    }

    public void setBirthCertificateNumber(String birthCertificateNumber) {
        this.birthCertificateNumber = birthCertificateNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getSpouseNationalCode() {
        return spouseNationalCode;
    }

    public void setSpouseNationalCode(String spouseNationalCode) {
        this.spouseNationalCode = spouseNationalCode;
    }

    public LocalDate getEnterYear() {
        return enterYear;
    }

    public void setEnterYear(LocalDate enterYear) {
        this.enterYear = enterYear;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Boolean getDormitoryResident() {
        return dormitoryResident;
    }

    public void setDormitoryResident(Boolean dormitoryResident) {
        this.dormitoryResident = dormitoryResident;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public HouseInfo getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(HouseInfo houseInfo) {
        this.houseInfo = houseInfo;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", birthCertificateNumber='" + birthCertificateNumber + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", spouseNationalCode='" + spouseNationalCode + '\'' +
                ", enterYear=" + enterYear +
                ", grade=" + grade +
                ", dormitoryResident=" + dormitoryResident +
                ", university=" + university +
                ", houseInfo=" + houseInfo +
                ", loans=" + loans +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", birthDate=" + getBirthDate() +
                '}';
    }
}
