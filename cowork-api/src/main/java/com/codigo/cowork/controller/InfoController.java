package com.codigo.cowork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {

    @GetMapping("/api/info")
    public Map<String, String> getInfo() {
        return Map.of(
                "nombre", "cowork-api",
                "version", "1.0.0",
                "autor", "Paul"
        );
    }
}