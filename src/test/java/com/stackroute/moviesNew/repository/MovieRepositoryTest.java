package com.stackroute.moviesNew.repository;


import com.stackroute.moviesNew.domain.Movies;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
//we will use db server
@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {



    @Autowired
    MovieRepository movieRepository;
    Movies movies;

    @Before
    public void setUp(){
        movies = new Movies();
        movies.setImdbId("tt214");
        movies.setMovieTitle("hello");
        movies.setComments("lov story");
        movies.setPostedUrl("https://bookmmyshow.com:hello");
        movies.setRating((float)4.2);
        movies.setYearOfRelease("2017");
    }

    @Test
    public void testSaveMovie(){
        movieRepository.save(movies);
        Movies fetchMovies = movieRepository.findById(movies.getImdbId()).get();
        Assert.assertEquals("tt214",fetchMovies.getImdbId());
    }

    @Test
    public void testSaveMovieFailure(){
        movieRepository.save(movies);
        Movies fetchMovies = movieRepository.findById(movies.getImdbId()).get();
        Assert.assertNotEquals(movies,fetchMovies);
    }

    @Test
    public void testGetallMovies(){
        Movies movies1 = new Movies("t214","run-fun","https://www.bookmyshow.com",(float)3.8,"2016","love story");
        Movies movies2 = new Movies("t215","get-run","https://www.bookmyshow.com",(float)4.2,"2017","crime story");
        movieRepository.save(movies1);
        movieRepository.save(movies2);
        List<Movies> fetchMoviesList = movieRepository.findAll();
        Assert.assertEquals("crime story",fetchMoviesList.get(1).getComments());
    }

    @Test
    public void testUpdateMoviesbyId(){
        Movies movies1 = new Movies("t214","run-fun","https://www.bookmyshow.com",(float)3.8,"2016","love story");
        Movies movies2 = new Movies("t215","get-run","https://www.bookmyshow.com",(float)4.2,"2017","crime story");
        movieRepository.save(movies1);
        movieRepository.save(movies2);
        Movies temp = movieRepository.findById("t214").get();
        temp.setComments(movies.getComments());
        movieRepository.save(temp);
        List<Movies> fetchMoviesList = movieRepository.findAll();
        for(Movies mov:fetchMoviesList){
            System.out.println(mov.getImdbId());
        }
        Assert.assertEquals("lov story",fetchMoviesList.get(1).getComments());
    }

    @Test
    public void testDeleteMovies(){
        Movies movies1 = new Movies("t214","run-fun","https://www.bookmyshow.com",(float)3.8,"2016","love story");
        Movies movies2 = new Movies("t215","get-run","https://www.bookmyshow.com",(float)4.2,"2017","crime story");
        movieRepository.save(movies1);
        movieRepository.save(movies2);
        movieRepository.deleteById("t214");
        List<Movies> fetchMoviesList = movieRepository.findAll();
        Assert.assertEquals("t215",fetchMoviesList.get(0).getImdbId());

    }
}
