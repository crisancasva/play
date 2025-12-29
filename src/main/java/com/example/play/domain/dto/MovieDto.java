package com.example.play.domain.dto;

import com.example.play.domain.Genre;
import com.example.play.domain.State;

import java.time.LocalDate;

public record MovieDto(
        Long id,
        String title,
        Integer duration,
        Genre genre,
        LocalDate releaseDate,
        Double rating,
        State state
) {
}
