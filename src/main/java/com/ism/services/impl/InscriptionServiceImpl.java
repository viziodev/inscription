package com.ism.services.impl;

import java.util.Date;

import com.ism.entities.Classe;
import com.ism.entities.Etudiant;
import com.ism.entities.Inscription;
import com.ism.repositories.ClasseRepository;
import com.ism.repositories.EtudiantRepository;
import com.ism.repositories.InscriptionRepository;
import com.ism.services.InscriptionService;

public class InscriptionServiceImpl implements InscriptionService {
    

    private InscriptionRepository inscriptionRepository;
     private EtudiantRepository etudiantRepository;
     private ClasseRepository classeRepository;

     public InscriptionServiceImpl(InscriptionRepository inscriptionRepository, EtudiantRepository etudiantRepository,
            ClasseRepository classeRepository) {
        this.inscriptionRepository = inscriptionRepository;
        this.etudiantRepository = etudiantRepository;
        this.classeRepository = classeRepository;
    }

    @Override
    public boolean inscription(Etudiant etudiant, Classe classe,double montant) {
            if(etudiant.getId()==0){
            int idEtudiant= etudiantRepository.insert(etudiant);
                etudiant.setId(idEtudiant);
            }
     
         Inscription inscription=new Inscription(0,new Date(),montant,classe,etudiant);
          return inscriptionRepository.insert(inscription)!=0;
    }

    @Override
    public Etudiant rechercherEtudiantParMatricule(String matricule) {
      Etudiant etudiant=etudiantRepository.findEtudiantByMatricule(matricule);
      etudiant.setClasses(classeRepository.findAllByEtudiant(etudiant.getId()));
      return etudiant;
    }
    
}
