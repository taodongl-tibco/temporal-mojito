package com.cloud.l10n.mojito.businesslogic;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record RepositoryCreation(
        @NotEmpty(message = "Repository name is mandatory") String name,
        @NotEmpty(message = "Repository locales are mandatory") List<String> locales
) {
}
