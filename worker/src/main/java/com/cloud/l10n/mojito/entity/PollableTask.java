package com.cloud.l10n.mojito.entity;

import com.cloud.l10n.mojito.entity.security.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedBy;

import java.time.OffsetDateTime;
import java.util.Set;

/**
 * @author jaurambault
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(
        name = "pollable_task",
        indexes = {
                @Index(name = "I__POLLABLE_TASK__NAME", columnList = "name"),
                @Index(name = "I__POLLABLE_TASK__FINISHED_DATE", columnList = "finished_date")
        })
@BatchSize(size = 1000)
public class PollableTask extends AuditableEntity {

    @CreatedBy
    @ManyToOne
    @JoinColumn(
            name = BaseEntity.CreatedByUserColumnName,
            foreignKey = @ForeignKey(name = "FK__POLLABLE_TASK__USER__ID"))
    protected User createdByUser;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "finished_date")
    private OffsetDateTime finishedDate;
    @JsonIgnore
    @Column(name = "message", length = Integer.MAX_VALUE)
    private String message;
    @JsonIgnore
    @Column(name = "error_message", length = Integer.MAX_VALUE)
    private String errorMessage;
    @Column(name = "error_stacks", length = Integer.MAX_VALUE)
    private String errorStack;
    @Basic(optional = false)
    @Column(name = "expected_sub_task_number")
    private int expectedSubTaskNumber = 0;
    @OneToMany(mappedBy = "parentTask", fetch = FetchType.EAGER)
    @OrderBy("id")
    @BatchSize(size = 1000)
    private Set<PollableTask> subTasks;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(
            name = "parent_task_id",
            foreignKey = @ForeignKey(name = "FK__POLLABLE_TASK__POLLABLE_TASK__ID"))
    private PollableTask parentTask;
    @JsonIgnore
    @Column(name = "timeout")
    private Long timeout;

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(OffsetDateTime finishedDate) {
        this.finishedDate = finishedDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorStack() {
        return errorStack;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }

    public int getExpectedSubTaskNumber() {
        return expectedSubTaskNumber;
    }

    public void setExpectedSubTaskNumber(int expectedSubTaskNumber) {
        this.expectedSubTaskNumber = expectedSubTaskNumber;
    }

    public Set<PollableTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(Set<PollableTask> subTasks) {
        this.subTasks = subTasks;
    }

    public PollableTask getParentTask() {
        return parentTask;
    }

    public void setParentTask(PollableTask parentTask) {
        this.parentTask = parentTask;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }


    public String getMessageAsJson() {
        return message;
    }

    public String getErrorMessageAsJson() {
        return errorMessage;
    }

    /**
     * NOTE review, don't like to have business logic here... but overkill to move somewhere else
     *
     * <p>Indicates if this task and all its sub tasks are finished.
     *
     * @return {@code true} if this task and all its subtasks are finished else {@code false}
     */
    public boolean isAllFinished() {

        boolean currentTaskFinished = (getFinishedDate() != null);
        boolean allSubtasksFinished = true;

        // If parent task is finished, check all its subtasks
        if (currentTaskFinished) {

            if (getSubTasks() != null) {
                int numSubtasks = getSubTasks().size();

                // if not all expected subtasks have run => not finished
                if (numSubtasks < getExpectedSubTaskNumber()) {
                    allSubtasksFinished = false;
                } else {
                    // a task is finished only if all its subtasks are finished
                    for (PollableTask pollableTask : subTasks) {
                        if (!pollableTask.isAllFinished()) {
                            allSubtasksFinished = false;
                            break;
                        }
                    }
                }
            }
        }

        return currentTaskFinished && allSubtasksFinished;
    }
}
