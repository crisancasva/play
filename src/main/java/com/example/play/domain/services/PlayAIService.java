package com.example.play.domain.services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface PlayAIService {
    @UserMessage("""
            Genera un saludo de bienvenida a la plataforma de gestion de Peliculas {{platform}}            usa menos de 120 caracteres y hazlo con el estilo de play
            """)
    String generateGreeting(@V("platform")String platform);

    @SystemMessage("""
            Eres un experto en cine que recomienda peliculas personalizadas segun los gustos del usuario.
            Debes recomendar maximo 3 peliculas.
            No incluyas peliculas que estan por fuera de la plataforma Play.
            """)
    String generateMoviesSuggestion(@UserMessage String userMessage);
}
