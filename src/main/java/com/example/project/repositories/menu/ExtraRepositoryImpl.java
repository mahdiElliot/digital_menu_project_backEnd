package com.example.project.repositories.menu;

import com.example.project.model.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

public class ExtraRepositoryImpl implements ExtraRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String tableName;

    @Autowired
    public ExtraRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        tableName = Menu.class.getName().toLowerCase();
    }

    @Override
    public Optional<Menu> update(Long id, Menu menu) {
        String sql = "UPDATE " + tableName + " SET name = ?, pickup = ?, delivery = ?, enabled = ? WHERE id = ?";
        int rows = jdbcTemplate.update(
                sql,
                menu.getName(),
                menu.getPickup(),
                menu.getDelivery(),
                menu.getEnabled(),
                id
        );
        if (rows == 0) return Optional.empty();
        return Optional.of(menu);
    }
}
