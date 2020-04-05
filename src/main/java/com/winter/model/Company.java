package com.winter.model;


import io.quarkus.runtime.annotations.RegisterForReflection;

import java.math.BigDecimal;
import java.util.List;

@RegisterForReflection
public class Company {
    private Long id;
    private String name;
    private BigDecimal money;
    private Long currentProjectId;
    private Long userId;
    List<Marketer> marketers;
    List<Programmer> programmers;
    List<Designer> designers;

    public Company(Long id, String name, BigDecimal money, Long currentProjectId, Long userId, List<Marketer> marketers, List<Programmer> programmers, List<Designer> designers) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.currentProjectId = currentProjectId;
        this.userId = userId;
        this.marketers = marketers;
        this.programmers = programmers;
        this.designers = designers;
    }

    public Company() {
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Long getCurrentProjectId() {
        return currentProjectId;
    }

    public void setCurrentProjectId(Long currentProjectId) {
        this.currentProjectId = currentProjectId;
    }

    public List<Marketer> getMarketers() {
        return marketers;
    }

    public void setMarketers(List<Marketer> marketers) {
        this.marketers = marketers;
    }

    public List<Programmer> getProgrammers() {
        return programmers;
    }

    public void setProgrammers(List<Programmer> programmers) {
        this.programmers = programmers;
    }

    public List<Designer> getDesigners() {
        return designers;
    }

    public void setDesigners(List<Designer> designers) {
        this.designers = designers;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
