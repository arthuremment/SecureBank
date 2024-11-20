package com.example.ebanque.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance
        (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type_Cpte",length = 20,discriminatorType = DiscriminatorType.STRING)
public abstract class Compte implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String numeroCompte;

    @Column
    private Double solde;

    @Column
    private String DeviceMonnaie;

    @Column
    private String MotDePasseCompte;

    @ManyToOne
    @JoinColumn(name = "CODE_id")
    private Client client;

    @OneToMany(mappedBy = "compte")
    private Collection<Operation> operations;

    public Compte(String deviceMonnaie, String motDePasseCompte, Client client) {

        this.numeroCompte = " Mh"+client.getTelephone();
        DeviceMonnaie = deviceMonnaie;
        MotDePasseCompte = motDePasseCompte;
        this.client = client;
    }

}
