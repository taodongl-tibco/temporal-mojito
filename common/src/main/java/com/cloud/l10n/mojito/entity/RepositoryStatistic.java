package com.cloud.l10n.mojito.entity;

import com.cloud.l10n.mojito.entity.security.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedBy;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity that contains statistics (word count, translated count, etc) of a {@link Repository}
 *
 * @author jaurambault
 */
@Entity
@Table(name = "repository_statistic")
@BatchSize(size = 1000)
public class RepositoryStatistic extends AuditableEntity {

    @CreatedBy
    @ManyToOne
    @JoinColumn(
            name = BaseEntity.CreatedByUserColumnName,
            foreignKey = @ForeignKey(name = "FK__REPOSITORY_STATISTIC__USER__ID"))
    protected User createdByUser;
    /**
     * The number of used text units in the repository
     */
    private Long usedTextUnitCount = 0L;
    /**
     * The word count of used text units
     */
    private Long usedTextUnitWordCount = 0L;
    /**
     * The number of unused text units in the repository
     */
    private Long unusedTextUnitCount = 0L;
    /**
     * The word count of used text units
     */
    private Long unusedTextUnitWordCount = 0L;
    /**
     * The number of text unit for plural forms
     */
    private Long pluralTextUnitCount = 0L;
    /**
     * The number of words for plural forms
     */

    private Long pluralTextUnitWordCount = 0L;
    /**
     * The number of OOSLA text unit
     */

    private Long ooslaTextUnitCount = 0L;
    /**
     * The number of OOSLA words
     */
    private Long ooslaTextUnitWordCount = 0L;
    private OffsetDateTime ooslaCreatedBefore;
    /**
     * The number of text unit without comments
     */
    private Long uncommentedTextUnitCount = 0L;
    @OneToMany(mappedBy = "repositoryStatistic", fetch = FetchType.EAGER)
    @OrderBy(value = "locale.id")
    private Set<RepositoryLocaleStatistic> repositoryLocaleStatistics = new HashSet<>();

    public RepositoryStatistic() {
    }

    public RepositoryStatistic(
            Long usedTextUnitCount,
            Long usedTextUnitWordCount,
            Long unusedTextUnitCount,
            Long unusedTextUnitWordCount,
            Long uncommentedTextUnitCount,
            Long pluralTextUnitCount,
            Long pluralTextUnitWordCount) {

        this.usedTextUnitCount = usedTextUnitCount;
        this.usedTextUnitWordCount = usedTextUnitWordCount;
        this.unusedTextUnitCount = unusedTextUnitCount;
        this.unusedTextUnitWordCount = unusedTextUnitWordCount;
        this.uncommentedTextUnitCount = uncommentedTextUnitCount;
        this.pluralTextUnitCount = pluralTextUnitCount;
        this.pluralTextUnitWordCount = pluralTextUnitWordCount;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    /**
     * @return the usedTextUnitCount
     */
    public Long getUsedTextUnitCount() {
        return usedTextUnitCount;
    }

    /**
     * @param usedTextUnitCount the usedTextUnitCount to set
     */
    public void setUsedTextUnitCount(Long usedTextUnitCount) {
        this.usedTextUnitCount = usedTextUnitCount;
    }

    /**
     * @return the usedTextUnitWordCount
     */
    public Long getUsedTextUnitWordCount() {
        return usedTextUnitWordCount;
    }

    /**
     * @param usedTextUnitWordCount the usedTextUnitWordCount to set
     */
    public void setUsedTextUnitWordCount(Long usedTextUnitWordCount) {
        this.usedTextUnitWordCount = usedTextUnitWordCount;
    }

    /**
     * @return the unusedTextUnitCount
     */
    public Long getUnusedTextUnitCount() {
        return unusedTextUnitCount;
    }

    /**
     * @param unusedTextUnitCount the unusedTextUnitCount to set
     */
    public void setUnusedTextUnitCount(Long unusedTextUnitCount) {
        this.unusedTextUnitCount = unusedTextUnitCount;
    }

    /**
     * @return the unusedTextUnitWordCount
     */
    public Long getUnusedTextUnitWordCount() {
        return unusedTextUnitWordCount;
    }

    /**
     * @param unusedTextUnitWordCount the unusedTextUnitWordCount to set
     */
    public void setUnusedTextUnitWordCount(Long unusedTextUnitWordCount) {
        this.unusedTextUnitWordCount = unusedTextUnitWordCount;
    }

    public Long getPluralTextUnitCount() {
        return pluralTextUnitCount;
    }

    public void setPluralTextUnitCount(Long pluralTextUnitCount) {
        this.pluralTextUnitCount = pluralTextUnitCount;
    }

    public Long getPluralTextUnitWordCount() {
        return pluralTextUnitWordCount;
    }

    public void setPluralTextUnitWordCount(Long pluralTextUnitWordCount) {
        this.pluralTextUnitWordCount = pluralTextUnitWordCount;
    }

    public Long getOoslaTextUnitCount() {
        return ooslaTextUnitCount;
    }

    public void setOoslaTextUnitCount(Long ooslaTextUnitCount) {
        this.ooslaTextUnitCount = ooslaTextUnitCount;
    }

    public Long getOoslaTextUnitWordCount() {
        return ooslaTextUnitWordCount;
    }

    public void setOoslaTextUnitWordCount(Long ooslaTextUnitWordCount) {
        this.ooslaTextUnitWordCount = ooslaTextUnitWordCount;
    }

    public OffsetDateTime getOoslaCreatedBefore() {
        return ooslaCreatedBefore;
    }

    public void setOoslaCreatedBefore(OffsetDateTime ooslaCreatedBefore) {
        this.ooslaCreatedBefore = ooslaCreatedBefore;
    }

    /**
     * @return the repositoryLocaleStatistics
     */
    public Set<RepositoryLocaleStatistic> getRepositoryLocaleStatistics() {
        return repositoryLocaleStatistics;
    }

    /**
     * @param repositoryLocaleStatistics the repositoryLocaleStatistics to set
     */
    public void setRepositoryLocaleStatistics(
            Set<RepositoryLocaleStatistic> repositoryLocaleStatistics) {
        this.repositoryLocaleStatistics = repositoryLocaleStatistics;
    }

    /**
     * @return the uncommentedTextUnitCount
     */
    public Long getUncommentedTextUnitCount() {
        return uncommentedTextUnitCount;
    }

    /**
     * @param uncommentedTextUnitCount the uncommentedTextUnitCount to set
     */
    public void setUncommentedTextUnitCount(Long uncommentedTextUnitCount) {
        this.uncommentedTextUnitCount = uncommentedTextUnitCount;
    }
}
