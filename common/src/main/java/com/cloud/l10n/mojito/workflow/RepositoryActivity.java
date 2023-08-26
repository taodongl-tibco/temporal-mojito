package com.cloud.l10n.mojito.workflow;

import com.cloud.l10n.mojito.businesslogic.RepositoryCreation;
import io.temporal.activity.ActivityInterface;

import java.util.List;

@ActivityInterface
public interface RepositoryActivity {
    RepositoryCreation createRepository(String name, List<String> locales);
}