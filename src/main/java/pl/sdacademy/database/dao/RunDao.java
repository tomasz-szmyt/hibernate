package pl.sdacademy.database.dao;

import pl.sdacademy.database.entity.Run;

import java.util.List;

public interface RunDao {

    void save(Run run); //C,U
    Run findById(Integer id); //R
    List<Run> findAll();
    void deleteById(Integer id); //D

}
