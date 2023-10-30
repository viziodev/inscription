package com.ism.repositories;

import com.ism.entities.Etudiant;

public interface EtudiantRepository extends Repository<Etudiant> {
    Etudiant findEtudiantByMatricule(String matricule);
}
