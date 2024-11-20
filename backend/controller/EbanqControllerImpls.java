package com.example.ebanque.controller;

import com.example.ebanque.entities.*;
import com.example.ebanque.repository.ClientRepository;
import com.example.ebanque.services.EbanqSerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")//tu change avec ton port
public class EbanqControllerImpls {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private EbanqSerImpl ebanqSer;



    @PostMapping("/createCompte")
    public ResponseEntity<Compte> saveCompte(@RequestBody Map<String, Object> request) {
        String nom = (String) request.get("nom");
        String prenom = (String) request.get("prenom");
        String telephone = (String) request.get("telephone");
        String deviceMonnaie = (String) request.get("deviceMonnaie");
        String motDePasse = (String) request.get("motDePasse");
        String typeCompte = (String) request.get("typeCompte");
        Double decouvert = (Double) request.get("decouvert");
        Double taux = (Double) request.get("taux");

        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setTelephone(telephone);

        Compte cp = null;
        if (client.getNom() != null) {
            if (typeCompte.equals("e")) {
                System.out.println("Compte épargne créé");
                cp = ebanqSer.creerCompteEpargne(deviceMonnaie, motDePasse, client, taux);
            } else if (typeCompte.equals("c")) {
                System.out.println("Compte courant créé");
                cp = ebanqSer.creerCompteCourant(deviceMonnaie, motDePasse, client, decouvert);
            }
        }

        return cp != null ? ResponseEntity.ok(cp) : ResponseEntity.badRequest().build();
    }



    @RequestMapping(value = "/upload/{id}")
    public String UploaderPhoto(@RequestParam("files") MultipartFile files, @PathVariable("id") Long id) throws IOException {
//      System.out.println("affichons le id" +id);
        ebanqSer.UploaderPhoto(files, id);
        return "";
    }



    @PostMapping("/login")
    public ResponseEntity<Client> login(@RequestBody Map<String, String> request) {
        String telephone = request.get("telephone");
        String motDePasse = request.get("motDePasse");

        Client client = ebanqSer.authenticateClient(telephone, motDePasse);
        if (client != null) {
            System.out.println("Connexion réussie pour: " + telephone);
            return ResponseEntity.ok(client); // Retourne le client en cas de succès
        } else {
            System.out.println("Numéro de téléphone ou mot de passe incorrect pour: " + telephone);
            return ResponseEntity.status(401).build();
        }
    }


    @RequestMapping(value = "/oper", method = RequestMethod.POST)
    public void createOperation(Operation operation, Compte compte) {

    }


    @PostMapping("/retrait")
    public ResponseEntity<Void> createRetrait(@RequestBody Map<String, Object> request) {
        Double montant = (Double) request.get("montant");
        String telephone = (String) request.get("telephone");
        String motDePasse = (String) request.get("motDePasse");

        try {
            ebanqSer.createRetrait(montant, telephone, motDePasse);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            System.out.println("Erreur lors du retrait: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/depot")
    public ResponseEntity<Void> createDepot(@RequestBody Map<String, Object> request) {
        Double montant = (Double) request.get("montant");
        String telephone = (String) request.get("telephone");

        try {
            ebanqSer.createDepot(montant, telephone);
            return ResponseEntity.ok().build(); // Retourne 200 OK si le dépôt est réussi
        } catch (RuntimeException e) {
            System.out.println("Erreur lors du dépôt: " + e.getMessage());
            return ResponseEntity.badRequest().build(); // Retourne 400 Bad Request en cas d'erreur
        }
    }


    @PostMapping("/transferer")
    public ResponseEntity<Void> transfert(@RequestBody Map<String, Object> request) {
        Double montant = (Double) request.get("montant");
        String tel1 = (String) request.get("tel1");
        String tel2 = (String) request.get("tel2");
        String motDePasse1 = (String) request.get("motDePasse1");

        try {
            ebanqSer.transferer(tel1, tel2, motDePasse1, montant);
            return ResponseEntity.ok().build(); // Retourne 200 OK si le transfert est réussi
        } catch (RuntimeException e) {
            System.out.println("Erreur lors du transfert: " + e.getMessage());
            return ResponseEntity.badRequest().build(); // Retourne 400 Bad Request en cas d'erreur
        }
    }




    @PostMapping("/transf")
    public ResponseEntity<Void> effectue(@RequestBody Map<String, Object> request) {
        Double montant = (Double) request.get("montant");
        String motDePasse = (String) request.get("motDePasse");
        String telephone = (String) request.get("telephone");

        try {
            ebanqSer.transfer(montant, motDePasse, telephone);
            return ResponseEntity.ok().build(); // Retourne 200 OK si le transfert est réussi
        } catch (RuntimeException e) {
            System.out.println("Erreur lors du transfert: " + e.getMessage());
            return ResponseEntity.badRequest().build(); // Retourne 400 Bad Request en cas d'erreur
        }
    }

}






