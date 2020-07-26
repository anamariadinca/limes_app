package com.example.lime.demolime.service;

import com.example.lime.demolime.dao.ILimeDAO;
import com.example.lime.demolime.entity.Lime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimeService implements ILimeService {
    @Autowired
    private ILimeDAO limeDAO;

    @Override
    public List<Lime> getAllLimes() {
        return limeDAO.getAllLimes();
    }

    @Override
    public Lime getLimeById(Integer id) {
        Lime lime = limeDAO.getLimeById(id);
        return lime;
    }

    @Override
    public synchronized boolean addLime(Lime lime) {
        if(limeDAO.limeExists(lime.getId())){
            return false;
        }
        else{
            limeDAO.addLime(lime);
            return true;
        }
    }

    @Override
    public void updateLime(Lime lime) {
        limeDAO.updateLime(lime);
    }

    @Override
    public void deleteLime(Integer id) {
        limeDAO.deleteLime(id);
    }
}
