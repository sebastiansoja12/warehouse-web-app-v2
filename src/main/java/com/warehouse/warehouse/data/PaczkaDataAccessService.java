package com.warehouse.warehouse.data;

import com.warehouse.warehouse.model.Paczka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PaczkaDataAccessService {


    @Autowired
    private final JdbcTemplate jdbcTemplate;


    public PaczkaDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

 String selectAllPaczkas() {
        String sql = "" +
                "SELECT " +
                " * " +
                " FROM " +
                " paczka " +
                " where, " +
                " kodPaczki=  " +
                " ? ";
        return sql;

    }
}
