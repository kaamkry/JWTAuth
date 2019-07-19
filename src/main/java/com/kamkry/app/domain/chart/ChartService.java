package com.kamkry.app.domain.chart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartService {

    @Autowired
    private ChartDao chartDao;

    public Chart get(Integer id) {
        return chartDao.get(id);
    }

    public List<Chart> getByUserId(Integer id) {
        return chartDao.getByUserId(id);
    }

    public void save(Chart chart) {
        chartDao.save(chart);
    }

    public void update(Chart chart) {
        chartDao.update(chart);
    }

    public void delete(Chart chart) {
        chartDao.delete(chart);
    }

    public ChartType getChartType(Integer id) {
        return chartDao.getChartType(id);
    }

}
