package com.ism.services.impl;

import java.util.Arrays;
import java.util.List;

import com.ism.entities.Classe;
import com.ism.entities.Filiere;
import com.ism.entities.Niveau;
import com.ism.repositories.ClasseRepository;
import com.ism.services.ClasseService;

public class ClasseServiceImpl implements ClasseService {

  private ClasseRepository repository;

  
    public ClasseServiceImpl(ClasseRepository repository) {
    this.repository = repository;
    }

    @Override
    public List<Classe> listerClasse() {
       return repository.findAll();
    }

    @Override
    public boolean ajouterClasse(Classe classe) {
        return repository.insert(classe)!=0;
    }

    @Override
    public List<Filiere> listerFiliere() {
        return Arrays.asList(new Filiere("GLRS"),new Filiere("MAIE"),new Filiere("CDSD"));
    }

    @Override
    public List<Niveau> listerNiveaux() {
      
         return Arrays.asList(new Niveau("L1"),new Niveau("L2")
         ,new Niveau("L3"));
    }
    
}
