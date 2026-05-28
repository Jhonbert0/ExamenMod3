package com.codigo.cowork.controller;

import com.codigo.cowork.dto.ReservaRequestDTO;
import com.codigo.cowork.dto.ReservaResponseDTO;
import com.codigo.cowork.service.ReservaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> crearReserva(@RequestBody ReservaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.crearReserva(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> obtenerReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> listarReservas(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha,
            @RequestParam(required = false) Long salaId) {
        return ResponseEntity.ok(reservaService.listarConFiltros(estado, fecha, salaId));
    }

    @GetMapping("/sala/{salaId}")
    public ResponseEntity<List<ReservaResponseDTO>> listarPorSala(@PathVariable Long salaId) {
        return ResponseEntity.ok(reservaService.listarPorSala(salaId));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<ReservaResponseDTO> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String nuevoEstado) {
        return ResponseEntity.ok(reservaService.cambiarEstado(id, nuevoEstado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comprobante")
    public ResponseEntity<Map<String, Object>> subirComprobante(
            @PathVariable Long id,
            @RequestHeader("X-Cliente-Id") String clienteId,
            @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(Map.of(
                "nombre", file.getOriginalFilename(),
                "tamano", file.getSize()
        ));
    }
}