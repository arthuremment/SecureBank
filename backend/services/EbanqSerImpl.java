package com.example.ebanque.services;

import com.example.ebanque.entities.*;
import com.example.ebanque.repository.ClientRepository;
import com.example.ebanque.repository.CompteRepository;
import com.example.ebanque.repository.OperationRepository;
import com.example.ebanque.repository.TransfertRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EbanqSerImpl implements Ebanq{

    @Autowired
    private TransfertRepository transfertRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private OperationRepository operationRepository;
    @Override
    public Compte rechercheCompte(String Numtelephone) {
        Client cl=clientRepository.findClientByTelephone(Numtelephone);
        Collection<Compte> comptes=compteRepository.findByClient(cl);
        return (Compte) comptes;
    }

    @Override
    public Client rechercheClient(String telephone) {
        return clientRepository.findClientByTelephone(telephone);
    }

    @Override
    public Operation createOperation(Double montant, Compte compte) {
        Operation operation= new Operation(new Date(), 30000.0, compte);


        return null;
    }

//    @Override
//    public Operation createRetrait(Double montant, String telephone, String motDePasse) {
//        Client client= clientRepository.findClientByTelephone(telephone);
//        Collection<Compte> compte;
//        Retrait retrait= new Retrait();
//        if(client !=null) {
//            compte = compteRepository.findByClient(client);
//            for (Compte c : compte) {
//                if (c instanceof CompteCourant) {
//                    System.out.println("le retrait s'est  effectue pour le montant:"+montant+ "avec le telephone:"+telephone+ "et le motDePasse:"+motDePasse);
//                    if (c.getMotDePasseCompte().equals( motDePasse)) {
//                        if (c.getSolde() >= montant) {
//                            c.setSolde(c.getSolde() - montant);
//                            retrait.setDateOperation(new Date());
//                            retrait.setMontant(montant);
//                            retrait.setCompte(c);
//                            operationRepository.save(retrait);
//                        } else {
//                            throw new RuntimeException("Le solde est insuffisant");
//                        }
//                    }
//                }
//            }
//        }
//       return null;
//    }

    @Override
    public Operation createRetrait(Double montant, String telephone, String motDePasse) {
        Client client= clientRepository.findClientByTelephone(telephone);
        Collection<Compte> compte;
        Retrait retrait= new Retrait();
        if(client !=null) {
            compte = compteRepository.findByClient(client);
            for (Compte c : compte) {
                if (c instanceof CompteCourant) {
                    CompteCourant compteCourant= (CompteCourant) c;
                    System.out.println("le retrait s'est  effectue pour le montant:"+montant+ "avec le telephone:"+telephone+ "et le motDePasse:"+motDePasse);
                    if (c.getMotDePasseCompte().equals( motDePasse)) {
                        if (c.getSolde() >= montant) {
                            c.setSolde(c.getSolde() - montant);
                            retrait.setDateOperation(new Date());
                            retrait.setMontant(montant);
                            retrait.setCompte(c);
                            operationRepository.save(retrait);
                        } else if(compteCourant.getSolde()+ compteCourant.getDecouvert()>=montant) {
                            Double montantDecouvert = montant - compteCourant.getSolde();
                            compteCourant.setSolde(compteCourant.getSolde() - compteCourant.getSolde());
                            compteCourant.setDecouvert(compteCourant.getDecouvert() - montantDecouvert);
                            retrait.setDateOperation(new Date());
                            retrait.setMontant(montant);
                            retrait.setCompte(compteCourant);
                            operationRepository.save(retrait);
                            return retrait;
                        }else{
                            throw new RuntimeException("Le solde est insuffisant");
                        }
                    }
                }
            }
        }
        return null;
    }


    @Override
    public Operation createDepot(Double montant, String telephone) {
        Client client = clientRepository.findClientByTelephone(telephone);
        Collection<Compte> comptes; //compteRepository.findByClient(client);
        Versement versement = new Versement();
        if (client != null) {
            comptes = compteRepository.findByClient(client);
            for (Compte c : comptes) {
                //je dois l'enlever ici
                if (c instanceof CompteCourant) {
                    System.out.println("le versement s'est  effectue pour le montant:" + montant + "avec le telephone:" + telephone);
                                c.setSolde(c.getSolde() + montant);
                                versement.setDateOperation(new Date());
                                versement.setMontant(montant);
                                versement.setCompte(c);


                        return operationRepository.save(versement);

                }
            }
        }
            return null;
        }

        //cette methode marche presque mais faut faire des reajustements
    public Transfert transfer(Double montant, String telephone,String motDePasse) {
        Client clientTransferer = clientRepository.findClientByTelephone(telephone);

//        System.out.println("Le transfert s'est effectué avec  le  montant:" + montant + " au numero:" + telephone);
        Collection<Compte> comptes;
        Transfert transfert=new Transfert();
        if (clientTransferer != null) {
            comptes = compteRepository.findByClient(clientTransferer);
//            if (clientTransferer.equals(null)) {
//
//                throw new RuntimeException("ce compte n'existe pas!!");
//            }
            for (Compte c : comptes) {
                if (c instanceof CompteCourant) {
                    CompteCourant compteCourant = (CompteCourant) c;
                    System.out.println("Le transfert s'est effectué avec  le  montant:" + montant + " au numero:" + telephone);
                    if (c.getMotDePasseCompte().equals(motDePasse)) {
//                        System.out.println("Le transfert s'est effectué avec le montant" + montant + "sur le numero:" + telephone);

                        if(c.getSolde()<montant){
                            c.setSolde(c.getSolde()-montant);
                            transfert.setDateOperation(new Date());
                            transfert.setMontant(montant);
                            transfert.setCompte(c);
                            operationRepository.save(transfert);


                        }else {
                            c.setSolde(c.getSolde()+montant);
                            transfert.setDateOperation(new Date());
                            transfert.setMontant(montant);
                            transfert.setCompte(c);
                            operationRepository.save(transfert);
                        }
                    }

                }
            }
        }
        return null;
    }

    @Override
    public List<Operation> listOperation(String telephone) {
        Compte cp= rechercheCompte(telephone);
        return operationRepository.findByCompte(cp);
    }



    @Override
    public Transfert transferer(String tel1, String tel2, String motDepasse1, Double montant) {

        Compte compteExpediteur= compteRepository.findByClientTelephone(tel1);
        Compte compteRecepteur= compteRepository.findByClientTelephone(tel2);

        if(compteExpediteur!=null && compteRecepteur!=null){
            createRetrait(montant,tel1,motDepasse1);
            createDepot(montant,tel2);
        }


        return null ;
    }
//
//    public Transfert getTransfertById(Long transfertId) {
//        Optional<Transfert> optionalTransfert = transfertRepository.findById(transfertId);
//        return optionalTransfert.orElse(null);
//    }

    //@Transactional
    @Override
    public Compte creerCompteCourant(String deviceMonnaie, String motDePasse, Client client,Double decouvert) {
            System.out.println("iiiiiiiiiiiiiiiiiiiiiiii" +client);
            System.out.println("iiiiiiiiiiiiiiiiiiiiiiii" +motDePasse);
            System.out.println("iiiiii"+decouvert);
            clientRepository.save(client);
            Compte comptecreer= new CompteCourant(deviceMonnaie, motDePasse, client,decouvert);
            System.out.println("cccccccccccccccccc" +comptecreer.getNumeroCompte());
    //      comptecreer.setOperations(compte.getOperations());

        return compteRepository.save(comptecreer);
    }

//    @Override
//    public Compte creerCompteCourant(String deviceMonnaie, String motDePasse, Client client, Double decouvert, Double solde) {
//        System.out.println("iiiiiiiiiiiiiiiiiiiiiiii" +client);
//        System.out.println("iiiiiiiiiiiiiiiiiiiiiiii" +motDePasse);
//        clientRepository.save(client);
//
//        Compte comptecreer= new CompteCourant(deviceMonnaie, motDePasse, client);
//        System.out.println("cccccccccccccccccc" +comptecreer.getNumeroCompte());
////        comptecreer.setOperations(compte.getOperations());
//
//        return compteRepository.save(comptecreer);
//    }
//    @Transactional
    @Override
    public Compte creerCompteEpargne(String deviceMonnaie, String motDePasse, Client client,Double taux) {
        System.out.println("iiiiiiiiiiiiiiiiiiiiiiii" +client);
        System.out.println("iiiiiiiiiiiiiiiiiiiiiiii" +motDePasse);
        clientRepository.save(client);
        if (taux<0 || taux >100){
            throw new IllegalArgumentException("Le taux doit etre compris entre 0 et 100");
        }
            Compte comptecreer= new CompteEpargne(deviceMonnaie, motDePasse, client,taux);
            //vient d'etre ajouter

            System.out.println("eeeeeeeeeeeeeeeeee" +comptecreer.getNumeroCompte());
//          comptecreer.setOperations(compte.getOperations());
        return compteRepository.save(comptecreer);
    }


    @Override
    public void UploaderPhoto(MultipartFile files, Long id) throws IOException {
        Client client=clientRepository.findById(id).orElseThrow(); //recherche un client à partir de son id
        System.out.println("le client est upload" +client);
        System.out.println("le client est upload" +id);
        String nomImage= files.getOriginalFilename();//récupère le nom du client(image)
        client.setPhoto(nomImage);
        Files.write(Paths.get(System.getProperty("user.home")+"/fichiersPhoto/"+nomImage),files.getBytes());
        //permet de recupèrer et  recrire l'image en ....

      clientRepository.save(client);
    }



    public Client authenticateClient(String telephone, String motDePasse) throws EntityNotFoundException {
        Client client = clientRepository.findByTelephoneAndMotDePasse(telephone,motDePasse);
        if (client == null) {
            throw  new EntityNotFoundException("le client avec le telephone" +telephone+ "et le mot de passe"+motDePasse+" sont inexistants");
        } else {
            return client;
//            throw new EntityNotFoundException("Numéro de téléphone ou mot de passe correct");
        }

    }
}
