package com.webgram.repository;

import com.webgram.model.Contact;
import com.webgram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
  List<Contact> findByUser(User user); // tous les contacts d'un utilisateur donnés
  List<Contact> findByNomContainingIgnoreCase(String nom); // permet de rechercher les contacts à partir d'un mot
}
