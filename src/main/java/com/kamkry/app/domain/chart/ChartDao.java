package com.kamkry.app.domain.chart;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChartDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Chart get(Integer id) {
        return (Chart) sessionFactory.getCurrentSession()
                .createQuery("from Chart where id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }

    public List<Chart> getByUserId(Integer id) {
        return (List<Chart>) sessionFactory.getCurrentSession()
                .createQuery("from Chart where user.id=:id")
                .setParameter("id", id)
                .getResultList();
    }

    public void save(Chart chart) {
        sessionFactory.getCurrentSession().save(chart);
    }

    public void update(Chart chart) {
        sessionFactory.getCurrentSession().update(chart);
    }

    public void delete(Chart chart) {
        sessionFactory.getCurrentSession().update(chart);
    }

    public ChartType getChartType(Integer id) {
        return (ChartType) sessionFactory.getCurrentSession()
                .createQuery("from ChartType where id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }
}
