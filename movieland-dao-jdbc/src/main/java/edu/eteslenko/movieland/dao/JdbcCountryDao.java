package edu.eteslenko.movieland.dao;

import edu.eteslenko.movieland.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCountryDao implements CountryDao {

    private static final CountryRowMapper ROW_MAPPER = new CountryRowMapper();

    private Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;

    private String countrySelectAllQuery;
    private String countrySelectByMovieIdQuery;

    @Override
    public List<Country> getAll() {
        logger.debug("Getting all countries from DB");
        List<Country> list =
                jdbcTemplate.query(countrySelectAllQuery, ROW_MAPPER);
        return list;
    }

    @Override
    public List<Country> getByMovieId(int id) {
        logger.debug("Getting all countries for Movie {} from DB", id);
        List<Country> list =
                jdbcTemplate.query(countrySelectByMovieIdQuery, ROW_MAPPER, id);

        return list;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setCountrySelectAllQuery(String countrySelectAllQuery) {
        this.countrySelectAllQuery = countrySelectAllQuery;
    }

    @Autowired
    public void setCountrySelectByMovieIdQuery(String countrySelectByMovieIdQuery) {
        this.countrySelectByMovieIdQuery = countrySelectByMovieIdQuery;
    }
}
