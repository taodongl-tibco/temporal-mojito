package com.cloud.l10n.mojito.service.assetintegritychecker;

import com.cloud.l10n.mojito.entity.AssetIntegrityChecker;
import com.cloud.l10n.mojito.entity.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AssetIntegrityCheckerRepository
        extends JpaRepository<AssetIntegrityChecker, Long> {

    Set<AssetIntegrityChecker> findByRepository(Repository repository);

    Set<AssetIntegrityChecker> findByRepositoryAndAssetExtension(
            Repository repository, String assetExtension);

    void deleteByRepository(Repository repository);
}
