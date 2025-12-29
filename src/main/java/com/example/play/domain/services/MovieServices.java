package com.example.play.domain.services;

import com.example.play.domain.dto.MovieDto;
import com.example.play.domain.dto.UpdateMovieDto;
import com.example.play.domain.repository.MovieRepository;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServices {
    private final MovieRepository movieRepository;


    public MovieServices(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Tool("Busca todas la peliculas que existan en la plataforma")
    public List<MovieDto> getAll() {
        return this.movieRepository.getAll();
    }

    public MovieDto getById(Long id) {
        return this.movieRepository.getById(id);
    }

    public MovieDto add(MovieDto movieDto) {
        return this.movieRepository.save(movieDto);
    }

    public MovieDto update(Long id, UpdateMovieDto updateMovieDto) {
        return this.movieRepository.update(id, updateMovieDto);
    }

    public MovieDto delete(Long id) {
        return this.movieRepository.delete(id);
    }
}
