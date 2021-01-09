package com.example.project.repositories.business;

import com.example.project.model.business.Business;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalBusinessRepository {
    Business update(Business business);
}
