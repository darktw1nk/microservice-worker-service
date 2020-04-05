package com.winter.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Programmer {
    private Long id;
    private Long workerId;

    public Programmer(Long id, Long workerId) {
        this.id = id;
        this.workerId = workerId;
    }

    public Programmer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }
}
