package com.mielji.bibliotheque.service;

import com.mielji.bibliotheque.model.Document;
import com.mielji.bibliotheque.model.Livre;
import com.mielji.bibliotheque.model.Magazine;
import com.mielji.bibliotheque.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService implements IMetier<Document, Long> {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Document save(Document entity) {
        return documentRepository.save(entity);
    }

    @Override
    public Document update(Document entity) {
        return documentRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        documentRepository.deleteById(id);
    }

    @Override
    public Document get(Long id) {
        return documentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    // Méthode pour récupérer seulement les livres
    public List<Livre> getAllLivres() {
        return documentRepository.findAll()
                .stream()
                .filter(d -> d instanceof Livre)
                .map(d -> (Livre)d)
                .collect(Collectors.toList());
    }

    // Méthode pour récupérer seulement les magazines
    public List<Magazine> getAllMagazines() {
        return documentRepository.findAll()
                .stream()
                .filter(d -> d instanceof Magazine)
                .map(d -> (Magazine)d)
                .collect(Collectors.toList());
    }
}