package com.example.project.repositories.business;

import com.example.project.model.business.Business;
import com.example.project.repositories.GRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends GRepository<Business, Long> {
}
