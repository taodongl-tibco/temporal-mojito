package com.cloud.l10n.mojito.rest.repository;

import com.cloud.l10n.mojito.businesslogic.RepositoryCreation;
import com.cloud.l10n.mojito.workflow.RepositoryWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepositoryController {
    final private WorkflowClient client;

    @Autowired
    public RepositoryController(WorkflowClient client) {
        this.client = client;
    }

    @RequestMapping(value = "/api/repositories", method = RequestMethod.POST)
    public ResponseEntity<RepositoryCreation> createRepository(@Valid @RequestBody RepositoryCreation request) {
        var workflow =
                client.newWorkflowStub(
                        RepositoryWorkflow.class,
                        WorkflowOptions.newBuilder()
                                .setTaskQueue("RepositoryTaskQueue")
                                .setWorkflowId("create-repository-workflow")
                                .build());
        var ret = workflow.createRepository(request.name(), request.locales());
        return ResponseEntity.status(HttpStatus.CREATED).body(ret);
    }
}
