package com.example.play.web.controller;

import com.example.play.domain.services.PlayAIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final String platform;
    private final PlayAIService aiService;

    public HelloController(@Value("spring.application.name") String platform, PlayAIService aiService) {
        this.platform = platform;
        this.aiService = aiService;
    }

    @GetMapping("/hello")
    public String hello() {
        return this.aiService.generateGreeting(platform);
    }
}
