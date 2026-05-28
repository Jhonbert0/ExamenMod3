package com.codigo.cowork.service;

import com.codigo.cowork.dto.ReservaRequestDTO;
import com.codigo.cowork.dto.ReservaResponseDTO;
import com.codigo.cowork.mapper.ReservaMapper;
import com.codigo.cowork.model.Reserva;
import com.codigo.cowork.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public ReservaResponseDTO crearReserva(ReservaRequestDTO dto) {
        Reserva reserva = ReservaMapper.toReserva(dto);
        reserva.setEstado("PENDIENTE"); // Regla 6: Estado inicial siempre PENDIENTE
        Reserva guardada = reservaRepository.save(reserva);
        return ReservaMapper.toReservaResponseDTO(guardada);
    }

    public ReservaResponseDTO obtenerPorId(Long id) {
        return reservaRepository.findById(id)
                .map(ReservaMapper::toReservaResponseDTO)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    public List<ReservaResponseDTO> listarConFiltros(String estado, LocalDate fecha, Long salaId) {
        // Regla 10: Intersección de filtros
        return reservaRepository.findAll().stream()
                .filter(r -> estado == null || r.getEstado().equalsIgnoreCase(estado))
                .filter(r -> fecha == null || r.getFecha().equals(fecha))
                .filter(r -> salaId == null || r.getSalaId().equals(salaId))
                .map(ReservaMapper::toReservaResponseDTO)
                .toList();
    }

    public List<ReservaResponseDTO> listarPorSala(Long salaId) {
        return reservaRepository.findBySalaId(salaId).stream()
                .map(ReservaMapper::toReservaResponseDTO)
                .toList();
    }

    public ReservaResponseDTO cambiarEstado(Long id, String nuevoEstado) {
        if (!nuevoEstado.equals("PENDIENTE") && !nuevoEstado.equals("CONFIRMADA") && !nuevoEstado.equals("CANCELADA")) {
            throw new RuntimeException("Estado inválido. Solo se permite PENDIENTE, CONFIRMADA o CANCELADA.");
        }

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reserva.setEstado(nuevoEstado);
        Reserva actualizada = reservaRepository.save(reserva);
        return ReservaMapper.toReservaResponseDTO(actualizada);
    }

    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
