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
@Table(name = "YOGYATOUR_SUB_CATEGORY")
public class SubCategory {

    @Id
    @GeneratedValue
    @Column(name = "SUB_CATEGORY_ID")
    private Long id;
    @Column(name = "SUB_CATEGORY_NAME", length = 50, nullable = false)
    @Size(max = 50, min = 1)
    private String name;
    @ManyToOne
    @JoinColumn(name = "MAIN_CATEGORY_ID", nullable = false)
    private MainCategory mainCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
