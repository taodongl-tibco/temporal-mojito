package com.cloud.l10n.mojito.rest.repository;

import com.cloud.l10n.mojito.businesslogic.ErrorResp;
import com.cloud.l10n.mojito.businesslogic.RepositoryCreation;
import com.cloud.l10n.mojito.entity.RepositoryLocale;
import com.cloud.l10n.mojito.service.locale.LocaleService;
import com.cloud.l10n.mojito.service.repository.RepositoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.stream.Collectors;

@RestController
public class RepositoryController {
    private final RepositoryService repositoryService;
    private final LocaleService localeService;

    @Autowired
    public RepositoryController(RepositoryService repositoryService, LocaleService localeService) {
        this.repositoryService = repositoryService;
        this.localeService = localeService;
    }

    @RequestMapping(value = "/api/repositories", method = RequestMethod.POST)
    public ResponseEntity<?> createRepository(@Valid @RequestBody RepositoryCreation request) {
        var locale = localeService.getDefaultLocale();
        var tags = localeService.findByBcp47Tags(request.locales()).stream().map(x -> {
            var repositoryLocale = new RepositoryLocale();
            repositoryLocale.setLocale(x);
            return repositoryLocale;
        }).collect(Collectors.toUnmodifiableSet());
        try {
            var xxx = repositoryService.createRepository(request.name(), "", locale, false, Collections.emptySet(), tags);
            return ResponseEntity.status(HttpStatus.CREATED).body(request);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResp.builder()
                    .add("Repository creation failed")
                    .add(e.getMessage())
                    .build());
        }
    }
}
