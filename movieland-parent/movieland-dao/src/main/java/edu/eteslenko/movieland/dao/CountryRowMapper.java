package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {

    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country(resultSet.getInt("cntr_id"), resultSet.getString("cntr_name"));
        return country;
    }
}
