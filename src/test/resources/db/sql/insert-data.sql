INSERT INTO movie.movie(m_id,m_title,m_title_en,m_release_year,m_description,m_rating,m_price) VALUES(0,'Побег из Шоушенка','The Shawshank Redemption',1994,'Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.',8.9,123.45);
INSERT INTO movie.movie(m_id,m_title,m_title_en,m_release_year,m_description,m_rating,m_price) VALUES(1,'Зеленая миля','The Green Mile',1999,'Обвиненный в страшном преступлении, Джон Коффи оказывается в блоке смертников тюрьмы «Холодная гора». Вновь прибывший обладал поразительным ростом и был пугающе спокоен, что, впрочем, никак не влияло на отношение к нему начальника блока Пола Эджкомба, привыкшего исполнять приговор.',8.9,134.67);


INSERT INTO movie.movie_poster(poster_id,m_id,picture_url) VALUES(0,(SELECT m_id FROM movie.movie as m WHERE m.m_title='Побег из Шоушенка'),'https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg');
INSERT INTO movie.movie_poster(poster_id,m_id,picture_url) VALUES(1,(SELECT m_id FROM movie.movie as m WHERE m.m_title='Зеленая миля'),'https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg');