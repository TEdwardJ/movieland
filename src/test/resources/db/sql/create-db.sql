create schema movie;
set SCHEMA movie;
CREATE TABLE movie (
    m_id           numeric NOT NULL,
    m_title        character varying(64) NOT NULL,
    m_title_en     character varying(64),
    m_price        double precision NOT NULL,
    m_release_year character(4),
    m_description  text,
    m_rating       double precision,
    CONSTRAINT movie_pk PRIMARY KEY (m_id)
);
CREATE TABLE movie_poster
(
    poster_id   numeric NOT NULL,
    picture_url character varying(256) NOT NULL,
    m_id        numeric,
    CONSTRAINT "POSTER_ID_PK" PRIMARY KEY (poster_id)
);

CREATE TABLE movie.genre
(
    gnr_id numeric NOT NULL,
    gnr_name character varying(64) NOT NULL,
    CONSTRAINT genre_pk PRIMARY KEY (gnr_id),
    CONSTRAINT genre_uk UNIQUE (gnr_name)
);

CREATE TABLE movie_genre
(
    gnr_id numeric NOT NULL,
    m_id numeric NOT NULL,
    CONSTRAINT movie_genre_pk PRIMARY KEY (gnr_id, m_id)
);

CREATE OR REPLACE VIEW v_movie_ui AS
 SELECT m.m_id,
    m.m_title,
    m.m_title_en,
    m.m_price,
    m.m_release_year,
    m.m_description,
    m.m_rating,
    mpos.picture_url
   FROM movie.movie m
     JOIN movie.movie_poster mpos ON mpos.m_id = m.m_id;

CREATE OR REPLACE VIEW v_movie_genre_ui AS
 SELECT m.m_id,
    m.m_title,
    m.m_title_en,
    m.m_price,
    m.m_release_year,
    m.m_description,
    m.m_rating,
    m.picture_url,
    g.gnr_id
   FROM movie.v_movie_ui m
     JOIN movie.movie_genre g ON m.m_id = g.m_id;
