package com.sumerge.grad.program.jpa.rest.student;

import com.sumerge.grad.program.jpa.entity.Student;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

import static com.sumerge.grad.program.jpa.constants.Constants.PERSISTENT_UNIT;
import static java.util.logging.Level.SEVERE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("students")
public class StudentResources {

    private static final Logger LOGGER = Logger.getLogger(StudentResources.class.getName());

    @PersistenceContext(unitName = PERSISTENT_UNIT)
    private EntityManager em;

    @GET
    public Response getAllStudents() {
        try {
            return Response.ok().
                    entity(em.createQuery("SELECT s FROM Student s", Student.class).getResultList()).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e).
                    build();
        }
    }
}
