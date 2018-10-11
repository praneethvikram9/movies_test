package com.stackroute.moviesNew.repository;

import com.stackroute.moviesNew.domain.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MovieRepository extends JpaRepository<Movies,String> {

}
