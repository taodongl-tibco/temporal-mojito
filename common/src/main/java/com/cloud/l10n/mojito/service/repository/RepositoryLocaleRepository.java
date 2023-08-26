package com.cloud.l10n.mojito.service.repository;

import com.cloud.l10n.mojito.entity.Locale;
import com.cloud.l10n.mojito.entity.Repository;
import com.cloud.l10n.mojito.entity.RepositoryLocale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryLocaleRepository extends JpaRepository<RepositoryLocale, Long> {

    RepositoryLocale findByRepositoryAndLocale(Repository repository, Locale locale);

    RepositoryLocale findByRepositoryAndLocale_Bcp47Tag(Repository repostiory, String bcp47tag);

    RepositoryLocale findByRepositoryAndParentLocaleIsNull(Repository repository);

    Long deleteByRepositoryAndParentLocaleIsNotNull(Repository repository);

    RepositoryLocale findByRepositoryIdAndLocaleId(Long repositoryId, long localeId);
}
