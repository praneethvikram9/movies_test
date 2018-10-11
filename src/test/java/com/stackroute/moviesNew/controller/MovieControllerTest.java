package com.stackroute.moviesNew.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviesNew.domain.Movies;
import com.stackroute.moviesNew.exceptions.MovieNameExistsException;
import com.stackroute.moviesNew.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Movies movies;

    @MockBean
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    private List<Movies> list=null;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        movies = new Movies();
        movies.setImdbId("tt214");
        movies.setMovieTitle("hello");
        movies.setComments("love story");
        movies.setPostedUrl("https://bookmmyshow.com:hello");
        movies.setRating((float)4.2);
        movies.setYearOfRelease("2017");
        List<Movies> list = new ArrayList<>();

    }

    @Test
    public void saveMovie() throws Exception {
        when(movieService.saveMovie(any())).thenReturn(movies);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie/add")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movies)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void saveMovieFailure() throws Exception {
        when(movieService.saveMovie(any())).thenThrow(MovieNameExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie/add")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movies)))
                .andExpect(MockMvcResultMatchers.status().isNotImplemented())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getAllMovies() throws Exception {
        when(movieService.getallMovies()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/show")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movies)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteMovie() throws Exception{
        when(movieService.delete("tt214")).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/movie/tt214")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movies)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
