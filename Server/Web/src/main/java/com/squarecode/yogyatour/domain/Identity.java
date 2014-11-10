package com.squarecode.yogyatour.domain;

import com.squarecode.yogyatour.domain.enums.Gender;
import com.squarecode.yogyatour.domain.enums.Religions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
@Entity
@Table(name = "YOGYATOUR_IDENTITY")
public class Identity {

    @Id
    @GeneratedValue
    @Column(name = "IDENTITY_ID")
    private Long id;
    @Column(name = "IDENTITY_NO", nullable = false)
    private String no;
    @Column(name = "IDENTITY_NAME", nullable = false)
    private String name;
    @Column(name = "IDENTITY_BORN_PLACE", nullable = false)
    private String bornPlace;
    @Temporal(TemporalType.DATE)
    @Column(name = "IDENTITY_BORN_DATE", nullable = false)
    private Date date;
    @Column(name = "IDENTITY_GENDER", nullable = false)
    private Gender gender;
    @Column(name = "IDENTITY_ADDRESS", nullable = false)
    private String address;
    @OneToOne
    @JoinColumn(name = "IDENTITY_ORIGIN", nullable = false)
    private Country country;
    @Column(name = "IDENTITY_RELIGION", nullable = false)
    private Religions religions;
    @Column(name = "IDENTITY_JOB", nullable = false)
    private String job;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBornPlace() {
        return bornPlace;
    }

    public void setBornPlace(String bornPlace) {
        this.bornPlace = bornPlace;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Religions getReligions() {
        return religions;
    }

    public void setReligions(Religions religions) {
        this.religions = religions;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
