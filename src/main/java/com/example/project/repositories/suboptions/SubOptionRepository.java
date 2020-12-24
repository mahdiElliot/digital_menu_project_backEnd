package com.example.project.repositories.suboptions;

import com.example.project.model.suboptions.SubOption;
import com.example.project.repositories.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubOptionRepository extends EntityRepository<SubOption, Long> {
}
