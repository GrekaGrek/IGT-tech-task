package com.igt.technical.task.repository;

import com.igt.technical.task.domain.CustomerInfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoRepository extends CrudRepository<CustomerInfoEntity, Long> {
}
