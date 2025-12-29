package com.example.play.web.controller;

import com.example.play.domain.dto.MovieDto;
import com.example.play.domain.dto.SuggestRequestDto;
import com.example.play.domain.dto.UpdateMovieDto;
import com.example.play.domain.services.MovieServices;
import com.example.play.domain.services.PlayAIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies", description = "Operations about movies on Play")
public class MovieController {
    private final MovieServices movieServices;
    private final PlayAIService aiService;

    public MovieController(MovieServices movieServices, PlayAIService aiService) {
        this.movieServices = movieServices;
        this.aiService = aiService;
    }

    @GetMapping()
    public ResponseEntity<List<MovieDto>> getAll() {


        return ResponseEntity.ok(this.movieServices.getAll());

    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a movie by Id",
            description = "Return the movie by Id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Movie find"),
                    @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content)
            }
    )
    public ResponseEntity<MovieDto> getById(@Parameter(description = "Id movie to find", example = "9") @PathVariable Long id) {
        MovieDto movieDto = this.movieServices.getById(id);
        if(movieDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDto);
    }

    @PostMapping()
    public ResponseEntity<MovieDto> add(@RequestBody MovieDto movieDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.movieServices.add(movieDto));
    }

    @PostMapping("/suggest")
    public ResponseEntity<String> generateMoviesSuggestion(@RequestBody SuggestRequestDto suggestRequestDto){
        return ResponseEntity.ok(this.aiService.generateMoviesSuggestion(suggestRequestDto.userPreference()));

    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable Long id, @RequestBody @Valid UpdateMovieDto updateMovieDto) {
        return ResponseEntity.ok(this.movieServices.update(id, updateMovieDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieDto> delete(@PathVariable Long id){
        return ResponseEntity.ok(this.movieServices.delete(id));
    }
}
