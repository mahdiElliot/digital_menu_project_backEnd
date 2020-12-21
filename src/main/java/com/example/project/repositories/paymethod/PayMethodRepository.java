package com.example.project.repositories.paymethod;

import com.example.project.model.paymethod.PayMethod;
import com.example.project.repositories.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayMethodRepository extends EntityRepository<PayMethod, Long> {
}
