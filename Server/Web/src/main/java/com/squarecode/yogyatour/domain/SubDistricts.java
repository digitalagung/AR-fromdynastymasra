package com.squarecode.yogyatour.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "YOGYATOUR_SUB_DISTRICTS")
public class SubDistricts {

    @Id
    @GeneratedValue
    @Column(name = "SUB_DISTRICT_ID")
    private Long id;
    @Column(name = "SUB_DISTRICT_NO", unique = true)
    @Size(min = 1)
    private Integer no;
    @Column(name = "SUB_DISTRICT_NAME", nullable = false)
    private String name;
    @OneToMany(mappedBy = "subDistricts")
    private List<Villages> villageses;
    @ManyToOne
    @JoinColumn(name = "REGENCY_ID", nullable = false)
    private Regencys regencys;

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

    public List<Villages> getVillageses() {
        return villageses;
    }

    public void setVillageses(List<Villages> villageses) {
        this.villageses = villageses;
    }

    public Regencys getRegencys() {
        return regencys;
    }

    public void setRegencys(Regencys regencys) {
        this.regencys = regencys;
    }
}
