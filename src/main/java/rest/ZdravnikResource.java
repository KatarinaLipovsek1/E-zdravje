package rest;

import dao.ZdravnikDao;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import vao.Zdravnik;
import java.util.Collection;
import java.util.logging.Logger;


@Path("/zdravniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ZdravnikResource {
    private static final Logger log=Logger.getLogger("ZdravnikResource");

    @EJB
    ZdravnikDao dao;

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/info")
    public String info() {
        return "ZdravnikResource";
    }

    @GET
    public Collection<Zdravnik> getVsiZdravniki() {
        return dao.getAll();
    }

    @GET
    @Path("/{email}")
    public Zdravnik getZdravnik(@PathParam("email") String email) {
        return dao.find(email);
    }

}
