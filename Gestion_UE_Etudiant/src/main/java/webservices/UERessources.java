package webservices;

import javax.ws.rs.GET; 
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path; 
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType; 
import javax.ws.rs.core.Response;
import metiers.UniteEnseignementBusiness;
import entities.UniteEnseignement;

@Path("/UE")
public class UERessources {
    public UniteEnseignementBusiness ueBusiness = new UniteEnseignementBusiness();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeUE() {
        return Response
                .status(200)
                .entity(ueBusiness.getListeUE())
                .build();
    }

    @GET
    @Path("/{semestre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEBySemestre(@PathParam("semestre") int semestre) {
        return Response
                .status(200)
                .entity(ueBusiness.getUEBySemestre(semestre))
                .build();
    }

    @GET
    @Path("/{domaine}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEByDomaine(@PathParam("domaine") String domaine) {
        return Response
                .status(200)
                .entity(ueBusiness.getUEByDomaine(domaine))
                .build();
    }

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEByCode(@PathParam("code") int code) {
        return Response
                .status(200)
                .entity(ueBusiness.getUEByCode(code))
                .build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUE(UniteEnseignement ue) {
        boolean success = ueBusiness.addUniteEnseignement(ue);
        if (success) {
            return Response.status(201)
                    .entity("UE ajoutÃ©e")
                    .build();
        } else {
            return Response.status(500)
                    .entity("Ajout UE fail").build();
        }
    }

    @PUT
    @Path("/update/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUE(@PathParam("code") int code, UniteEnseignement updatedUE) {
        boolean success = ueBusiness.updateUniteEnseignement(code, updatedUE);
        if (success) {
            return Response.status(200)
                    .entity("UE mise Ã  jour")
                    .build();
        } else {
            return Response.status(404)
                    .entity("UE non trouvÃ©e").build();
        }
    }

    @DELETE
    @Path("/delete/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUE(@PathParam("code") int code) {
        boolean success = ueBusiness.deleteUniteEnseignement(code);
        if (success) {
            return Response.status(200)
                    .entity("UE supprimÃ©e")
                    .build();
        } else {
            return Response.status(404)
                    .entity("UE non trouvÃ©e").build();
        }
    }


}
