package com.cloud.l10n.mojito.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;

import java.time.OffsetDateTime;

/**
 * @author garion
 */
@Entity
@Table(
        name = "tm_text_unit_statistic",
        indexes = {
                @Index(
                        name = "UK__TM_TEXT_UNIT_STATISTIC__BRANCH_ID",
                        columnList = "tm_text_unit_id",
                        unique = true),
        })
@BatchSize(size = 1000)
public class TMTextUnitStatistic extends AuditableEntity {

    @OneToOne(optional = false)
    @JoinColumn(
            name = "tm_text_unit_id",
            foreignKey = @ForeignKey(name = "FK__TM_TEXT_UNIT_STATISTIC__BRANCH_ID"))
    private TMTextUnit tmTextUnit;

    @Column(name = "last_day_usage_count")
    private Double lastDayUsageCount = 0d;

    @Column(name = "last_period_usage_count")
    private Double lastPeriodUsageCount = 0d;

    @Column(name = "last_seen_date")
    private OffsetDateTime lastSeenDate;

    public TMTextUnit getTMTextUnit() {
        return tmTextUnit;
    }

    public void setTMTextUnit(TMTextUnit tmTextUnit) {
        this.tmTextUnit = tmTextUnit;
    }

    public Double getLastDayUsageCount() {
        return lastDayUsageCount;
    }

    public void setLastDayUsageCount(Double lastDayUsageCount) {
        this.lastDayUsageCount = lastDayUsageCount;
    }

    public Double getLastPeriodUsageCount() {
        return lastPeriodUsageCount;
    }

    public void setLastPeriodUsageCount(Double lastPeriodUsageCount) {
        this.lastPeriodUsageCount = lastPeriodUsageCount;
    }

    public OffsetDateTime getLastSeenDate() {
        return lastSeenDate;
    }

    public void setLastSeenDate(OffsetDateTime lastSeenDate) {
        this.lastSeenDate = lastSeenDate;
    }
}

