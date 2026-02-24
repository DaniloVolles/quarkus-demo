package org.danilovolles;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Person extends PanacheEntity {
    /*
    simmmm, os atributos são públicos, os criadores do panache reconheceram
    que geralmente a gente sempre cria os getters e setters para todos os
    atributos sem maiores cuidados. Que diferença isso faz então ?
    nesse caso, os criadores do panache apenas nos falaram que vão cuidar do
    resto se deixarmos tudo público... devemos confiar ?
     */
    public String name;
    public int birthYear;

    public static List<Person> findByYear(int year) {
        return find("birthYear", year).list();
    }
}
