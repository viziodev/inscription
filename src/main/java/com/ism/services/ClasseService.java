package com.ism.services;

import java.util.List;

import com.ism.entities.Classe;
import com.ism.entities.Filiere;
import com.ism.entities.Niveau;

public interface ClasseService {
     List<Classe> listerClasse();
     List<Filiere> listerFiliere();
     List<Niveau> listerNiveaux();
    boolean ajouterClasse(Classe classe);
}
