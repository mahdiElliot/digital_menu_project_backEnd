package com.example.project.repositories.business;

import com.example.project.model.business.Business;
import com.example.project.model.zone.Zone;
import com.example.project.repositories.location.LocationRepository;
import com.example.project.repositories.zone.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashSet;
import java.util.Set;

public class AdditionalBusinessRepositoryImpl implements AdditionalBusinessRepository {
    private final JdbcTemplate jdbcTemplate;
    private final LocationRepository locationRepository;
    private final ZoneRepository zoneRepository;

    @Autowired
    public AdditionalBusinessRepositoryImpl(JdbcTemplate jdbcTemplate, LocationRepository locationRepository, ZoneRepository zoneRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.locationRepository = locationRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Business update(Business business) {
//        String sql = "UPDATE business SET name=?, service_fee=?, tax=?, logo=?, enabled=?";
//        if (business.getLocation() != null) {
//            sql += ", location_id=? WHERE id=?";
//            locationRepository.update(business.getLocation());
//            int r = jdbcTemplate.update(sql,
//                    business.getName(), business.getServiceFee(),
//                    business.getTax(), business.getLogo(),
//                    business.getEnabled(), business.getLocation().getId(), business.getId());
//            if (r == 0) return null;
//        } else {
//            sql += " WHERE id=?";
//            int r = jdbcTemplate.update(sql,
//                    business.getName(), business.getServiceFee(), business.getTax(), business.getLogo(),
//                    business.getEnabled(), business.getId());
//            if (r == 0) return null;
//        }
//
//        if (!business.getZones().isEmpty()) {
//            Set<Zone> zones = new HashSet<>();
//            for (Zone zone : business.getZones()) {
//                Zone zone1 = zoneRepository.save(zone);
//                if (!zone.getId().equals(zone1.getId())) {
//                    sql = "INSERT INTO business_zone VALUES (?, ?)";
//                    int r = jdbcTemplate.update(sql, business.getId(), zone1.getId());
//                    if (r == 0) return null;
//                }
//                zones.add(zone1);
//            }
//            business.setZones(zones);
//        }
//        return business;
        return null;
    }
}
