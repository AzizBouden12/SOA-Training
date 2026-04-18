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
import metiers.ModuleBusiness;
import entities.Module;
import entities.UniteEnseignement;

@Path("/module")
public class ModuleRessources {
    public ModuleBusiness moduleBusiness = new ModuleBusiness();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeModules() {
        return Response
                .status(200)
                .entity(moduleBusiness.getAllModules())
                .build();
    }

    @GET
    @Path("/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByType(@PathParam("type") Module.TypeModule type) {
        return Response
                .status(200)
                .entity(moduleBusiness.getModulesByType(type))
                .build();
    }

    @GET
    @Path("/{domaine}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByMatricule(@PathParam("domaine") String matricule) {
        return Response
                .status(200)
                .entity(moduleBusiness.getModuleByMatricule(matricule))
                .build();
    }

    @GET
    @Path("/{ue}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByUE(@PathParam("ue") UniteEnseignement ue) {
        return Response
                .status(200)
                .entity(moduleBusiness.getModulesByUE(ue))
                .build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addModule(Module module) {
        boolean success = moduleBusiness.addModule(module);
        if (success) {
            return Response.status(201)
                    .entity("Module ajoutÃ©")
                    .build();
        } else {
            return Response.status(500)
                    .entity("Ajout Module fail").build();
        }
    }

    @PUT
    @Path("/update/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateModule(@PathParam("code") int code, Module updatedModule) {
        boolean success = moduleBusiness.updateModule(updatedModule.getMatricule(), updatedModule);
        if (success) {
            return Response.status(200)
                    .entity("Module mis Ã  jour")
                    .build();
        } else {
            return Response.status(404)
                    .entity("Module non trouvÃ©").build();
        }
    }

    @DELETE
    @Path("/delete/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteModule(@PathParam("matricule") String matricule) {
        boolean success = moduleBusiness.deleteModule(matricule);
        if (success) {
            return Response.status(200)
                    .entity("Module supprimÃ©")
                    .build();
        } else {
            return Response.status(404)
                    .entity("Module non trouvÃ©").build();
        }
    }


}
