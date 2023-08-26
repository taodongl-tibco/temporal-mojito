package com.cloud.l10n.mojito.service.locale;

import com.cloud.l10n.mojito.entity.Locale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LocaleRepository
        extends JpaRepository<Locale, Long>, JpaSpecificationExecutor<Locale> {
}