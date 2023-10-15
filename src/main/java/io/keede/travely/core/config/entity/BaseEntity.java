package io.keede.travely.core.config.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
* @author keede
* Created on 2023/10/15
*/
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "is_delete")
    private boolean isDelete;

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void remove() {
        this.isDelete = true;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now()
                .withNano(0);
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedAt = LocalDateTime.now()
                .withNano(0);
    }

}
