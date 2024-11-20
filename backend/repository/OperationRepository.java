package com.example.ebanque.repository;

import com.example.ebanque.entities.Compte;
import com.example.ebanque.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    public List<Operation> findByCompte(Compte compte);
}
