
package com.mielji.bibliotheque.controller;

import com.mielji.bibliotheque.model.Emprunt;
import com.mielji.bibliotheque.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    @GetMapping
    public ResponseEntity<List<Emprunt>> getAll() {
        return ResponseEntity.ok(empruntService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> getById(@PathVariable Long id) {
        Emprunt e = empruntService.get(id);
        if (e != null) return ResponseEntity.ok(e);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Emprunt> create(@RequestBody Emprunt emprunt) {
        return ResponseEntity.ok(empruntService.save(emprunt));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprunt> update(@PathVariable Long id, @RequestBody Emprunt emprunt) {
        Emprunt existing = empruntService.get(id);
        if (existing != null) {
            emprunt.setId(id);
            return ResponseEntity.ok(empruntService.update(emprunt));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Emprunt existing = empruntService.get(id);
        if (existing != null) {
            empruntService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}