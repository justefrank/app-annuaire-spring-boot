package com.webgram.controller;

import com.webgram.dto.ContactDTO;
import com.webgram.model.Contact;
import com.webgram.model.User;
import com.webgram.service.ContactService;
import com.webgram.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;
    private final UserService userService;

    public ContactController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
    }

    @GetMapping
    public List<Contact> getAll() {
        return contactService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> get(@PathVariable Long id) {
        return contactService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Contact> search(@RequestParam String q) {
        return contactService.searchByNom(q);
    }

    @PostMapping
    public ResponseEntity<Contact> create(@Valid @RequestBody ContactDTO dto) {
        User owner = userService.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));
        //on mappe la DTO -> Entity
        Contact c = new Contact();
        c.setNom(dto.getNom());
        c.setPrenom(dto.getPrenom());
        c.setTelephone(dto.getTelephone());
        c.setEmail(dto.getEmail());
        c.setPoste(dto.getPoste());
        c.setDirection(dto.getDirection());
        c.setUser(owner);

        Contact created = contactService.create(c);
        return ResponseEntity.created(URI.create("/api/contacts/" +
                created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> update(@Valid @PathVariable Long id, @RequestBody ContactDTO dto) {
        return contactService.findById(id)
                .map(existing -> {
                    existing.setNom(dto.getNom());
                    existing.setPrenom(dto.getPrenom());
                    existing.setEmail(dto.getEmail());
                    existing.setTelephone(dto.getTelephone());
                    existing.setPoste(dto.getPoste());
                    existing.setDirection(dto.getDirection());

                    //possible de changer de proprietaire:
                    if (dto.getUserId() != null) {
                        User owner = userService.findById(dto.getUserId())
                                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));
                        existing.setUser(owner);
                    }
                    Contact updated = contactService.update(id ,existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if (contactService.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



