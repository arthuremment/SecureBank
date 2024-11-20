package com.example.ebanque.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Versement extends Operation{

    private String telephone;

    public Versement(Date dateOperation, Double montant, Compte compte, String telephone) {
        super(dateOperation, montant, compte);
        this.telephone = telephone;
    }
}
