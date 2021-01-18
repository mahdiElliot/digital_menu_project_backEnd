package com.example.project.repositories.extra;

import com.example.project.model.extra.Extra;
import com.example.project.repositories.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraRepository extends EntityRepository<Extra, Long> {
    @Query(value = "SELECT * FROM #{#entityName} t WHERE t.business_id=?1", nativeQuery = true)
    Iterable<Extra> findExtrasByBusinessId(Long businessId);
}
