package com.example.ebanque.repository;

import com.example.ebanque.entities.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfertRepository extends JpaRepository<Transfert, Long> {



}
