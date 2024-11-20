package com.example.ebanque.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue( "CE")
public class CompteEpargne extends Compte {

    private  Double taux;

    public CompteEpargne(String deviceMonnaie, String motDePasse, Client client, Double taux) {
        super(deviceMonnaie,motDePasse,client);
        this.taux=taux;

    }
}
