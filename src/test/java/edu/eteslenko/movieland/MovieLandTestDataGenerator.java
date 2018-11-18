package edu.eteslenko.movieland;

import edu.eteslenko.movieland.entity.Genre;
import edu.eteslenko.movieland.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieLandTestDataGenerator{
    public List<Movie> getMovies() {
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = new Movie();
        movie1.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg");
        movie1.setRating(8.9);
        movie1.setDescription("test test");
        movie1.setReleaseYear(1994);
        movie1.setPrice(123.45);
        movie1.setTitle("Побег из Шоушенка");
        movie1.setTitleInternational("The Shawshank Redemption");
        movieList.add(movie1);
        Movie movie2 = new Movie();
        movie2.setId(1);
        movie2.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg");
        movie2.setRating(8.9);
        movie2.setDescription("test test2");
        movie2.setReleaseYear(1999);
        movie2.setPrice(134.67);
        movie2.setTitle("Зеленая миля");
        movie2.setTitleInternational("The Green Mile");
        movieList.add(movie2);
        return movieList;
    }

    public List<Genre> getGenres() {
        List<Genre> genreList = new ArrayList<>();

        Genre genre1 = new Genre(1, "драма");
        Genre genre2 = new Genre(2, "криминал");
        genreList.add(genre1);
        genreList.add(genre2);

        return genreList;
    }

    public List<Movie> getMoviesForRandomTest() {
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = new Movie();
        movie1.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg");
        movie1.setRating(8.9);
        movie1.setDescription("test test");
        movie1.setReleaseYear(1994);
        movie1.setPrice(123.45);
        movie1.setTitle("Побег из Шоушенка");
        movie1.setTitleInternational("The Shawshank Redemption");
        movieList.add(movie1);
        Movie movie2 = new Movie();
        movie2.setId(1);
        movie2.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg");
        movie2.setRating(8.9);
        movie2.setDescription("test test2");
        movie2.setReleaseYear(1999);
        movie2.setPrice(134.67);
        movie2.setTitle("Зеленая миля");
        movie2.setTitleInternational("The Green Mile");
        movieList.add(movie2);


        Movie movie3 = new Movie();
        movie1.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg");
        movie1.setRating(8.9);
        movie1.setDescription("test test");
        movie1.setReleaseYear(1992);
        movie1.setPrice(55.45);
        movie1.setTitle("Укрощение строптивого");
        movie1.setTitleInternational("Il bisbetico domato");
        movieList.add(movie3);
        Movie movie4 = new Movie();
        movie2.setId(1);
        movie2.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg");
        movie2.setRating(8.9);
        movie2.setDescription("test test2");
        movie2.setReleaseYear(2008);
        movie2.setPrice(45);
        movie2.setTitle("Джанго освобожденный");
        movie2.setTitleInternational("Django Unchained");
        movieList.add(movie4);
        return movieList;

    }
}
