package com.ism.repositories;

import java.util.List;

import com.ism.entities.Classe;

public interface ClasseRepository extends Repository<Classe> {
    List<Classe> findAll();
     List<Classe> findAllByEtudiant(int id);
    
}
