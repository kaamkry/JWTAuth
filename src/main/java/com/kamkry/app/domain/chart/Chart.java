package com.kamkry.app.domain.chart;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kamkry.app.domain.category.Category;
import com.kamkry.app.domain.user.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "chart")
public class Chart implements Serializable {
    private Integer id;
    private User user;
    private ChartType chartType;
    private Category category;
    private Integer nrInQueue;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Category getCategory() {
        return category;
    }

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public ChartType getChartType() {
        return chartType;
    }

    public void setChartType(ChartType chartType) {
        this.chartType = chartType;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getNrInQueue() {
        return nrInQueue;
    }

    public void setNrInQueue(Integer nrInQueue) {
        this.nrInQueue = nrInQueue;
    }
}
