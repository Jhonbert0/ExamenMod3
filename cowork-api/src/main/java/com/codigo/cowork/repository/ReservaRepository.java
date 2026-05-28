package com.codigo.cowork.repository;

import com.codigo.cowork.model.Reserva;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class ReservaRepository {
    private final List<Reserva> reservas = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public Reserva save(Reserva reserva) {
        if (reserva.getId() == null) {
            reserva.setId(counter.getAndIncrement());
            reservas.add(reserva);
        } else {
            deleteById(reserva.getId());
            reservas.add(reserva);
        }
        return reserva;
    }

    public List<Reserva> findAll() { return reservas; }

    public Optional<Reserva> findById(Long id) {
        return reservas.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public List<Reserva> findBySalaId(Long salaId) {
        return reservas.stream().filter(r -> r.getSalaId().equals(salaId)).toList();
    }

    public void deleteById(Long id) {
        reservas.removeIf(r -> r.getId().equals(id));
    }
}
