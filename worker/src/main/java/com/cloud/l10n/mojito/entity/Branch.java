package com.cloud.l10n.mojito.entity;

import com.cloud.l10n.mojito.entity.security.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;

import java.util.Set;

/**
 * Entity to manage branches of an {@link Asset}.
 *
 * <p>The branch name can be {@code null}. This will be used when no branch is provided to the
 * system. Note {@code null} complicates query when searching by name and {@link
 * com.box.l10n.mojito.service.branch.BranchService#findByNameAndRepository(String, Repository)}
 * should be used instead of {@link
 * com.box.l10n.mojito.service.branch.BranchRepository#findByNameAndRepository(String, Repository)}
 *
 * @author jeanaurambault
 */
@Entity
@Table(
        name = "branch",
        indexes = {
                @Index(
                        name = "UK__BRANCH__REPOSITORY_ID__PATH",
                        columnList = "repository_id, name",
                        unique = true),
                @Index(name = "I__BRANCH__DELETED", columnList = "deleted")
        })
public class Branch extends SettableAuditableEntity {

    @CreatedBy
    @ManyToOne
    @JoinColumn(
            name = BaseEntity.CreatedByUserColumnName,
            foreignKey = @ForeignKey(name = "FK__BRANCH__USER__ID"))
    protected User createdByUser;
    @ManyToOne(optional = false)
    @JoinColumn(name = "repository_id", foreignKey = @ForeignKey(name = "FK__BRANCH__REPOSITORY__ID"))
    Repository repository;
    @Column(name = "name")
    String name;
    @Column(name = "deleted", nullable = false)
    Boolean deleted = false;

//    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonDeserialize(as = LinkedHashSet.class)
//    @OrderBy("id")
//    Set<Screenshot> screenshots = new HashSet<>();

    @OneToOne(mappedBy = "branch", fetch = FetchType.LAZY)
    BranchStatistic branchStatistic;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "branch_notifiers",
            joinColumns = @JoinColumn(name = "branch_id"),
            foreignKey = @ForeignKey(name = "FK__BRANCH_NOTIFIERS__BRANCH__ID"))
    private Set<String> notifiers;

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

//    public Set<Screenshot> getScreenshots() {
//        return screenshots;
//    }

//    public void setScreenshots(Set<Screenshot> screenshots) {
//        this.screenshots = screenshots;
//    }

    public BranchStatistic getBranchStatistic() {
        return branchStatistic;
    }

    public void setBranchStatistic(BranchStatistic branchStatistic) {
        this.branchStatistic = branchStatistic;
    }

    public Set<String> getNotifiers() {
        return notifiers;
    }

    public void setNotifiers(Set<String> notifiers) {
        this.notifiers = notifiers;
    }
}
