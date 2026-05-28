package com.codigo.cowork.repository;

import com.codigo.cowork.model.Sala;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SalaRepository {
    private final List<Sala> salas =new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public Sala save(Sala sala) {
        if (sala.getId() == null) {
            sala.setId(counter.getAndIncrement());
            salas.add(sala);
        } else {
            deleteById(sala.getId());
            salas.add(sala);
        }
        return sala;
    }

    public List<Sala> findAll() { return salas; }

    public Optional<Sala> findById(Long id) {
        return salas.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id) {
        salas.removeIf(s -> s.getId().equals(id));
    }

}
