package com.example.demo.repo;

import com.example.demo.models.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMovieRepository extends CrudRepository<Movie, Long> {

  List<Movie> findByTitle(String title);
}
