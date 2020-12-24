package com.example.project.repositories.suboptions;

import com.example.project.model.suboptions.SubOption;
import com.example.project.repositories.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubOptionRepository extends EntityRepository<SubOption, Long> {

    @Query(value = "SELECT * FROM #{#entityName} t WHERE t.option_id = ?1", nativeQuery = true)
    List<SubOption> findAllByOptionId(Long id);
}
