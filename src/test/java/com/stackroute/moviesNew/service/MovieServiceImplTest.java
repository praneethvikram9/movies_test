package com.stackroute.moviesNew.service;

import com.stackroute.moviesNew.domain.Movies;

import com.stackroute.moviesNew.exceptions.NoSuchMovieException;
import com.stackroute.moviesNew.exceptions.NullDetailsException;
import com.stackroute.moviesNew.repository.MovieRepository;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;


import org.mockito.Mock;
import org.junit.Assert;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class MovieServiceImplTest {


    Movies movies;

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieServiceImpl movieService;
    List<Movies> list = null;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        movies = new Movies();
        movies.setImdbId("tt214");
        movies.setMovieTitle("hello");
        movies.setComments("love story");
        movies.setPostedUrl("https://bookmmyshow.com:hello");
        movies.setRating((float)4.2);
        movies.setYearOfRelease("2017");

        //Movies movies1 = new Movies("t214","run-fun","https://www.bookmyshow.com",(float)3.8,"2016","love story");
        //list.add(movies);

    }



    @Test
    public void saveMovieTest() throws Exception{
        when(movieRepository.save((Movies)any())).thenReturn(movies);
        Movies savedMovies = movieService.saveMovie(movies);
        Assert.assertEquals(movies,savedMovies);
        verify(movieRepository,times(1)).save(movies);
    }

    @Test
    public void getAllUser() throws NullDetailsException {
        movieRepository.save(movies);
        when(movieRepository.findAll()).thenReturn(list);
        List<Movies> moviesList = movieService.getallMovies();
        Assert.assertEquals(list,moviesList);
        verify(movieRepository,times(1)).findAll();
    }

    @Test(expected = NoSuchMovieException.class)
    public void searchById() throws Exception{
            Movies movies1 = new Movies("t214","run-fun","https://www.bookmyshow.com",(float)3.8,"2016","love story");
            when(movieRepository.save(movies1)).thenReturn(movies1);
            Movies savedMovies = movieService.saveMovie(movies1);
            Optional<Movies> returnMovie=Optional.empty();
            // Assert.assertEquals(movies1,savedMovies);
            //System.out.println(savedMovies.getImdbId());
            //Assert.assertEquals(savedMovies.getImdbId(),movies1.getImdbId());
            when(movieRepository.findById(any())).thenReturn(returnMovie);
            Optional<Movies> returnServiceMovie = movieService.getMovieById(movies1.getImdbId());
            Assert.assertEquals(returnMovie,returnServiceMovie);


       /* catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

*/
    }


    @Test
    public void updatebyId() throws Exception{
        try{
            Movies movies1 = new Movies("t214","run-fun","https://www.bookmyshow.com",(float)3.8,"2016","love story");
            when(movieRepository.save(movies1)).thenReturn(movies1);
            Movies savedMovies1 = movieService.saveMovie(movies1);
            Movies movies2 = new Movies("t215","run raja","https://www.bookmyshow.com",(float)4.2,"2018","crime story");
            when(movieRepository.save(movies2)).thenReturn(movies2);
            Movies savedMovies2 = movieService.saveMovie(movies2);
            List<Movies> newList = new ArrayList<>();
            newList = movieService.getallMovies();
            System.out.println(newList.get(0).getImdbId());


        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

}
