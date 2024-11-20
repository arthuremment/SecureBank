package com.example.ebanque.services;

import com.example.ebanque.entities.Client;
import com.example.ebanque.entities.Compte;
import com.example.ebanque.entities.Operation;
import com.example.ebanque.entities.Transfert;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;


public interface Ebanq{

    public Compte rechercheCompte(String Numtelephone);
    public Client  rechercheClient(String telephone);

    public Operation createOperation(Double montant, Compte compte);

    public Operation createRetrait(Double montant, String telephone, String motDePasse);

    public Operation createDepot(Double montant, String telephone);

    public List<Operation> listOperation(String telephone);
    public Transfert transferer( String tel1, String tel2, String motDepasse1, Double montant);

//    public Compte creerCompteCourant( String deviceMonnaie, String motDePasse, Client client,Double decouvert,Double solde);

    public Compte creerCompteCourant( String deviceMonnaie, String motDePasse, Client client,Double decouvert);

    public Compte creerCompteEpargne(String deviceMonnaie, String motDePasse, Client client,Double taux);

    public void UploaderPhoto(MultipartFile files, Long id) throws IOException;




}
