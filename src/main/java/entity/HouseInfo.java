package entity;

import base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "houseinfo")
public class HouseInfo extends BaseEntity<Long> {
    @NotNull(message = "expire date cannot be null")
    @Size(min = 12, max = 12, message = "Rent number must have 12 digits")
    private String rentNumber;
    @NotNull(message = "expire date cannot be null")
    private String houseAddress;
    @OneToOne(mappedBy = "houseInfo")
    private Student student;

    public HouseInfo(String rentNumber, String houseAddress, Student student) {
        this.rentNumber = rentNumber;
        this.houseAddress = houseAddress;
        this.student = student;
    }

    public HouseInfo(String rentNumber, String houseAddress) {
        this.rentNumber = rentNumber;
        this.houseAddress = houseAddress;
    }

    public HouseInfo() {
    }

    public String getRentNumber() {
        return rentNumber;
    }

    public void setRentNumber(String rentNumber) {
        this.rentNumber = rentNumber;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "HouseInfo{" +
                "id=" + getId() +
                ", rentNumber='" + rentNumber + '\'' +
                ", houseAddress='" + houseAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HouseInfo houseInfo = (HouseInfo) o;
        return Objects.equals(rentNumber, houseInfo.rentNumber) && Objects.equals(houseAddress, houseInfo.houseAddress) && Objects.equals(student, houseInfo.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentNumber, houseAddress, student);
    }
}
