package com.mielji.bibliotheque.controller;

import com.mielji.bibliotheque.model.Document;
import com.mielji.bibliotheque.model.Livre;
import com.mielji.bibliotheque.model.Magazine;
import com.mielji.bibliotheque.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    // GET : tous les documents
    @GetMapping("/")
    public List<Document> getAllDocuments() {
        return documentService.getAll();
    }

    // GET : tous les livres
    @GetMapping("/livres")
    public List<Livre> getAllLivres() {
        return documentService.getAllLivres();
    }

    // GET : tous les magazines
    @GetMapping("/magazines")
    public List<Magazine> getAllMagazines() {
        return documentService.getAllMagazines();
    }

    // GET : document par id
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        Document doc = documentService.get(id);
        if (doc == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(doc);
    }

    // POST : ajouter un document (livre ou magazine)
    @PostMapping("/")
    public Document createDocument(@RequestBody Document document) {
        return documentService.save(document);
    }

    // PUT : mettre à jour un document
    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestBody Document updatedDoc) {
        Document existing = documentService.get(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        updatedDoc.setId(id); // s'assurer que l'id est correct
        Document saved = documentService.update(updatedDoc);
        return ResponseEntity.ok(saved);
    }

    // DELETE : supprimer un document
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        Document existing = documentService.get(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        documentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}