package com.example.lime.demolime.service;


import com.example.lime.demolime.entity.Lime;

import java.util.List;

public interface ILimeService {
    List<Lime> getAllLimes();
    Lime getLimeById(Integer id);
    boolean addLime(Lime lime);
    void updateLime(Lime lime);
    void deleteLime(Integer id);
}
