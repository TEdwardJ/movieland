CREATE OR REPLACE VIEW movie.v_all_movie_countries_ui AS
  SELECT cntr.cntr_id,
         cntr.cntr_name,
         mc.m_id
  FROM movie.country cntr
    JOIN movie.movie_country mc ON cntr.cntr_id = mc.cntr_id;