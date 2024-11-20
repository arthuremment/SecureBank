package com.example.ebanque;

import com.example.ebanque.entities.*;
import com.example.ebanque.repository.ClientRepository;
import com.example.ebanque.repository.CompteRepository;
import com.example.ebanque.repository.OperationRepository;
import com.example.ebanque.services.EbanqSerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
public class EBanqueApplication implements CommandLineRunner {

    @Autowired
    private EbanqSerImpl service;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private OperationRepository operationRepository;

    public static void main(String[] args) {
        SpringApplication.run(EBanqueApplication.class, args);
    }







    @Override
    public void run(String... args) throws Exception {
//           Client cli1= clientRepository.save(new Client("mballa","jean-claude","687254982","hollandeveux","kil356","paulin"));
//
//          Client cli2=  clientRepository.save(new Client("bolo", "egana", "65884545", "jules cesar", "eb21", "polo"));





//            CompteEpargne ct3=new CompteEpargne(600.00);
//            CompteEpargne st=new CompteEpargne(7600.00);
//            CompteEpargne st3=new CompteEpargne(8600.00);
//
//
//
//
//              Operation o4=new Operation(ct3);
//              Operation o5=new Operation(st);
//              Operation o6=new Operation(st3);

//
//
//

//            Collection<Operation> op =new ArrayList<>();
//
//            op.add(o4);
//            op.add(o5);

         // CompteCourant cc1=compteRepository.save(new CompteCourant("5246","50000","francs","baolor",cli1,op,700000.00));
//        CompteCourant compteCourant=new CompteCourant( "6554",50000.0,"francs","baolor",cli1, op,700000.00);
//         compteRepository.save(compteCourant);


//            CompteEpargne cce1=compteRepository.save(new CompteEpargne("657l",785691.0,"Euro","blaca52",cli1,op,25.05));
//
//            compteRepository.save(cce1);
//
//
//        System.out.println("Ici est le client récherché:");
//        System.out.println(compteRepository.findByClient (cli1));
//
//        System.out.println("Ici est le compte récherché:");
//        System.out.println(service.rechercheCompte("687254982").getSolde());




//
//        Compte compte1= new Compte("123a","12000","fcfa","1234a",cli1,op);
//
//        compteRepository.save(compte1);
//
//        CompteCourant CompteCourant = new CompteCourant( "5478d",79000.0,"euro", "blondelle", cli1,op,78555522215.00);
//
//        compteRepository.save(CompteCourant);

    }

}


