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

CREATE TABLE movie.country
(
    cntr_id numeric NOT NULL,
    cntr_name character varying(64) NOT NULL,
CONSTRAINT country_pk PRIMARY KEY (cntr_id)
);

CREATE TABLE movie.movie_country
(
    m_id numeric NOT NULL,
    cntr_id numeric NOT NULL,
    CONSTRAINT MOVIE_COUNTRY_PK PRIMARY KEY (m_id, cntr_id),
    CONSTRAINT COUNTRY_ID_FK FOREIGN KEY (cntr_id)
    REFERENCES movie.country (cntr_id),
CONSTRAINT MOVIE_ID_FK FOREIGN KEY (m_id)
REFERENCES movie.movie (m_id)
);

CREATE TABLE movie_genre
(
    gnr_id numeric NOT NULL,
    m_id numeric NOT NULL,
    CONSTRAINT movie_genre_pk PRIMARY KEY (gnr_id, m_id)
);

CREATE TABLE movie.user
(
    usr_id numeric NOT NULL,
    usr_name character varying(32) NOT NULL,
    usr_email character varying(32),
    usr_password character(128) NOT NULL,
    usr_password_enc character(128) NOT NULL,
    usr_sole character varying(32),
    CONSTRAINT user_pkey PRIMARY KEY (usr_id)
);

CREATE TABLE movie.movie_review
(
    review_id numeric NOT NULL,
    usr_id numeric NOT NULL,
    message text NOT NULL,
    m_id numeric NOT NULL,
    review_date date NOT NULL DEFAULT CURRENT_DATE,
CONSTRAINT REVIEW_PK PRIMARY KEY (review_id),
CONSTRAINT M_ID_FK FOREIGN KEY (m_id)
REFERENCES movie.movie (m_id),
CONSTRAINT USR_ID_FK FOREIGN KEY (usr_id)
REFERENCES movie.user (usr_id)
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
