package com.example.project.repositories.business;

import com.example.project.model.business.Business;
import com.example.project.repositories.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends EntityRepository<Business, Long> {
}
