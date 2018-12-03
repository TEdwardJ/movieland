CREATE OR REPLACE VIEW movie.v_all_movie_genres_ui AS
  SELECT g.gnr_id,
         g.gnr_name,
         mg.m_id
  FROM movie.genre g
    JOIN movie.movie_genre mg ON g.gnr_id = mg.gnr_id;
