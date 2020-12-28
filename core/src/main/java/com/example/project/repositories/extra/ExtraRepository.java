package com.example.project.repositories.extra;

import com.example.project.model.extra.Extra;
import com.example.project.repositories.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraRepository extends EntityRepository<Extra, Long> {
}
