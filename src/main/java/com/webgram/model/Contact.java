package com.webgram.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false) 
    private String nom;

    @Column(nullable = false)
    @NotBlank
    private String prenom;
    @Column(nullable = false)
    @NotBlank
    private String telephone;
    @Email
    @Column(nullable = false)
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String poste; // intitule du poste
    @NotBlank
    @Column(nullable = false)
    private String direction; // direction ou le service


    //Relation : plusieurs contacts pour un utilisateur
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;


}
