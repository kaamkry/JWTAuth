package com.kamkry.app.domain.operation;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "operation_type")
public class OperationType implements Serializable {

    private Integer id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
