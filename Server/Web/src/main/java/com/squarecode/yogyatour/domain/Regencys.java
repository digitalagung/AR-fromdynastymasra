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
@Table(name = "YOGYATOUR_REGENCYS")
public class Regencys {

    @Id
    @GeneratedValue
    @Column(name = "REGENCY_ID")
    private Long id;
    @Column(name = "REGENCY_NO")
    @Size(min = 1)
    private Integer no;
    @Column(name = "REGENCY_NAME", nullable = false)
    private String name;
    @OneToMany(mappedBy = "regencys")
    private List<SubDistricts> subDistrictses;
    @ManyToOne
    @JoinColumn(name = "PROVINCE_ID", nullable = false)
    private Provinces provinces;

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

    public List<SubDistricts> getSubDistrictses() {
        return subDistrictses;
    }

    public void setSubDistrictses(List<SubDistricts> subDistrictses) {
        this.subDistrictses = subDistrictses;
    }

    public Provinces getProvinces() {
        return provinces;
    }

    public void setProvinces(Provinces provinces) {
        this.provinces = provinces;
    }
}
