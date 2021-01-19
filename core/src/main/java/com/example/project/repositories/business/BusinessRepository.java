package com.example.project.repositories.business;

import com.example.project.model.business.Business;
import com.example.project.repositories.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends EntityRepository<Business, Long>, AdditionalBusinessRepository {
//    @Query(value = "SELECT t.id, t.enabled, t.name, t.service_fee, t.tax, t.logo FROM #{#entityName} t FULL JOIN category on ", nativeQuery = true)
}
