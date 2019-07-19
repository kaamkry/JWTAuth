package com.kamkry.app.domain.chart;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "chart_type")
public class ChartType implements Serializable {
    private Integer id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
