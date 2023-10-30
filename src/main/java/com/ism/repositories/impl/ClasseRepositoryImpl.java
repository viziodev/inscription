package com.ism.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ism.entities.Classe;
import com.ism.repositories.ClasseRepository;
import com.ism.repositories.DataBase;

public class ClasseRepositoryImpl implements ClasseRepository {
    private final String SQL_INSERT="INSERT INTO `classe` (`nomCl`,  `filiere`,  `niveau`) VALUES (?,?,?)";
    private final String  SQL_SELECT_ALL="SELECT * FROM classe";
    private final String  SQL_SELECT_BY_ETUDIANT="SELECT cl.* FROM inscription i, classe cl where i.classe_id=cl.id and i.etudiant_id=?";
    private DataBase dataBase;
    public ClasseRepositoryImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public int insert(Classe data) {
        int lastInsertId=0;
            try {
                dataBase.openConnexion();
                dataBase.initPreparedStatement(SQL_INSERT);
                dataBase.getPs().setString(1,data.getNomCl());
                dataBase.getPs().setString(2,data.getFiliere());
                dataBase.getPs().setString(3,data.getNiveau());
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
    public List<Classe> findAll() {
        ArrayList<Classe> datas=new ArrayList<>();
                  try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_SELECT_ALL);
                    ResultSet resultSet=dataBase.executeSelect();
                    while (resultSet.next()) {
                        
                        Classe data=new Classe( resultSet.getInt("id")
                                         , resultSet.getString("nomCl")
                                         ,resultSet.getString("filiere")
                                          ,resultSet.getString("niveau"));

                         datas.add(data);
                           
                      }
                   resultSet.close();
                   dataBase.closeConnexion();
                } catch (SQLException e) {
                    System.out.println("Erreur execution de Requete");
                }
            
            return datas;
    }

    @Override
    public List<Classe> findAllByEtudiant(int id) {
        ArrayList<Classe> datas=new ArrayList<>();
                  try {
                      dataBase.openConnexion();
                        dataBase.initPreparedStatement(SQL_SELECT_BY_ETUDIANT);
                        dataBase.getPs().setInt(1,id);
                    ResultSet resultSet=dataBase.executeSelect();
                    while (resultSet.next()) {
                        
                        Classe data=new Classe( resultSet.getInt("id")
                                         , resultSet.getString("nomCl")
                                         ,resultSet.getString("filiere")
                                          ,resultSet.getString("niveau"));

                         datas.add(data);
                           
                      }
                   resultSet.close();
                   dataBase.closeConnexion();
                } catch (SQLException e) {
                    System.out.println("Erreur execution de Requete");
                }
            
            return datas;
    }
    
}
