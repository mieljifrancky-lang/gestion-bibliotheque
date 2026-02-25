package com.mielji.bibliotheque.service;

import com.mielji.bibliotheque.model.Utilisateur;
import com.mielji.bibliotheque.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService implements IMetier<Utilisateur, Long> {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur save(Utilisateur entity) {
        return utilisateurRepository.save(entity);
    }

    @Override
    public Utilisateur update(Utilisateur entity) {
        return utilisateurRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public Utilisateur get(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    @Override
    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }
}