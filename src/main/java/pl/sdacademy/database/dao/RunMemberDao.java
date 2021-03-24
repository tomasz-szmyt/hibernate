package pl.sdacademy.database.dao;


import pl.sdacademy.database.entity.RunMember;

import java.util.List;

public interface RunMemberDao {

    void save(RunMember RunMember); //C,U
    RunMember findById(Integer id); //R
    List<RunMember> findAll();      //R
    void deleteById(Integer id); //D

}