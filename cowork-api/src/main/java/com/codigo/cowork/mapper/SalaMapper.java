package com.codigo.cowork.mapper;

import com.codigo.cowork.dto.SalaRequestDTO;
import com.codigo.cowork.dto.SalaResponseDTO;
import com.codigo.cowork.model.Sala;

public class SalaMapper {
    public static Sala toSala(SalaRequestDTO salaRequestDTO){
        Sala sala = new Sala();
        sala.setCodigo(salaRequestDTO.getCodigo());
        sala.setNombre(salaRequestDTO.getNombre());
        sala.setCapacidad(salaRequestDTO.getCapacidad());
        sala.setUbicacion(salaRequestDTO.getUbicacion());
        sala.setActiva(salaRequestDTO.getActiva() != null ? salaRequestDTO.getActiva() : true);
        return sala;
    }
    public static SalaResponseDTO toSalaResponseDTO(Sala sala, String despcripcionCorta){
        SalaResponseDTO salaResponseDTO = new SalaResponseDTO();
        salaResponseDTO.setId(sala.getId());
        salaResponseDTO.setCodigo(sala.getCodigo());
        salaResponseDTO.setNombre(sala.getNombre());
        salaResponseDTO.setCapacidad(sala.getCapacidad());
        salaResponseDTO.setActiva(sala.isActiva());
        salaResponseDTO.setUbicacion(sala.getUbicacion());
        salaResponseDTO.setDescripcionCorta(despcripcionCorta);
        return salaResponseDTO;
    }
}
