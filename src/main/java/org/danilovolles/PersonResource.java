package org.danilovolles;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("pessoa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    public List<Person> getPerson() {
        return Person.listAll();
    }

    @POST
    @Transactional
    public Person createPerson(Person person) {
        person.id = null;
        person.persist();
        return person;
    }

    @PUT
    @Transactional
    public Person updatePerson(Person person) {

        Person personEntity = Person.findById(person.id);
        personEntity.name = person.name;
        personEntity.birthYear = person.birthYear;

        personEntity.persist();
        return personEntity;
    }

    @DELETE
    @Transactional
    public void deletePerson(int id) {
        Person.deleteById(id);
    }

    @GET
    @Path("year")
    public List<Person> findByYear(@QueryParam("year") int year) {
        return Person.findByYear(year);
    }
}
