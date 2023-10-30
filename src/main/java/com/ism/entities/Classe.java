package com.ism.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classe {

    private int id;
    private String nomCl;
    private String filiere;
    private String niveau;
    
   public  Classe(int id){
      this.id=id;
    }
    
}
