package com.kamkry.app.domain.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {

    @Autowired
    private OperationDao operationDao;

    public Operation get(Integer id) {
        return operationDao.get(id);
    }

    public List<Operation> getByUserId(Integer id) {
        return operationDao.getByUserId(id);
    }

    public void save(Operation operation) {
        operationDao.save(operation);
    }

    public void update(Operation operation) {
        operationDao.update(operation);
    }

    public void delete(Operation operation) {
        operationDao.disable(operation);
    }

    public OperationType getOperationType(Integer id) {
        return operationDao.getOperationType(id);
    }
}
