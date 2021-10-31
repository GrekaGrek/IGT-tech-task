package com.igt.technical.task.repository;

import com.igt.technical.task.domain.CustomerDebtCaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDebtCaseRepository extends CrudRepository<CustomerDebtCaseEntity, Long> {
}
