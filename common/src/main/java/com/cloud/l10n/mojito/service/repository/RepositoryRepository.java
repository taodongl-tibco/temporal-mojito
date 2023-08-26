package com.cloud.l10n.mojito.service.repository;

import com.cloud.l10n.mojito.entity.Repository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryRepository extends JpaRepository<Repository, Long>, JpaSpecificationExecutor<Repository> {
    Repository findByName(@Param("name") String name);

    List<Repository> findByDeletedFalseOrderByNameAsc();

    @EntityGraph(value = "Repository.statistics", type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Repository> findAll(Specification<Repository> s, Sort sort);


}
