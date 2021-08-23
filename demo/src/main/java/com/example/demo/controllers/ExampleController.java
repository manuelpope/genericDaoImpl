package com.example.demo.controllers;

import com.example.demo.models.Actor;
import com.example.demo.models.Movie;
import com.example.demo.repo.IGenericDao;
import com.example.demo.repo.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExampleController {

  @Autowired private IMovieRepository movieRepository;
  @Autowired private IGenericDao genericJpaDao;

  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    Movie movie = new Movie("ssss", "sssss");
    System.out.println(movie.getDescription());
    // movieRepository.save(movie);
    genericJpaDao.setClazz(Movie.class);
    genericJpaDao.create(movie);
    movie.setDescription("hola si fuen un gusto");
    genericJpaDao.create(movie);

    genericJpaDao.create(movie);
    System.out.println(genericJpaDao.findAll().toString());

    Actor actor = new Actor("Ryan", 33);
    genericJpaDao.setClazz(Actor.class).create(actor);
    System.out.println(genericJpaDao.setClazz(Actor.class).findAll());
    return String.format("Hello %s!", name);
  }

  @PostMapping("/movie")
  Movie newMovie(@RequestBody Movie newMovie) {
    genericJpaDao.create(newMovie);
    return newMovie;
  }

  @PostMapping("/actor")
  Actor newActor(@Valid @RequestBody Actor actor) {
    genericJpaDao.create(actor);

    return actor;
  }

  @GetMapping("/listactor")
  List<Actor> newActor() {

    return genericJpaDao.setClazz(Actor.class).findAll();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return errors;
  }
}
