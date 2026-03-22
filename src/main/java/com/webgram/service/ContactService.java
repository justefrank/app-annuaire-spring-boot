package com.webgram.service;

import com.webgram.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ContactService {
    List<Contact> findAll(); //Récupérer tous les contacts enregistrer dans la base
    Optional<Contact> findById(Long id);//récupérer  un contact précis grâce à son identifiant

    //Methode Du CRUD
    Contact create(Contact contact);
    Contact update(Long id, Contact contact);
    void delete(Long id);
    List<Contact> searchByNom(String nom); //permet de rechercher un contact
}
