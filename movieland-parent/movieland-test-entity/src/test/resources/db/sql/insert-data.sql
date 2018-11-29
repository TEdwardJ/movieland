INSERT INTO movie.movie(m_id,m_title,m_title_en,m_release_year,m_description,m_rating,m_price)
VALUES(0,'Побег из Шоушенка','The Shawshank Redemption',1994,'Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.',8.9,123.45);
INSERT INTO movie.movie(m_id,m_title,m_title_en,m_release_year,m_description,m_rating,m_price)
VALUES(1,'Зеленая миля','The Green Mile',1999,'Обвиненный в страшном преступлении, Джон Коффи оказывается в блоке смертников тюрьмы «Холодная гора». Вновь прибывший обладал поразительным ростом и был пугающе спокоен, что, впрочем, никак не влияло на отношение к нему начальника блока Пола Эджкомба, привыкшего исполнять приговор.',8.8,134.67);
INSERT INTO movie.movie(m_id,m_title,m_title_en,m_release_year,m_description,m_rating,m_price)
VALUES(2,'Унесённые призраками','Sen to Chihiro no kamikakushi',2001,'Маленькая Тихиро вместе с мамой и папой переезжают в новый дом. Заблудившись по дороге, они оказываются в странном пустынном городе, где их ждет великолепный пир. Родители с жадностью набрасываются на еду и к ужасу девочки превращаются в свиней, став пленниками злой колдуньи Юбабы, властительницы таинственного мира древних богов и могущественных духов.',8.6,145.9);
INSERT INTO movie.movie(m_id,m_title,m_title_en,m_release_year,m_description,m_rating,m_price)
VALUES(3,'Титаник','Titanic',1997,'Молодые влюбленные Джек и Роза находят друг друга в первом и последнем плавании «непотопляемого» Титаника. Они не могли знать, что шикарный лайнер столкнется с айсбергом в холодных водах Северной Атлантики, и их страстная любовь превратится в схватку со смертью…',7.9,150.0);
INSERT INTO movie.movie(m_id,m_title,m_title_en,m_release_year,m_description,m_rating,m_price)
VALUES(4,'Пролетая над гнездом кукушки','One Flew Over the Cuckoo''s Nest',1975,'Сымитировав помешательство в надежде избежать тюремного заключения, Рэндл Патрик МакМерфи попадает в психиатрическую клинику, где почти безраздельным хозяином является жестокосердная сестра Милдред Рэтчед. МакМерфи поражается тому, что прочие пациенты смирились с существующим положением вещей, а некоторые — даже сознательно пришли в лечебницу, прячась от пугающего внешнего мира. И решается на бунт. В одиночку.',8.7,180.0);


INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(1, 'драма');
INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(2, 'криминал');
INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(3, 'фэнтези');
INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(4, 'детектив');
INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(5, 'мелодрама');
INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(6, 'биография');
INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(7, 'комедия');
INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(8, 'фантастика');
INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(9, 'боевик');
INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(10, 'триллер');
INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(11, 'приключения');
INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(12, 'аниме');
INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(13, 'мультфильм');
INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(14, 'семейный');
INSERT INTO movie.genre(gnr_id, gnr_name) VALUES(15, 'вестерн');

INSERT INTO movie.movie_genre(m_id,gnr_id) VALUES(0,1);
INSERT INTO movie.movie_genre(m_id,gnr_id) VALUES(0,2);
INSERT INTO movie.movie_genre(m_id,gnr_id) VALUES(1,4);
INSERT INTO movie.movie_genre(m_id,gnr_id) VALUES(1,1);
INSERT INTO movie.movie_genre(m_id,gnr_id) VALUES(1,2);
INSERT INTO movie.movie_genre(m_id,gnr_id) VALUES(1,3);

INSERT INTO movie.movie_poster(poster_id,m_id,picture_url) VALUES(0,(SELECT m_id FROM movie.movie m WHERE m.m_title='Побег из Шоушенка'),'https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg');
INSERT INTO movie.movie_poster(poster_id,m_id,picture_url) VALUES(1,(SELECT m_id FROM movie.movie m WHERE m.m_title='Зеленая миля'),'https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg');
INSERT INTO movie.movie_poster(poster_id,m_id,picture_url) VALUES(2,(SELECT m_id FROM movie.movie m WHERE m.m_title='Унесённые призраками'),'https://images-na.ssl-images-amazon.com/images/M/MV5BOGJjNzZmMmUtMjljNC00ZjU5LWJiODQtZmEzZTU0MjBlNzgxL2ltYWdlXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1._SY209_CR0,0,140,209_.jpg');
INSERT INTO movie.movie_poster(poster_id,m_id,picture_url) VALUES(3,(SELECT m_id FROM movie.movie m WHERE m.m_title='Титаник'),'https://images-na.ssl-images-amazon.com/images/M/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1._SY209_CR0,0,140,209_.jpg');
INSERT INTO movie.movie_poster(poster_id,m_id,picture_url) VALUES(4,(SELECT m_id FROM movie.movie m WHERE m.m_title='Пролетая над гнездом кукушки'),'https://images-na.ssl-images-amazon.com/images/M/MV5BZjA0OWVhOTAtYWQxNi00YzNhLWI4ZjYtNjFjZTEyYjJlNDVlL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1._SY209_CR0,0,140,209_.jpg');
