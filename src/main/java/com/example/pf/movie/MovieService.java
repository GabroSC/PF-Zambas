package com.example.pf.movie;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie criar(Movie movie) {
        validarNota(movie);
        return movieRepository.save(movie);
    }

    public List<Movie> listarTodos() {
        return movieRepository.findAll();
    }

    public void excluir(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Filme não encontrado com id: " + id);
        }
        movieRepository.deleteById(id);
    }

    private void validarNota(Movie movie) {
        if (movie.getNota() == null) {
            throw new InvalidRatingException("Nota é obrigatória.");
        }
        if (movie.getNota() < 0 || movie.getNota() > 5) {
            throw new InvalidRatingException("Nota deve estar entre 0 e 5.");
        }
    }
}
