package com.ism.entities;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inscription {
    private int id;
    private Date date;
    private double montant;
    private Classe classe;
    private Etudiant etudiant;
}
