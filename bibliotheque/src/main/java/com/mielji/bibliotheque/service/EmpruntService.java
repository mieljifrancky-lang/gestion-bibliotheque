package com.mielji.bibliotheque.service;

import com.mielji.bibliotheque.model.Emprunt;
import com.mielji.bibliotheque.repository.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpruntService implements IMetier<Emprunt, Long> {

    @Autowired
    private EmpruntRepository empruntRepository;

    @Override
    public Emprunt save(Emprunt entity) {
        return empruntRepository.save(entity);
    }

    @Override
    public Emprunt update(Emprunt entity) {
        return empruntRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        empruntRepository.deleteById(id);
    }

    @Override
    public Emprunt get(Long id) {
        return empruntRepository.findById(id).orElse(null);
    }

    @Override
    public List<Emprunt> getAll() {
        return empruntRepository.findAll();
    }
}