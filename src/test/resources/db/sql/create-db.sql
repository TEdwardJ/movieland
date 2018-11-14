create schema movie;
CREATE TABLE movie.movie (
  m_id               INT PRIMARY KEY,
  m_title            VARCHAR(64),
  m_title_en         VARCHAR(64),
  m_price            DOUBLE PRECISION,
  m_country_producer INT,
  m_release_year     INT,
  m_description      VARCHAR(256),
  m_rating           DOUBLE PRECISION,
  picture_url        VARCHAR(256)
);
drop table movie.movie;
CREATE TABLE movie.movie
(
    m_id numeric NOT NULL,
    m_title character varying(64) NOT NULL,
    m_title_en character varying(64),
    m_price double precision NOT NULL,
    m_country_producer numeric,
    m_release_year character(4),
    m_description text,
    m_rating double precision,
    CONSTRAINT movie_pk PRIMARY KEY (m_id)
);
CREATE TABLE movie.movie_poster
(
    poster_id numeric NOT NULL,
    picture_url character varying(256) NOT NULL,
    m_id numeric,
    CONSTRAINT "POSTER_ID_PK" PRIMARY KEY (poster_id)
);

CREATE OR REPLACE VIEW movie.v_movie_ui AS
 SELECT m.m_id,
    m.m_title,
    m.m_title_en,
    m.m_price,
    m.m_country_producer,
    m.m_release_year,
    m.m_description,
    m.m_rating,
    mpos.picture_url
   FROM movie.movie m
     JOIN movie.movie_poster mpos ON mpos.m_id = m.m_id;
