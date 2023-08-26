package com.cloud.l10n.mojito.service.repository.statistics;

import com.cloud.l10n.mojito.entity.Repository;
import com.cloud.l10n.mojito.entity.RepositoryLocaleStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface RepositoryLocaleStatisticRepository
        extends JpaRepository<RepositoryLocaleStatistic, Long>, JpaSpecificationExecutor<Repository> {

    RepositoryLocaleStatistic findByRepositoryStatisticIdAndLocale_Id(
            Long repositoryStatisticId, Long localeID);

    Set<RepositoryLocaleStatistic> findByRepositoryStatisticId(Long repositoryStatisticId);

    void deleteByRepositoryStatisticId(Long repositoryStatisticId);
}