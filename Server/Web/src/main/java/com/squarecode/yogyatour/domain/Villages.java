package com.squarecode.yogyatour.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
@Entity
@Table(name = "YOGYATOUR_VILLAGES")
public class Villages {

    @Id
    @GeneratedValue
    @Column(name = "VILLAGE_ID")
    private Long id;
    @Column(name = "VILLAGE_NO", unique = true)
    @Size(min = 1)
    private Integer no;
    @Column(name = "VILLAGE_NAME", nullable = false)
    private String name;
    @Column(name = "VILLAGE_POSTAL_CODE", nullable = false)
    private Integer postalCode;
    @ManyToOne
    @JoinColumn(name = "SUB_DISTRICT_ID", nullable = false)
    private SubDistricts subDistricts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public SubDistricts getSubDistricts() {
        return subDistricts;
    }

    public void setSubDistricts(SubDistricts subDistricts) {
        this.subDistricts = subDistricts;
    }
}
