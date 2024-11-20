package com.example.ebanque.repository;

import com.example.ebanque.entities.Client;
import com.example.ebanque.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CompteRepository extends JpaRepository<Compte, String> {

   public Compte findByClientTelephone( String numTel);
    public Collection<Compte> findByClient(Client client);

    public Compte findByClient_MotDePasse(String motDePasse);



}
