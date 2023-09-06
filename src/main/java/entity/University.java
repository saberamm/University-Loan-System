package entity;

import base.entity.BaseEntity;
import entity.enumertion.City;
import entity.enumertion.UniversityType;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "university")
public class University extends BaseEntity<Long> {
    @NotNull(message = "universityName cannot be null")
    private String universityName;
    @NotNull(message = "universityType cannot be null")
    @Enumerated(EnumType.STRING)
    private UniversityType universityType;
    private Boolean daily;
    @Enumerated(EnumType.STRING)
    private City city;
    @OneToMany(mappedBy = "university")
    private List<Student> students;

    public University(String universityName, UniversityType universityType, Boolean daily, City city, List<Student> students) {
        this.universityName = universityName;
        this.universityType = universityType;
        this.daily = daily;
        this.city = city;
        this.students = students;
    }

    public University(String universityName, UniversityType universityType, Boolean daily, City city) {
        this.universityName = universityName;
        this.universityType = universityType;
        this.daily = daily;
        this.city = city;
    }

    public University(String universityName, UniversityType universityType, City city) {
        this.universityName = universityName;
        this.universityType = universityType;
        this.city = city;
    }

    public University() {
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public UniversityType getUniversityType() {
        return universityType;
    }

    public void setUniversityType(UniversityType universityType) {
        this.universityType = universityType;
    }

    public Boolean getDaily() {
        return daily;
    }

    public void setDaily(Boolean daily) {
        this.daily = daily;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + getId() +
                ", universityName='" + universityName + '\'' +
                ", universityType=" + universityType +
                ", daily=" + daily +
                ", city=" + city +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return Objects.equals(universityName, that.universityName) && universityType == that.universityType && Objects.equals(daily, that.daily) && city == that.city && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityName, universityType, daily, city, students);
    }
}
