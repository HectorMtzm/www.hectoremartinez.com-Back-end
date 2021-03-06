package com.phoenixgb6.portfolio.universitycrm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Pattern(regexp = "^[2-9][0-9]{9}", message = "{phoneNumber.invalid}")
    @Column(name = "office_phone_number")
    private String phoneNumber;

    @NotBlank(message = "{office.blank}")
    @Column(name = "office")
    private String office;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "profile_pic")
    private String profilePic;

    @OneToOne(mappedBy= "details",
            cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH})
    private Instructor instructor;

    public InstructorDetail() {
    }

    public InstructorDetail(String office, String phoneNumber, String youtubeChannel,
                            String profilePic, Instructor instructor) {
        this.phoneNumber = phoneNumber;
        this.office = office;
        this.youtubeChannel = youtubeChannel;
        this.profilePic = profilePic;
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
