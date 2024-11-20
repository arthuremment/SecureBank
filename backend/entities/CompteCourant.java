package com.example.ebanque.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue( "CC")
public class CompteCourant extends  Compte {

    private Double decouvert;

    public CompteCourant(String deviceMonnaie, String motDePasseCompte, Client client,Double decouvert) {
        super(deviceMonnaie, motDePasseCompte, client);
        this.decouvert = decouvert;
    }





}
