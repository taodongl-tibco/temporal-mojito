package com.cloud.l10n.mojito.service.tm;

import com.cloud.l10n.mojito.entity.TM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TMRepository extends JpaRepository<TM, Long> {
}
