package entity;

import entity.enumertion.Grade;

import java.time.LocalDate;

public class Student {
    private String fatherName;
    private String motherName;
    private String birthCertificateNumber;
    private String nationalCode;
    private String studentNumber;
    private String spouseNationalCode;
    private LocalDate enterYear;
    private Grade grade;
    private Boolean dormitoryResident;
    private University university;
    private HouseAddress houseAddress;
}
