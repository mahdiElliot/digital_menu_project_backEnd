package com.example.project.repositories.location;

import com.example.project.model.location.Location;
import com.example.project.repositories.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends EntityRepository<Location, Long> {
}
