package com.cloud.l10n.mojito.entity;

import com.cloud.l10n.mojito.service.assetintegritychecker.integritychecker.IntegrityCheckerType;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

/**
 * Entity that assign an integrity check for a type of asset.
 *
 * @author wyau
 */
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Table(
        name = "asset_integrity_checker",
        indexes = {
                @Index(
                        name = "I__ASSET_INTEGRITY_CHECKER__REPOSITORY_ID__ASSET_EXTENSION",
                        columnList = "repository_id, asset_extension",
                        unique = false)
        })
public class AssetIntegrityChecker extends BaseEntity {

    @ManyToOne
    @JoinColumn(
            name = "repository_id",
            foreignKey = @ForeignKey(name = "FK__ASSET_INTEGRITY_CHECKER__REPOSITORY__ID"),
            nullable = false)
    private Repository repository;

    @Basic(optional = false)
    @Column(name = "asset_extension")
    private String assetExtension;

    @Basic(optional = false)
    @Column(name = "integrity_checker_type")
    @Enumerated(EnumType.STRING)
    private IntegrityCheckerType integrityCheckerType;

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public String getAssetExtension() {
        return assetExtension;
    }

    public void setAssetExtension(String assetExtension) {
        this.assetExtension = assetExtension;
    }

    public IntegrityCheckerType getIntegrityCheckerType() {
        return integrityCheckerType;
    }

    public void setIntegrityCheckerType(IntegrityCheckerType integrityCheckerType) {
        this.integrityCheckerType = integrityCheckerType;
    }
}