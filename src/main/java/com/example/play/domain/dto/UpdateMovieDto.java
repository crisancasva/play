package com.example.play.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record UpdateMovieDto(
        @NotBlank(message = "El titulo es obligatorio")
        String title,

        @PastOrPresent(message = "La fecha debe ser la presente o pasada")
        LocalDate releaseDate,

        @Min(value = 0, message = "No puede ser menor a 0")
        @Max(value = 5, message = "No puede ser mayor a 5")
        Double rating
) {
}
