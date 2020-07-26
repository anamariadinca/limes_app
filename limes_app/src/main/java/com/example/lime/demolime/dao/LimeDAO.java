package com.example.lime.demolime.dao;
import com.example.lime.demolime.entity.Lime;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class LimeDAO implements ILimeDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Lime> getAllLimes() {
        String hql = "FROM Lime as lm ORDER BY lm.id";
        return (List<Lime>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Lime getLimeById(Integer id) {
        return entityManager.find(Lime.class, id);
    }

    @Override
    public void addLime(Lime lime) {
        entityManager.persist(lime);
    }

    @Override
    public void updateLime(Lime lime) {
        Lime updatedLime = getLimeById(lime.getId());
        updatedLime.setModel(lime.getModel());
        updatedLime.setBatteryLevel(lime.getBatteryLevel());
        updatedLime.setCoordinates(lime.getLat(), lime.getLon());
        entityManager.flush();
    }

    @Override
    public void deleteLime(Integer id) {
        entityManager.remove(getLimeById(id));
    }

    @Override
    public boolean limeExists(Integer id) {
        String hql = "From Lime as lm where lm.id =?0";
        int count = entityManager.createQuery(hql).setParameter(0, id).getResultList().size();
        return count > 0 ? true :  false;
    }

}
