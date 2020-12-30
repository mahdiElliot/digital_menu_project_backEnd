package com.example.project.service.business;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.repositories.business.BusinessRepository;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusinessService implements IBusinessService {

    private final BusinessRepository businessRepository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BusinessService(BusinessRepository businessRepository, JdbcTemplate jdbcTemplate) {
        this.businessRepository = businessRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    private Geometry getGeom(ResultSet rs) throws SQLException {
        Geometry geometry = null;
        try {
            GeometryFactory fact = new GeometryFactory(new PrecisionModel());
            double lng = rs.getDouble("lng");
            double lat = rs.getDouble("lat");
            geometry = new WKTReader(fact).read("POINT (" + lng + " " + lat + ")");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return geometry;
    }

    private RowMapper<Business> rowMapper() {
        return (rs, rowNumber) ->
        {
            Geometry geometry = getGeom(rs);
            return new Business(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getDouble("service_fee"),
                    rs.getDouble("tax"),
                    rs.getString("logo"),
                    rs.getBoolean("enabled"),
                    geometry
            );
        };
    }

    @Override
    public List<BusinessDTO> findAll() {
        String sql = "SELECT id, name, service_fee, tax, logo, enabled," +
                " ST_X(ST_Transform(geometry, 3857)) as lng, ST_Y(ST_Transform(geometry, 3857)) as lat FROM business";
        List<Business> list = jdbcTemplate.query(sql, rowMapper());
        return list.stream().map(Business::convertToDTO).collect(Collectors.toList());
    }

    private Business getBusiness(String sql, Object object) {
        return jdbcTemplate.execute(sql, (PreparedStatementCallback<Business>) ps -> {
            ps.setObject(1, object);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Geometry location = getGeom(rs);
                return new Business(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getDouble("service_fee"),
                        rs.getDouble("tax"),
                        rs.getString("logo"),
                        rs.getBoolean("enabled"),
                        location
                );
            }
            return null;
        });
    }

    @Override
    public BusinessDTO findByName(String name) {
        if (name == null) return null;
        String sql = "SELECT id, name, service_fee, tax, logo, enabled," +
                " ST_X(ST_Transform(geometry, 4326)) as lng, ST_Y(ST_Transform(geometry, 4326)) as lat FROM business WHERE name = ?";
        Business business = getBusiness(sql, name);
        if (business == null) return null;
        return business.convertToDTO();
    }

    @Override
    public BusinessDTO findById(Long id) {
        if (id == null) return null;
        String sql = "SELECT id, name, service_fee, tax, logo, enabled," +
                " ST_X(ST_Transform(geometry, 4326)) as lng, ST_Y(ST_Transform(geometry, 4326)) as lat FROM business WHERE id = ?";
        Business business = getBusiness(sql, id);
        if (business == null) return null;
        return business.convertToDTO();
    }

    @Override
    public BusinessDTO delete(Long id) {
        if (id == null) return null;
        BusinessDTO businessDTO = findById(id);
        if (businessDTO == null) return null;
        businessRepository.deleteById(id);
        return businessDTO;
    }

    @Override
    public BusinessDTO save(Business business) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int effectedRows = jdbcTemplate.update(con -> {
            String sql = "INSERT INTO business (id, name, service_fee, tax, logo, enabled, geometry) " +
                    "VALUES (nextval('business_seq'), ?, ?, ?, ?, ?, st_geometryfromtext(?,4326))";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, business.getName());
            statement.setDouble(2, business.getServiceFee());
            statement.setDouble(3, business.getTax());
            statement.setString(4, business.getLogo());
            statement.setBoolean(5, business.getEnabled());
            statement.setString(6, business.getLocation().toString());
            return statement;
        }, generatedKeyHolder);
        if (effectedRows == 0) return new BusinessDTO();
        BusinessDTO businessDTO = business.convertToDTO();
        if (Objects.requireNonNull(generatedKeyHolder.getKeys()).size() > 1)
            businessDTO.setId((long) generatedKeyHolder.getKeys().get("id"));
        else
            businessDTO.setId(Objects.requireNonNull(generatedKeyHolder.getKey()).longValue());
        return businessDTO;
    }
}
