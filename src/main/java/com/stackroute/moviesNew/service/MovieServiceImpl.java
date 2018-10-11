package com.stackroute.moviesNew.service;

import com.stackroute.moviesNew.domain.Movies;
import com.stackroute.moviesNew.exceptions.MovieNameExistsException;
import com.stackroute.moviesNew.exceptions.NoSuchMovieException;
import com.stackroute.moviesNew.exceptions.NullDetailsException;
import com.stackroute.moviesNew.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {


    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository1){
        this.movieRepository=movieRepository1;
    }





    @Override
    public Movies saveMovie(Movies movies) throws MovieNameExistsException {
        if(movieRepository.existsById(movies.getImdbId())){
            throw new MovieNameExistsException("movie name already exist");
        }
        Movies savedMovie = movieRepository.save(movies);
        //check for null and user exist exception
        return savedMovie;
    }

    @Override
    public List<Movies> getallMovies() throws NullDetailsException {

        if(movieRepository==null){
            throw new NullDetailsException("no details to show");
        }
        List<Movies> movies = movieRepository.findAll();
        return movies;
    }



    @Override
    public List<Movies> delete(String id) throws NoSuchMovieException {
        if(!movieRepository.existsById(id)){
            throw new  NoSuchMovieException("no movie with that id");
        }

        movieRepository.deleteById(id);
        return movieRepository.findAll();
    }

    @Override
    public List<Movies> update(Movies movies,String id)throws NoSuchMovieException {

        if(movieRepository.existsById(id)){
            Movies temp=movieRepository.findById(id).get();
            temp.setComments(movies.getComments());
            movieRepository.save(temp);
        }
        else
        {
            throw new NoSuchMovieException("no such id to update");
        }
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movies> getMovieById(String id) throws NoSuchMovieException{

        if(movieRepository.existsById(id)){

            return movieRepository.findById(id);
        }
        else
        {
            throw new NoSuchMovieException("there is no movie by that id");
        }

    }

    @Override
    public List<Movies>getMovieByName(String name) throws NoSuchMovieException{
        List<Movies> temp = movieRepository.findAll();
        List<Movies> result = new ArrayList<>();
        for(Movies t:temp){
            if(t.getMovieTitle().equals(name)){
                result.add(t);
            }
        }
        return result;
    }

}
