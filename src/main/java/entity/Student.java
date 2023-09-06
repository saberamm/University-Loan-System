package entity;

import entity.enumertion.Grade;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student extends User {
    private String fatherName;
    private String motherName;
    @NotNull(message = "birthCertificateNumber cannot be null")
    private String birthCertificateNumber;
    @NotNull(message = "nationalCode cannot be null")
    private String nationalCode;
    @NotNull(message = "studentNumber cannot be null")
    private String studentNumber;
    private String spouseNationalCode;
    @NotNull(message = "enterYear cannot be null")
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

    public Student() {

    }

    public static class Builder {
        private Student student;

        public Builder() {
            student = new Student();
        }

        public Builder withUserProperties(String firstName, String lastName, String username, String password, LocalDate birthDate) {
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setUsername(username);
            student.setPassword(password);
            student.setBirthDate(birthDate);
            return this;
        }

        public Builder withFatherName(String fatherName) {
            student.fatherName = fatherName;
            return this;
        }

        public Builder withMotherName(String motherName) {
            student.motherName = motherName;
            return this;
        }

        public Builder withBirthCertificateNumber(String birthCertificateNumber) {
            student.birthCertificateNumber = birthCertificateNumber;
            return this;
        }

        public Builder withNationalCode(String nationalCode) {
            student.nationalCode = nationalCode;
            return this;
        }

        public Builder withStudentNumber(String studentNumber) {
            student.studentNumber = studentNumber;
            return this;
        }

        public Builder withSpouseNationalCode(String spouseNationalCode) {
            student.spouseNationalCode = spouseNationalCode;
            return this;
        }

        public Builder withEnterYear(LocalDate enterYear) {
            student.enterYear = enterYear;
            return this;
        }

        public Builder withGrade(Grade grade) {
            student.grade = grade;
            return this;
        }

        public Builder withDormitoryResident(Boolean dormitoryResident) {
            student.dormitoryResident = dormitoryResident;
            return this;
        }

        public Builder withUniversity(University university) {
            student.university = university;
            return this;
        }

        public Builder withHouseInfo(HouseInfo houseInfo) {
            student.houseInfo = houseInfo;
            return this;
        }

        public Builder withLoans(List<Loan> loans) {
            student.loans = loans;
            return this;
        }

        public Student build() {
            return student;
        }
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
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", birthDate=" + getBirthDate() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(fatherName, student.fatherName) && Objects.equals(motherName, student.motherName) && Objects.equals(birthCertificateNumber, student.birthCertificateNumber) && Objects.equals(nationalCode, student.nationalCode) && Objects.equals(studentNumber, student.studentNumber) && Objects.equals(spouseNationalCode, student.spouseNationalCode) && Objects.equals(enterYear, student.enterYear) && grade == student.grade && Objects.equals(dormitoryResident, student.dormitoryResident) && Objects.equals(university, student.university) && Objects.equals(houseInfo, student.houseInfo) && Objects.equals(loans, student.loans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fatherName, motherName, birthCertificateNumber, nationalCode, studentNumber, spouseNationalCode, enterYear, grade, dormitoryResident, university, houseInfo, loans);
    }
}
