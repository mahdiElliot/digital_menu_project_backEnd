package com.example.project.repositories.zone;

import com.example.project.model.zone.Zone;
import com.example.project.repositories.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends EntityRepository<Zone, Long> {
}
