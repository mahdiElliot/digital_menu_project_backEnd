package com.example.project.repositories.option;

import com.example.project.model.option.Option;
import com.example.project.repositories.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends EntityRepository<Option, Long> {
}
