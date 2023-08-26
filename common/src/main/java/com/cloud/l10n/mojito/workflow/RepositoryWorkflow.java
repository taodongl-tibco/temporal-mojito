package com.cloud.l10n.mojito.workflow;

import com.cloud.l10n.mojito.businesslogic.RepositoryCreation;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.List;

@WorkflowInterface
public interface RepositoryWorkflow {
    @WorkflowMethod
    RepositoryCreation createRepository(String name, List<String> locales);
}
