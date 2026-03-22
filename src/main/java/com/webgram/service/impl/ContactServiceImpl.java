package com.webgram.service.impl;

import com.webgram.model.Contact;
import com.webgram.repository.ContactRepository;
import com.webgram.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repo;

    //Constructeur pour injecter le repository dans le service
    public ContactServiceImpl(ContactRepository repo)
    {
        this.repo =repo;
    }

    @Override
    public List<Contact> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Contact> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Contact create(Contact contact) {
        return repo.save(contact);
    }

    @Override
    public Contact update(Long id, Contact contact) {
        contact.setId(id);
        return repo.save(contact);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);

    }

    @Override
    public List<Contact> searchByNom(String nom) {
        return null;
    }
}
