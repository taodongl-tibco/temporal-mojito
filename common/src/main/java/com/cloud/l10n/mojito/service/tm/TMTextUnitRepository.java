package com.cloud.l10n.mojito.service.tm;

import com.cloud.l10n.mojito.entity.Asset;
import com.cloud.l10n.mojito.entity.TM;
import com.cloud.l10n.mojito.entity.TMTextUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

/**
 * @author jaurambault
 */
public interface TMTextUnitRepository extends JpaRepository<TMTextUnit, Long> {

    TMTextUnit findFirstByTmAndMd5(TM tm, String md5);

    TMTextUnit findFirstByAssetAndMd5(Asset asset, String md5);

    TMTextUnit findFirstByAssetIdAndName(Long assetId, String name);

    List<TMTextUnit> findByTm_id(Long tmId);

    List<TMTextUnit> findByIdIn(Collection<Long> ids);

    @Query(
            "select tu from TMTextUnit tu left outer join fetch tu.tmTextUnitStatistic where tu.id IN ?1")
    List<TMTextUnit> findByIdInAndEagerFetchStatistics(Collection<Long> ids);

    List<TMTextUnit> findByAsset(Asset asset);

    List<TMTextUnit> findByAssetId(Long assetId);

    @Query(
            "select new com.cloud.l10n.mojito.service.tm.TextUnitIdMd5DTO(tu.id, tu.md5) from TMTextUnit tu where tu.asset.id = ?1")
    List<TextUnitIdMd5DTO> getTextUnitIdMd5DTOByAssetId(Long assetId);

    @Query("select tu.id from TMTextUnit tu where tu.asset.id = ?1")
    List<Long> getTextUnitIdsByAssetId(Long assetId);

    TMTextUnit findByMd5AndTmIdAndAssetId(String contentMd5, Long tmId, Long assetId);
}

