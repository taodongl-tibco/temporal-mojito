package com.cloud.l10n.mojito.workflow;

import com.cloud.l10n.mojito.businesslogic.RepositoryCreation;
import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;

import java.time.Duration;
import java.util.List;

@WorkflowImpl(taskQueues = "RepositoryTaskQueue")
public class RepositoryWorkflowImpl implements RepositoryWorkflow {
    private RepositoryActivity activity =
            Workflow.newActivityStub(
                    RepositoryActivity.class,
                    ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build());

    @Override
    public RepositoryCreation createRepository(String name, List<String> locales) {
        return activity.createRepository(name, locales);
    }
}
