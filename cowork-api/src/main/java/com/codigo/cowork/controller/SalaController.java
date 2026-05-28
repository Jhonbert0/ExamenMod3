package com.codigo.cowork.controller;

import com.codigo.cowork.dto.SalaRequestDTO;
import com.codigo.cowork.dto.SalaResponseDTO;
import com.codigo.cowork.service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping
    public ResponseEntity<List<SalaResponseDTO>> listarSalas() {
        return ResponseEntity.ok(salaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> obtenerSala(@PathVariable Long id) {
        return ResponseEntity.ok(salaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<SalaResponseDTO> crearSala(@RequestBody SalaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salaService.crearSala(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> actualizarSala(@PathVariable Long id, @RequestBody SalaRequestDTO dto) {
        return ResponseEntity.ok(salaService.actualizarSala(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSala(@PathVariable Long id) {
        salaService.eliminarSala(id);
        return ResponseEntity.noContent().build();
    }
}