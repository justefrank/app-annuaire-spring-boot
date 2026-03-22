package com.webgram.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ContactDTO {

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotBlank
    private String telephone;

    @Email
    private String email;

    @NotBlank
    private String poste; // intitule du poste

    @NotBlank
    private String direction; // direction ou le service

    @NotNull
    private Long userId; // correct pour un Long

}
