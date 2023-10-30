package com.ism.repositories.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ism.entities.Inscription;
import com.ism.repositories.DataBase;
import com.ism.repositories.InscriptionRepository;

public class InscriptionRepositoryImpl implements InscriptionRepository {
 private final String SQL_INSERT="INSERT INTO `inscription` (`date`, `montant`,etudiant_id,classe_id) VALUES (?,?,?,?)";
     private DataBase dataBase;
    public InscriptionRepositoryImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }
    @Override
    public int insert(Inscription data) {
       int lastInsertId=0;
            try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_INSERT);
                dataBase.getPs().setDate(1, new Date(data.getDate().getTime()));
                dataBase.getPs().setDouble(2,data.getMontant());
                dataBase.getPs().setDouble(3,data.getEtudiant().getId());
                dataBase.getPs().setDouble(4,data.getClasse().getId());
                dataBase.executeUpdate();
                  ResultSet rs=  dataBase.getPs().getGeneratedKeys();
                if(rs.next()){
                  lastInsertId=rs.getInt(1) ; 
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
          
            return lastInsertId; 
    }
    
}
