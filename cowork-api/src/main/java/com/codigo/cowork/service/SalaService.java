package com.codigo.cowork.service;

import com.codigo.cowork.dto.SalaRequestDTO;
import com.codigo.cowork.dto.SalaResponseDTO;
import com.codigo.cowork.mapper.SalaMapper;
import com.codigo.cowork.model.Reserva;
import com.codigo.cowork.model.Sala;
import com.codigo.cowork.repository.ReservaRepository;
import com.codigo.cowork.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SalaService {
    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository;

    public SalaService(SalaRepository salaRepository, ReservaRepository reservaRepository) {
        this.salaRepository = salaRepository;
        this.reservaRepository = reservaRepository;
    }

    public SalaResponseDTO crearSala(SalaRequestDTO dto) {
        Sala nuevaSala = SalaMapper.toSala(dto);
        if (dto.getActiva() == null) {
            nuevaSala.setActiva(true);
        }
        Sala guardada = salaRepository.save(nuevaSala);
        return mapearASalaResponse(guardada);
    }

    public List<SalaResponseDTO> listarTodas() {
        return salaRepository.findAll().stream()
                .map(this::mapearASalaResponse)
                .toList();
    }

    public SalaResponseDTO obtenerPorId(Long id) {
        return salaRepository.findById(id)
                .map(this::mapearASalaResponse)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));
    }

    public SalaResponseDTO actualizarSala(Long id, SalaRequestDTO dto) {
        Sala salaExistente = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        salaExistente.setCodigo(dto.getCodigo());
        salaExistente.setNombre(dto.getNombre());
        salaExistente.setCapacidad(dto.getCapacidad());
        salaExistente.setUbicacion(dto.getUbicacion());

        if (dto.getActiva() != null) {
            salaExistente.setActiva(dto.getActiva());
        }

        Sala actualizada = salaRepository.save(salaExistente);
        return mapearASalaResponse(actualizada);
    }

    public void eliminarSala(Long id) {
        // Regla 9: Eliminación en cascada de reservas
        List<Reserva> reservasAsociadas = reservaRepository.findBySalaId(id);
        for (Reserva r : reservasAsociadas) {
            reservaRepository.deleteById(r.getId());
        }
        salaRepository.deleteById(id);
    }

    private SalaResponseDTO mapearASalaResponse(Sala sala) {
        String descripcionCorta = String.format("%s - %s (cap. %d)",
                sala.getCodigo(), sala.getNombre(), sala.getCapacidad());
        return SalaMapper.toSalaResponseDTO(sala, descripcionCorta);
    }
}
