package com.example.ebanque.repository;

import com.example.ebanque.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    public Client  findByTelephoneAndMotDePasse(String tel,String motDePasse);

//    public Client findByTelephone(String tel);
    public Client findClientByTelephone(String tel);

    public Client  findByMail(String mail);





}
