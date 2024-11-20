package com.example.ebanque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numOper;

     private Date  DateOperation;

     private Double montant;


     @ManyToOne
     @JoinColumn(name = "type_cpte")          //NUM_CPTE//type_cpte
     private Compte compte;

    public Operation(Date dateOperation, Double montant, Compte compte) {

        DateOperation =new Date(2024, 0, 1);
        this.montant = montant;
        this.compte = compte;
    }
}
