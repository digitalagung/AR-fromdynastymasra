package com.squarecode.yogyatour.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "YOGYATOUR_COUNTRY")
public class Country {

    @Id
    @GeneratedValue
    @Column(name = "COUNTRY_ID")
    private Long id;
    @Column(name = "COUNTRY_ISO_CODES", nullable = false)
    private String codes;
    @Column(name = "COUNTRY_CODE", unique = true)
    @Size(min = 1)
    private Integer code;
    @Column(name = "COUNTRY_NAME", nullable = false)
    private String name;
    @OneToMany(mappedBy = "country")
    private List<Provinces> provinces;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Provinces> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Provinces> provinces) {
        this.provinces = provinces;
    }
}
