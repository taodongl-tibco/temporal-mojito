package com.cloud.l10n.mojito.workflow;

import com.cloud.l10n.mojito.businesslogic.RepositoryCreation;
import com.cloud.l10n.mojito.service.repository.RepositoryService;
import io.temporal.spring.boot.ActivityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ActivityImpl(taskQueues = "RepositoryTaskQueue")
public class RepositoryActivityImpl implements RepositoryActivity {
    private final RepositoryService repositoryService;

    @Autowired
    public RepositoryActivityImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Override
    public RepositoryCreation createRepository(String name, List<String> locales) {
        var xxx = repositoryService.createRepository(name, locales);
        return xxx;
    }
}
