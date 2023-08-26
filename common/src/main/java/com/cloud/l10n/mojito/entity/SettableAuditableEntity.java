package com.cloud.l10n.mojito.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

import java.time.OffsetDateTime;

/**
 * Similar to {@link AuditableEntity} but allows to override the attributes.
 *
 * <p>Spring doesn't allow to set dates manually via the setter with the annotations and the
 * listener
 */
@MappedSuperclass
public abstract class SettableAuditableEntity extends BaseEntity {

    @Column(name = "created_date")
    protected OffsetDateTime createdDate;

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @PrePersist
    public void onPrePersist() {
        if (createdDate == null) {
            createdDate = OffsetDateTime.now();
        }
    }
}
