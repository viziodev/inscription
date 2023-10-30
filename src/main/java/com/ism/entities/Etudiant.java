package com.ism.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {
    private int id;
    private String nomComplet;
    private String matricule;
    List<Classe> classes=new ArrayList<>();
}
