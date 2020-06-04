package com.phoenixgb6.portfolio.universitycrm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "student_detail")
public class StudentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "{state.blank}")
    @Column(name = "state")
    private String state;

    @NotBlank(message = "{city.blank}")
    @Column(name = "city")
    private String city;

    @Pattern(regexp = "^[2-9][0-9]{9}", message = "{phoneNumber.invalid}")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull(message = "{birthDate.null}")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "dob")
    private Date dateOfBirth;

    @NotBlank(message = "{major.blank}")
    @Column(name = "major")
    private String major;

    @Column(name = "profile_pic")
    private String profilePic;

    @NotBlank(message = "{address.blank}")
    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy= "details",
            cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH, })
    private Student student;

    public StudentDetail() {
    }

    public StudentDetail(String state, String city, String phoneNumber, Date dateOfBirth, String major, String profilePic, String address) {
        this.state = state;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.major = major;
        this.profilePic = profilePic;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "StudentDetail{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", major='" + major + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", address='" + address + '\'' +
                ", student=" + student +
                '}';
    }
}