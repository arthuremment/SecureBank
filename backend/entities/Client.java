package com.example.ebanque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.Serializable;
import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, unique = false)
    private String nom;

    @Column(name = "prenom", nullable = false, unique = false)
    private String prenom;

    @Column(name="telephone", nullable = false, unique = true)
    private String telephone;

    @Column(name="mail", nullable = false, unique = true)
    private String mail;

    @Column( name = "mot_de_passe" ,unique = true,nullable = true)
    private String motDePasse;


    @Column(name = "photo", unique = true)
    private String photo;
    
    private File files;

    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private Collection<Compte> compte;


    public Client(String nom, String prenom, String telephone,String mail, String motDePasse, String photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mail=mail;
        this.motDePasse = motDePasse;
        this.photo = photo;

    }
}
