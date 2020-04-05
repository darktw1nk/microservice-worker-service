package com.winter.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Designer {
    private Long id;
    private Long workerId;

    public Designer(Long id, Long workerId) {
        this.id = id;
        this.workerId = workerId;
    }

    public Designer() {
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
