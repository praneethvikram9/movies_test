package com.stackroute.moviesNew.controller;


import com.stackroute.moviesNew.domain.Movies;
import com.stackroute.moviesNew.exceptions.NoSuchMovieException;
import com.stackroute.moviesNew.exceptions.NullDetailsException;
import com.stackroute.moviesNew.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="api/v1")
public class MovieController {

    MovieService movieService ;

    @Autowired
    public MovieController( MovieService movieService1){
        this.movieService=movieService1;
    }


    @PostMapping("movie/add")
    public ResponseEntity<?> saveMovie(@RequestBody Movies movies){
        ResponseEntity responseEntity;
        try
        {
            movieService.saveMovie(movies);
            responseEntity = new ResponseEntity<List<Movies>>(movieService.getallMovies(), HttpStatus.CREATED);
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);

        }

        return responseEntity;

    }

    @GetMapping("movie/show")

    public ResponseEntity<?> getallMovies(){
        ResponseEntity responseEntity ;
        try{
            responseEntity = new ResponseEntity<List<Movies>>(movieService.getallMovies(),HttpStatus.FOUND);
        }
        catch (NullDetailsException ex){
            responseEntity =new ResponseEntity<String>(ex.getMessage(),HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }
    @GetMapping("movie/{id}")
    public ResponseEntity<?>getMovieById(@PathVariable("id") String id){
        ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity<Optional<Movies>>(movieService.getMovieById(id),HttpStatus.FOUND);
        }
        catch (NoSuchMovieException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    @PutMapping("movie/{id}")
    public ResponseEntity<?>updateMovie(@RequestBody Movies movies,@PathVariable("id") String id){
        ResponseEntity responseEntity;
        try{
            movieService.update(movies,id);
            responseEntity = new ResponseEntity<String>("successfully updated",HttpStatus.ACCEPTED);
        }
        catch (NoSuchMovieException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_IMPLEMENTED);
        }
        return responseEntity;
    }

    //@PutMapping("movie/delete/{id}")
    @DeleteMapping("movie/{id}")
    public  ResponseEntity<?> deleteMovie(@PathVariable("id") String id){
        ResponseEntity responseEntity;
        try{
            responseEntity= new ResponseEntity<List<Movies>>(movieService.delete(id),HttpStatus.OK);
        }
        catch (NoSuchMovieException ex){
            responseEntity =new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("movie/name/{name}")
    public ResponseEntity<?>getbyName(@PathVariable("name") String name){
        ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity<List<Movies>>(movieService.getMovieByName(name),HttpStatus.FOUND);
        }
        catch (NoSuchMovieException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
