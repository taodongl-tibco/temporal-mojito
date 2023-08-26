package com.cloud.l10n.mojito.entity;

import com.cloud.l10n.mojito.entity.security.user.User;
import com.cloud.l10n.mojito.service.repository.RepositoryService;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.annotation.CreatedBy;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity that describes a repository.
 *
 * @author aloison
 */
@Entity
@NamedEntityGraph(
        name = "Repository.statistics",
        attributeNodes = @NamedAttributeNode("repositoryStatistic"))
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Table(
        name = "repository",
        indexes = {@Index(name = "UK__REPOSITORY__NAME", columnList = "name", unique = true)})
public class Repository extends AuditableEntity {

    public static final int NAME_MAX_LENGTH = 255;
    @CreatedBy
    @ManyToOne
    @JoinColumn(
            name = BaseEntity.CreatedByUserColumnName,
            foreignKey = @ForeignKey(name = "FK__REPOSITORY__USER__ID"))
    protected User createdByUser;
    @ManyToOne
    @JoinColumn(
            name = "source_locale_id",
            foreignKey = @ForeignKey(name = "FK__REPOSITORY__LOCALE__ID"))
    Locale sourceLocale;

//    @Column(name = "drop_exporter_type")
//    @Enumerated(EnumType.STRING)
//    @JsonView(View.Repository.class)
//    private DropExporterType dropExporterType;
    @OneToMany(mappedBy = "repository", fetch = FetchType.EAGER)
    Set<RepositoryLocale> repositoryLocales = new HashSet<>();
    @OneToOne
    @JoinColumn(
            name = "repository_statistic_id",
            foreignKey = @ForeignKey(name = "FK__REPOSITORY__REPOSITORY_STATISTIC__ID"))
    RepositoryStatistic repositoryStatistic;
    @OneToMany(mappedBy = "repository", fetch = FetchType.EAGER)
    Set<AssetIntegrityChecker> assetIntegrityCheckers = new HashSet<>();
    @OneToMany(mappedBy = "repository", fetch = FetchType.LAZY)
    @NotAudited
    Set<Branch> branches = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "tm_id", foreignKey = @ForeignKey(name = "FK__REPOSITORY__TM__ID"))
    TM tm;
    @Basic(optional = false)
    @Column(name = "name", length = NAME_MAX_LENGTH)
    private String name;
    @Column(name = "description")
    private String description;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "manual_screenshot_run_id",
//            foreignKey = @ForeignKey(name = "FK__REPOSITORY__SCREENSHOT_RUN__ID"))
//    ScreenshotRun manualScreenshotRun;
    /**
     * To mark a Repository as deleted so it can be hidden in a Repositories page.
     */
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    /**
     * flag repository for SLA check by Default false
     */
    @Column(name = "checkSLA", nullable = false)
    private Boolean checkSLA;

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * NOTE Be careful when using this method because this returns the entire list of locales
     * including the root locale. Use {@link
     * RepositoryService#getRepositoryLocalesWithoutRootLocale(Repository)} to get the list without
     * the root locale.
     *
     * @return
     */
    public Set<RepositoryLocale> getRepositoryLocales() {
        return repositoryLocales;
    }

    public void setRepositoryLocales(Set<RepositoryLocale> repositoryLocales) {
        this.repositoryLocales = repositoryLocales;
    }

    public TM getTm() {
        return tm;
    }

    public void setTm(TM tm) {
        this.tm = tm;
    }

//    public DropExporterType getDropExporterType() {
//        return dropExporterType;
//    }

//    public void setDropExporterType(DropExporterType exporterType) {
//        this.dropExporterType = exporterType;
//    }

    public RepositoryStatistic getRepositoryStatistic() {
        return repositoryStatistic;
    }

    public void setRepositoryStatistic(RepositoryStatistic repositoryStatistic) {
        this.repositoryStatistic = repositoryStatistic;
    }

    public Set<AssetIntegrityChecker> getAssetIntegrityCheckers() {
        return assetIntegrityCheckers;
    }

    public void setAssetIntegrityCheckers(Set<AssetIntegrityChecker> assetIntegrityCheckers) {
        this.assetIntegrityCheckers = assetIntegrityCheckers;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getCheckSLA() {
        return checkSLA;
    }

    public void setCheckSLA(Boolean checkSLA) {
        this.checkSLA = checkSLA;
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

    public Locale getSourceLocale() {
        return sourceLocale;
    }

    public void setSourceLocale(Locale sourceLocale) {
        this.sourceLocale = sourceLocale;
    }

//    public ScreenshotRun getManualScreenshotRun() {
//        return manualScreenshotRun;
//    }
//
//    public void setManualScreenshotRun(ScreenshotRun manualScreenshotRun) {
//        this.manualScreenshotRun = manualScreenshotRun;
//    }
}
