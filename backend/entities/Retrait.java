package com.example.ebanque.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Retrait extends Operation{

    private String telephone;


    private String motDePasse;

    public Retrait(Date dateOperation, Double montant, Compte compte, String telephone, String motDePasse) {
        super(dateOperation, montant, compte);
        this.telephone = telephone;
        this.motDePasse = motDePasse;
    }
}
