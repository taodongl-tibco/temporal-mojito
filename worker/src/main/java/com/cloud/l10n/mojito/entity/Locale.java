package com.cloud.l10n.mojito.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.NaturalId;

import java.util.Objects;

/**
 * Entity that contains the Locales supported by the system globally. A locale is uniquely
 * identified by its BCP47 tag.
 *
 * @author jaurambault
 */
@Entity
@Table(
        name = "locale",
        indexes = {@Index(name = "UK__LOCALE__BCP47_TAG", columnList = "bcp47_tag", unique = true)})
// The batch size should be the size of the table, it's set to 2000 to allow
// for growth without needing further updates.
@BatchSize(size = 2000)
public class Locale extends BaseEntity {

    @Basic(optional = false)
    @Column(name = "bcp47_tag")
    @NaturalId
    private String bcp47Tag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locale locale = (Locale) o;
        return Objects.equals(bcp47Tag, locale.bcp47Tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bcp47Tag);
    }

    public String getBcp47Tag() {
        return bcp47Tag;
    }

    public void setBcp47Tag(String bcp47Tag) {
        this.bcp47Tag = bcp47Tag;
    }
}

