package com.ism.services;

import com.ism.entities.Classe;
import com.ism.entities.Etudiant;

public interface InscriptionService {
      boolean inscription(Etudiant etudiant,Classe classe,double montant) ;
      
    Etudiant rechercherEtudiantParMatricule(String matricule) ;
}
