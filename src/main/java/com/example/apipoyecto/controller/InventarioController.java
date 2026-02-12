package com.example.apipoyecto.controller;

import com.example.apipoyecto.model.Inventario;
import com.example.apipoyecto.repository.InventarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioRepository inventarioRepository;

    @GetMapping
    public List<Inventario> listar() {
        return inventarioRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizarCantidad(@PathVariable Long id,
                                                         @Valid @RequestBody Inventario inventarioActualizado) {
        return inventarioRepository.findById(id)
                .map(inv -> {
                    inv.setCantidadDisponible(inventarioActualizado.getCantidadDisponible());
                    return ResponseEntity.ok(inventarioRepository.save(inv));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}


