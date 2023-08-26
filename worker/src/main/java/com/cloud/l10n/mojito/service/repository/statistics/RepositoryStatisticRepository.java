package com.cloud.l10n.mojito.service.repository.statistics;

import com.cloud.l10n.mojito.entity.Repository;
import com.cloud.l10n.mojito.entity.RepositoryStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryStatisticRepository
        extends JpaRepository<RepositoryStatistic, Long>, JpaSpecificationExecutor<Repository> {
}