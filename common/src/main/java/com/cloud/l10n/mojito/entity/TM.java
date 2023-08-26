package com.cloud.l10n.mojito.entity;

import com.cloud.l10n.mojito.entity.security.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedBy;

import java.util.HashSet;
import java.util.Set;

@Entity
@BatchSize(size = 1000)
public class TM extends AuditableEntity {

    @CreatedBy
    @ManyToOne
    @JoinColumn(
            name = BaseEntity.CreatedByUserColumnName,
            foreignKey = @ForeignKey(name = "FK__TM__USER__ID"))
    protected User createdByUser;

    @OneToMany(mappedBy = "tm")
    Set<Repository> repositories = new HashSet<>();

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }
}
