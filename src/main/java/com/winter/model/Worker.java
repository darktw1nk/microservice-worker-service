package com.winter.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "workers")
@RegisterForReflection
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long type;
    private Long design;
    private Long programming;
    private Long marketing;
    private BigDecimal salary;
    private Long companyId;

    public Worker() {
    }

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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getDesign() {
        return design;
    }

    public void setDesign(Long design) {
        this.design = design;
    }

    public Long getProgramming() {
        return programming;
    }

    public void setProgramming(Long programming) {
        this.programming = programming;
    }

    public Long getMarketing() {
        return marketing;
    }

    public void setMarketing(Long marketing) {
        this.marketing = marketing;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", design=" + design +
                ", programming=" + programming +
                ", marketing=" + marketing +
                ", salary=" + salary +
                ", companyId=" + companyId +
                '}';
    }
}
