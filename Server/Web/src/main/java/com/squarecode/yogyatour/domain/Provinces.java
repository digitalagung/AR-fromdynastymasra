package com.squarecode.yogyatour.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
@Entity
@Table(name = "YOGYATOUR_PROVINCES")
public class Provinces {

    @Id
    @GeneratedValue
    @Column(name = "PROVINCE_ID")
    private Long id;
    @Column(name = "PROVINCE_NO")
    @Size(min = 1)
    private Integer no;
    @Column(name = "PROVINCE_ISO_CODE", nullable = false)
    @Size(min = 2)
    private String isoCodes;
    @Column(name = "PROVINCE_NAME", nullable = false)
    private String name;
    @OneToMany(mappedBy = "provinces")
    private List<Regencys> regencyses;
    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    private Country country;

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

    public List<Regencys> getRegencyses() {
        return regencyses;
    }

    public void setRegencyses(List<Regencys> regencyses) {
        this.regencyses = regencyses;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getIsoCodes() {
        return isoCodes;
    }

    public void setIsoCodes(String isoCodes) {
        this.isoCodes = isoCodes;
    }
}
