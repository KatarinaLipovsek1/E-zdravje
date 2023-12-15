package rest;

import dao.PacientDao;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import vao.Pacient;

import java.util.Collection;
import java.util.logging.Logger;

@Path("/pacienti")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacientResource {
    private static final Logger log=Logger.getLogger("PacientResource");

    @EJB
    PacientDao dao;

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/info")
    public String info() {
        return "PacientResource";
    }

    @GET
    public Collection<Pacient> getVsiPacienti() {
        return dao.getAll();
    }

    @GET
    @Path("/{email}")
    public Pacient getPacient(@PathParam("email") String email) {
        return dao.find(email);
    }

    @POST
    public void dodajPacienta(Pacient p) throws Exception {
        dao.save(p);
    }

    @PUT
    @Path("/{emailPacienta}/{emailZdravnika}")
    public void izberiZdravnika(@PathParam("emailPacienta") String pacientEmail, @PathParam("emailZdravnika") String zdravnikEmail) throws Exception {
        dao.shraniPacientaPoIzbiriZdravnika(pacientEmail, zdravnikEmail);
    }

}
