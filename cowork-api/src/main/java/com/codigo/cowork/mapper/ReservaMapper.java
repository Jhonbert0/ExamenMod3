package com.codigo.cowork.mapper;

import com.codigo.cowork.dto.ReservaRequestDTO;
import com.codigo.cowork.dto.ReservaResponseDTO;
import com.codigo.cowork.model.Reserva;

public class ReservaMapper {
    public static Reserva toReserva(ReservaRequestDTO reservaRequestDTO){
        Reserva reserva = new Reserva();
        reserva.setSalaId(reservaRequestDTO.getSalaId());
        reserva.setResponsable(reservaRequestDTO.getResponsable());
        reserva.setEmail(reservaRequestDTO.getEmail());
        reserva.setFecha(reservaRequestDTO.getFecha());
        reserva.setHoraInicio(reservaRequestDTO.getHoraInicio());
        reserva.setHoraFin(reservaRequestDTO.getHoraFin());
        reserva.setPasswordInterno(reservaRequestDTO.getPasswordInterno());
        return reserva;
    }
    public static ReservaResponseDTO toReservaResponseDTO(Reserva reserva){
        ReservaResponseDTO reservaResponseDTO = new ReservaResponseDTO();
        reservaResponseDTO.setId(reserva.getId());
        reservaResponseDTO.setSalaId(reserva.getSalaId());
        reservaResponseDTO.setResponsable(reserva.getResponsable());
        reservaResponseDTO.setEmail(reserva.getEmail());
        reservaResponseDTO.setFecha(reserva.getFecha());
        reservaResponseDTO.setHoraInicio(reserva.getHoraInicio());
        reservaResponseDTO.setHoraFin(reserva.getHoraFin());
        reservaResponseDTO.setEstado(reserva.getEstado());
        return reservaResponseDTO;
    }
}
