package com.kamkry.app.domain.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    @Autowired
    private OperationDao operationDao;

    public Operation get(Integer id) {
        return operationDao.get(id);
    }

    public void save(Operation operation) {
        operationDao.save(operation);
    }

    public void update(Operation operation) {
        operationDao.update(operation);
    }

    public void delete(Operation operation) {
        operationDao.delete(operation);
    }
}
