package com.example.play.persistence;

import com.example.play.domain.dto.MovieDto;
import com.example.play.domain.dto.UpdateMovieDto;
import com.example.play.domain.exception.MovieAlreadyExistException;
import com.example.play.domain.repository.MovieRepository;
import com.example.play.persistence.crud.CrudMovieEntity;
import com.example.play.persistence.entity.MovieEntity;
import com.example.play.persistence.mapper.MovieMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class MovieEntityRepository implements MovieRepository {
    private final CrudMovieEntity crudMovieEntity;
    private final MovieMapper movieMapper;

    public MovieEntityRepository(CrudMovieEntity crudMovieEntity, MovieMapper movieMapper) {
        this.crudMovieEntity = crudMovieEntity;
        this.movieMapper = movieMapper;
    }

    @Override
    public List<MovieDto> getAll() {
        return this.movieMapper.toDto(this.crudMovieEntity.findAll());
    }

    @Override
    public MovieDto getById(Long id) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);
        return this.movieMapper.toDto(movieEntity);
    }

    @Override
    public MovieDto save(MovieDto movieDto) {
        if(this.crudMovieEntity.findFirstByTitulo(movieDto.title()) != null){
            throw new MovieAlreadyExistException(movieDto.title());
        }
        MovieEntity movieEntity = this.movieMapper.toEntity(movieDto);
        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    @Override
    public MovieDto update(Long id, UpdateMovieDto updateMovieDto) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);

        if(movieEntity == null) return null;

        this.movieMapper.updateEntityFromDto(updateMovieDto, movieEntity);

        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    @Override
    public MovieDto delete(Long id) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);

        if(movieEntity == null) return null;
        this.crudMovieEntity.delete(movieEntity);
        return this.movieMapper.toDto(movieEntity);
    }


}
