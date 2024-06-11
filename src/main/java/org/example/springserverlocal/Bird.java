package org.example.springserverlocal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryEntity;
import com.querydsl.core.annotations.QueryType;
import jakarta.persistence.*;

@Entity
@QueryEntity
@Table(name = "birds")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Bird {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "clientsIdSeq", sequenceName = "clients_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientsIdSeq")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "tree")
    private String tree;
    @Column(name = "population")
    private int population;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTree(String tree) {
        this.tree = tree;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTree() {
        return tree;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tree='" + tree + '\'' +
                ", population='" + population + '\'' +
                '}';
    }
}
