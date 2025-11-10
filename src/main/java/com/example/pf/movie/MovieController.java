package com.example.pf.movie;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "*")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Movie movie) {
        Movie salvo = movieService.criar(movie);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> listarTodos() {
        return ResponseEntity.ok(movieService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        movieService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(InvalidRatingException.class)
    public ResponseEntity<Map<String, String>> handleInvalidRating(InvalidRatingException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("erro", ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }
}
