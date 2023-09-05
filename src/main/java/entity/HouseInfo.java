package entity;

import base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "houseinfo")
public class HouseInfo extends BaseEntity<Long> {
    private String rentNumber;
    private String houseAddress;
    @OneToOne(mappedBy = "houseInfo")
    private Student student;

    public HouseInfo(String rentNumber, String houseAddress, Student student) {
        this.rentNumber = rentNumber;
        this.houseAddress = houseAddress;
        this.student = student;
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
}
