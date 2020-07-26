package com.example.lime.demolime.dao;


import com.example.lime.demolime.entity.Lime;

import java.util.List;

public interface ILimeDAO{
    List<Lime> getAllLimes();
    Lime getLimeById(Integer id);
    void addLime(Lime lime);
    void updateLime(Lime lime);
    void deleteLime(Integer id);
    boolean limeExists(Integer id);
}
