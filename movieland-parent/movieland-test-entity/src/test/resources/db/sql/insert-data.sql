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

INSERT INTO movie.user(usr_id,usr_name,usr_email,usr_password) VALUES(1,'Рональд Рейнольдс','ronald.reynolds66@example.com','paco');
INSERT INTO movie.user(usr_id,usr_name,usr_email,usr_password) VALUES(2,'Дарлин Эдвардс','darlene.edwards15@example.com','bricks');
INSERT INTO movie.user(usr_id,usr_name,usr_email,usr_password) VALUES(3,'Габриэль Джексон','gabriel.jackson91@example.com','hjkl');
INSERT INTO movie.user(usr_id,usr_name,usr_email,usr_password) VALUES(4,'Дэрил Брайант','daryl.bryant94@example.com','exodus');
INSERT INTO movie.user(usr_id,usr_name,usr_email,usr_password) VALUES(5,'Нил Паркер','neil.parker43@example.com','878787');
INSERT INTO movie.user(usr_id,usr_name,usr_email,usr_password) VALUES(6,'Трэвис Райт','travis.wright36@example.com','smart');
INSERT INTO movie.user(usr_id,usr_name,usr_email,usr_password) VALUES(7,'Амелия Кэннеди','amelia.kennedy58@example.com','beaker');
INSERT INTO movie.user(usr_id,usr_name,usr_email,usr_password) VALUES(8,'Айда Дэвис','ida.davis80@example.com','pepsi1');
INSERT INTO movie.user(usr_id,usr_name,usr_email,usr_password) VALUES(9,'Джесси Паттерсон','jessie.patterson68@example.com','tommy');
INSERT INTO movie.user(usr_id,usr_name,usr_email,usr_password) VALUES(10,'Деннис Крейг','dennis.craig82@example.com','coldbeer');

INSERT INTO movie.country(cntr_id,cntr_name) VALUES(1,'США');
INSERT INTO movie.country(cntr_id,cntr_name) VALUES(2,'Франция');
INSERT INTO movie.country(cntr_id,cntr_name) VALUES(3,'Италия');
INSERT INTO movie.country(cntr_id,cntr_name) VALUES(4,'Германия');
INSERT INTO movie.country(cntr_id,cntr_name) VALUES(5,'Япония');
INSERT INTO movie.country(cntr_id,cntr_name) VALUES(6,'Великобритания');
INSERT INTO movie.country(cntr_id,cntr_name) VALUES(7,'Испания');

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
INSERT INTO movie.movie_genre(m_id,gnr_id) VALUES(2,2);
INSERT INTO movie.movie_genre(m_id,gnr_id) VALUES(2,3);

INSERT INTO movie.movie_poster(poster_id,m_id,picture_url) VALUES(0,(SELECT m_id FROM movie.movie m WHERE m.m_title='Побег из Шоушенка'),'https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg');
INSERT INTO movie.movie_poster(poster_id,m_id,picture_url) VALUES(1,(SELECT m_id FROM movie.movie m WHERE m.m_title='Зеленая миля'),'https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg');
INSERT INTO movie.movie_poster(poster_id,m_id,picture_url) VALUES(2,(SELECT m_id FROM movie.movie m WHERE m.m_title='Унесённые призраками'),'https://images-na.ssl-images-amazon.com/images/M/MV5BOGJjNzZmMmUtMjljNC00ZjU5LWJiODQtZmEzZTU0MjBlNzgxL2ltYWdlXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1._SY209_CR0,0,140,209_.jpg');
INSERT INTO movie.movie_poster(poster_id,m_id,picture_url) VALUES(3,(SELECT m_id FROM movie.movie m WHERE m.m_title='Титаник'),'https://images-na.ssl-images-amazon.com/images/M/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1._SY209_CR0,0,140,209_.jpg');
INSERT INTO movie.movie_poster(poster_id,m_id,picture_url) VALUES(4,(SELECT m_id FROM movie.movie m WHERE m.m_title='Пролетая над гнездом кукушки'),'https://images-na.ssl-images-amazon.com/images/M/MV5BZjA0OWVhOTAtYWQxNi00YzNhLWI4ZjYtNjFjZTEyYjJlNDVlL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1._SY209_CR0,0,140,209_.jpg');

INSERT INTO movie.movie_review(review_id,m_id,usr_id,message) VALUES(1,(SELECT m_id FROM movie.movie as m WHERE m.m_title='Побег из Шоушенка'),(SELECT usr_id FROM movie.user as m WHERE m.usr_name='Дарлин Эдвардс'),'Гениальное кино! Смотришь и думаешь «Так не бывает!», но позже понимаешь, что только так и должно быть. Начинаешь заново осмысливать значение фразы, которую постоянно используешь в своей жизни, «Надежда умирает последней». Ведь если ты не надеешься, то все в твоей жизни гаснет, не остается смысла. Фильм наполнен бесконечным числом правильных афоризмов. Я уверена, что буду пересматривать его сотни раз.');
INSERT INTO movie.movie_review(review_id,m_id,usr_id,message) VALUES(2,(SELECT m_id FROM movie.movie as m WHERE m.m_title='Побег из Шоушенка'),(SELECT usr_id FROM movie.user as m WHERE m.usr_name='Габриэль Джексон'),'Кино это является, безусловно, «со знаком качества». Что же до первого места в рейтинге, то, думаю, здесь имело место быть выставление «десяточек» от большинства зрителей вкупе с раздутыми восторженными откликами кинокритиков. Фильм атмосферный. Он драматичный. И, конечно, заслуживает того, чтобы находиться довольно высоко в мировом кинематографе.');
INSERT INTO movie.movie_review(review_id,m_id,usr_id,message) VALUES(3,(SELECT m_id FROM movie.movie as m WHERE m.m_title='Зеленая миля'),(SELECT usr_id FROM movie.user as m WHERE m.usr_name='Рональд Рейнольдс'),'Перестал удивляться тому, что этот фильм занимает сплошь первые места во всевозможных кино рейтингах. Особенно я люблю когда при экранизации литературного произведение из него в силу специфики кинематографа исчезает ирония и появляется некий сверхреализм, обязанный удерживать зрителя у экрана каждую отдельно взятую секунду.');
INSERT INTO movie.movie_review(review_id,m_id,usr_id,message) VALUES(4,(SELECT m_id FROM movie.movie as m WHERE m.m_title='Унесённые призраками'),(SELECT usr_id FROM movie.user as m WHERE m.usr_name='Деннис Крейг'),'Необыкновенно позитивный фильм. Его можно пересматривать много раз для поднятия настроения, находя много смешных, незаметных на первый взгляд моментов.');
INSERT INTO movie.movie_review(review_id,m_id,usr_id,message) VALUES(5,(SELECT m_id FROM movie.movie as m WHERE m.m_title='Титаник'),(SELECT usr_id FROM movie.user as m WHERE m.usr_name='Джесси Паттерсон'),'В итоге мы имеем отличный представитель своего жанра, который прошёл проверку временем и до сих пор отлично смотрится, несмотря на классический сюжет');
INSERT INTO movie.movie_review(review_id,m_id,usr_id,message) VALUES(6,(SELECT m_id FROM movie.movie as m WHERE m.m_title='Пролетая над гнездом кукушки'),(SELECT usr_id FROM movie.user as m WHERE m.usr_name='Деннис Крейг'),'Скажу только одно — как я жалею, что не посмотрела его раньше!');


INSERT INTO movie.movie_country(m_id,cntr_id) VALUES((SELECT m_id FROM movie.movie as m WHERE 'Побег из Шоушенка/The Shawshank Redemption' like '%'||m.m_title||'%'),(SELECT cntr_id FROM movie.country m WHERE m.cntr_name='США'));
INSERT INTO movie.movie_country(m_id,cntr_id) VALUES((SELECT m_id FROM movie.movie as m WHERE 'Зеленая миля/The Green Mile' like '%'||m.m_title||'%'),(SELECT cntr_id FROM movie.country m WHERE m.cntr_name='США'));
INSERT INTO movie.movie_country(m_id,cntr_id) VALUES((SELECT m_id FROM movie.movie as m WHERE 'Унесённые призраками/Sen to Chihiro no kamikakushi' like '%'||m.m_title||'%'),(SELECT cntr_id FROM movie.country m WHERE m.cntr_name='Япония'));
INSERT INTO movie.movie_country(m_id,cntr_id) VALUES((SELECT m_id FROM movie.movie as m WHERE 'Титаник/Titanic' like '%'||m.m_title||'%'),(SELECT cntr_id FROM movie.country m WHERE m.cntr_name='США'));
INSERT INTO movie.movie_country(m_id,cntr_id) VALUES((SELECT m_id FROM movie.movie as m WHERE 'Пролетая над гнездом кукушки/One Flew Over the Cuckoo''s Nest' like '%'||m.m_title||'%'),(SELECT cntr_id FROM movie.country m WHERE m.cntr_name='США'));
