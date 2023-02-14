package com.example.backend.domain;

import lombok.Getter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter // do not create/use `setter` on Entity class
@MappedSuperclass
public class CommonEntity {
    @Basic
    @Column(nullable = true, length = 20)
    protected String createId;

    @Basic
    @Column(nullable = true, updatable=false)
    protected LocalDateTime createDtm;


    @Basic
    @Column( nullable = true, length = 20)
    protected String updateId;

    @Basic
    @Column(nullable = true)
    protected LocalDateTime updateDtm;

    @PrePersist
    protected void onPersist() {
        this.createDtm = this.updateDtm = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDtm = LocalDateTime.now();
    }
}
