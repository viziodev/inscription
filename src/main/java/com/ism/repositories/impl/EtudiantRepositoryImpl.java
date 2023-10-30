package com.ism.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ism.entities.Etudiant;
import com.ism.repositories.DataBase;
import com.ism.repositories.EtudiantRepository;

public class EtudiantRepositoryImpl implements EtudiantRepository {
     private final String SQL_INSERT="INSERT INTO `etudiant` (`matricule`,  `nomComplet`) VALUES (?,?)";
     private final String SQL_SELECT_BY_MATRICULE="select * from etudiant  where matricule like ?";

     private DataBase dataBase;
    public EtudiantRepositoryImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }
    @Override
    public int insert(Etudiant data) {
        int lastInsertId=0;
            try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_INSERT);
                dataBase.getPs().setString(1,data.getMatricule());
                dataBase.getPs().setString(2,data.getNomComplet());
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
    @Override
    public Etudiant findEtudiantByMatricule(String matricule) {
       
             Etudiant data=null;
                  try {
                        dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_SELECT_BY_MATRICULE);
                        dataBase.getPs().setString(1,matricule);
                        ResultSet resultSet=dataBase.executeSelect();
                        if (resultSet.next()) {
                             data=new Etudiant( resultSet.getInt("id")
                                            , resultSet.getString("nomComplet"),
                                            resultSet.getString("matricule"),null);
                               
                          }
                       resultSet.close();
                       dataBase.closeConnexion();
                    } catch (SQLException e) {
                        System.out.println("Erreur execution de Requete");
                    }
                return data;
        }
    
    
}
