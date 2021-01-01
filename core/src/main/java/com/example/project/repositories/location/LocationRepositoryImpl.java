package com.example.project.repositories.location;

import com.example.project.model.location.Location;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationRepositoryImpl(JdbcTemplate jdbcTemplate) {
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

    private RowMapper<Location> rowMapper() {
        return (rs, rowNumber) ->
        {
            Geometry location = getGeom(rs);
            return new Location(
                    rs.getLong("id"),
                    rs.getInt("zipcode"),
                    rs.getInt("zoom"),
                    location
            );
        };
    }


    private Optional<Location> getLocation(String sql, Long id) {
        return jdbcTemplate.execute(sql, (PreparedStatementCallback<Optional<Location>>) ps -> {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Geometry location = getGeom(rs);
                return Optional.of(new Location(
                        rs.getLong("id"),
                        rs.getInt("zipcode"),
                        rs.getInt("zoom"),
                        location
                ));
            }
            return Optional.empty();
        });
    }

    @Override
    public Iterable<Location> findAll() {
        String sql = "SELECT id, zipcode, zoom, " +
                "ST_X(ST_Transform(geometry, 4326)) as lng, ST_Y(ST_Transform(geometry, 4326)) as lat FROM location";
        return jdbcTemplate.query(sql, rowMapper());
    }

    @Override
    public Optional<Location> findById(Long id) {
        String sql = "SELECT id, zipcode, zoom, " +
                "ST_X(ST_Transform(geometry, 4326)) as lng, ST_Y(ST_Transform(geometry, 4326)) as lat FROM location WHERE id = ?";
        return getLocation(sql, id);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM location WHERE id = ?", id);
    }

    @Override
    public Location save(Location location) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int effectedRows = jdbcTemplate.update(con -> {
            String sql = "INSERT INTO location (id, zipcode, zoom, geometry) " +
                    "VALUES (nextval('location_seq'), ?, ?, st_geometryfromtext(?,4326))";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, location.getZipcode());
            statement.setInt(2, location.getZoom());
            statement.setString(3, location.getLocation().toString());
            return statement;
        }, generatedKeyHolder);
        if (effectedRows == 0) return null;
        if (Objects.requireNonNull(generatedKeyHolder.getKeys()).size() > 1)
            location.setId((long) generatedKeyHolder.getKeys().get("id"));
        else
            location.setId(Objects.requireNonNull(generatedKeyHolder.getKey()).longValue());
        return location;
    }
}
