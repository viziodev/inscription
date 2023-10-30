package com.ism;

import java.util.Scanner;

import com.ism.entities.Classe;
import com.ism.entities.Etudiant;
import com.ism.entities.Filiere;
import com.ism.entities.Niveau;
import com.ism.repositories.ClasseRepository;
import com.ism.repositories.DataBase;
import com.ism.repositories.EtudiantRepository;
import com.ism.repositories.InscriptionRepository;
import com.ism.repositories.impl.ClasseRepositoryImpl;
import com.ism.repositories.impl.EtudiantRepositoryImpl;
import com.ism.repositories.impl.InscriptionRepositoryImpl;
import com.ism.repositories.impl.MysqlImpl;
import com.ism.services.ClasseService;
import com.ism.services.InscriptionService;
import com.ism.services.impl.ClasseServiceImpl;
import com.ism.services.impl.InscriptionServiceImpl;

public class Main {
    public static void main(String[] args) {
        int choix;
        Scanner cs=new Scanner(System.in);
        DataBase dataBase=new MysqlImpl();
        ClasseRepository classeRepository=new ClasseRepositoryImpl(dataBase);
        InscriptionRepository inscriptionRepository=new InscriptionRepositoryImpl(dataBase);
        EtudiantRepository etudiantRepository=new EtudiantRepositoryImpl(dataBase);
        ClasseService classeService=new ClasseServiceImpl(classeRepository);
        InscriptionService inscriptionService=new InscriptionServiceImpl(inscriptionRepository,etudiantRepository,classeRepository);
        do {
            System.out.println("1-Ajouter une classe");
            System.out.println("2-Inscription");
            System.out.println("3-ReInscription");
            System.out.println("4-Lister les etudiants d'une classe");
            System.out.println("5-Quitter");
            System.out.println("Entrer votre choix ?");
                choix=cs.nextInt();
                switch (choix) {
                case 1 :
                  cs.nextLine();
                     System.out.println("Choisir une Filiere");
                     classeService.listerFiliere().forEach(System.out::println);
                     int idFiliere=cs.nextInt();

                     System.out.println("Choisir une Niveaux");
                     classeService.listerNiveaux().forEach(System.out::println);
                     int idNiveau=cs.nextInt();

                     Filiere filiereSelect=classeService.listerFiliere().get(idFiliere-1);
                     Niveau niveauSelect=classeService.listerNiveaux().get(idNiveau-1);
                     String nomCl=String.format("%s %s",niveauSelect.getLibelle(),filiereSelect.getLibelle());

                     Classe classe=new Classe(0,nomCl,niveauSelect.getLibelle(),filiereSelect.getLibelle());
                     if(classeService.ajouterClasse(classe)){
                        System.out.println("Classe enregister avec success");
                     }else{
                          System.out.println("Erreur insertion");
                     }

                   break;

                   case 2 :
                       cs.nextLine();
                       System.out.println("Entrer le Matricule");
                       String matricule=cs.nextLine();
                       System.out.println("Entrer le Nom et le Prenom");
                       String nomComplet=cs.nextLine();
                        System.out.println("Choisir une Classe");
                       classeService.listerClasse().forEach(System.out::println);
                        int idClasse=cs.nextInt();
                       System.out.println("Entrer le Montant de l'inscription");
                       double montant=cs.nextDouble();
                       if(inscriptionService.inscription(
                                new Etudiant(0,nomComplet,matricule,null),
                                new Classe(idClasse),
                                montant)){
                         System.out.println("Etudiant inscrit avec success");
                         }else{
                          System.out.println("Erreur insertion");
                        }

                    break;
                    case 3 :
                       cs.nextLine();
                       System.out.println("Entrer le Matricule");
                        matricule=cs.nextLine();
                       Etudiant etudiant= inscriptionService.rechercherEtudiantParMatricule(matricule);
                       if (etudiant!=null) {
                             System.out.println("Nom et Prenom"+etudiant.getNomComplet());
                             System.out.println("Les Classes de Etudiant ");
                             etudiant.getClasses().forEach(System.out::println);

                            System.out.println("Choisir une Classe");
                             classeService.listerClasse().forEach(System.out::println);
                              idClasse=cs.nextInt();
                          System.out.println("Entrer le Montant de l'inscription");
                             montant=cs.nextDouble();
                           if(inscriptionService.inscription(
                                etudiant,
                                new Classe(idClasse),
                                montant)){
                                System.out.println("Etudiant reinscrit avec success");
                         }else{
                          System.out.println("Erreur insertion");
                        }
                       } else {
                            System.out.println("Erreur de Matricule");
                       }
                        

                    break;
                }
        }while(choix!=5);
}
}