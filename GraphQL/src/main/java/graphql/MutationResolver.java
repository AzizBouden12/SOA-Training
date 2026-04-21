package graphql;

// Import the business logic class responsible for managing "UniteEnseignement" data
import business.UniteEnseignementBusiness;
import business.ModuleBusiness;

// Import the GraphQL library class used to define the root of GraphQL mutations
import com.coxautodev.graphql.tools.GraphQLRootResolver;

// Import the entity class representing a Teaching Unit (Unité d’Enseignement)
import entities.UniteEnseignement;
import entities.Module;

/**
 * This class defines all GraphQL mutations related to the "UniteEnseignement" entity.
 * A mutation in GraphQL is used to perform write operations (create, update, delete)
 * as opposed to queries, which are for read operations.
 */
public class MutationResolver implements GraphQLRootResolver {

    // Attribute used to access the business logic (service layer)
    public UniteEnseignementBusiness helper;
    public ModuleBusiness aide;

    /**
     * Constructor of the resolver.
     * It initializes the business helper that manages the teaching units.
     */
    public MutationResolver() {
        helper = new UniteEnseignementBusiness();
        aide = new ModuleBusiness();
    }


    /**
     * GraphQL Mutation: addUniteEnseignement
     *
     * This method allows clients to add a new "Unité d’Enseignement" to the system.
     *
     * When a GraphQL mutation like the following is executed:
     *
     * mutation {
     *     addUniteEnseignement(
     *         code: 101,
     *         domaine: "Informatique",
     *         responsable: "Dr. Smith",
     *         credits: 4,
     *         semestre: 2
     *     )
     * }
     *
     * GraphQL calls this Java method to create and save a new teaching unit.
     */
    public boolean addUniteEnseignement(int code, String domaine, String responsable, int credits, int semestre) {
        // Create a new UniteEnseignement object with the provided parameters
        UniteEnseignement ue = new UniteEnseignement(code, domaine, responsable, credits, semestre);

        // Call the business logic method to add the new unit
        // The helper handles the actual persistence or logic of adding the entity
        return helper.addUniteEnseignement(ue);
    }

    public boolean addModule(String matricule, String libelle, int corf, int volumeHoraire, int codeUE, String type){
        UniteEnseignement uniteEnseignement = helper.getUEByCode(codeUE);
        if (uniteEnseignement == null) {
            return false;
        }

        Module.TypeModule typeModule;
        try {
            typeModule = Module.TypeModule.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }

        Module module = new Module(matricule, libelle, corf, volumeHoraire, typeModule, uniteEnseignement);
        return aide.addModule(module);
    }

    public boolean updateModule(String matricule, String libelle, Integer corf, Integer volumeHoraire, Integer codeUE, String type){
        Module existingModule = aide.getModuleByMatricule(matricule);
        if (existingModule == null) {
            return false;
        }

        if (libelle != null) {
            existingModule.setNom(libelle);
        }
        if (corf != null) {
            existingModule.setCoefficient(corf);
        }
        if (volumeHoraire != null) {
            existingModule.setVolumeHoraire(volumeHoraire);
        }
        if (codeUE != null) {
            UniteEnseignement ue = helper.getUEByCode(codeUE);
            if (ue == null) {
                return false;
            }
            existingModule.setUniteEnseignement(ue);
        }
        if (type != null) {
            try {
                existingModule.setType(Module.TypeModule.valueOf(type.toUpperCase()));
            } catch (IllegalArgumentException e) {
                return false;
            }
        }

        return aide.updateModule(matricule, existingModule);
    }

    public boolean deleteModule(String matricule){
        return aide.deleteModule(matricule);
    }

    public boolean updateUE(int code, String domaine, String responsable, Integer credits, Integer semestre) {
        UniteEnseignement existingUE = helper.getUEByCode(code);
        if (existingUE == null) {
            return false;
        }

        if (domaine != null) {
            existingUE.setDomaine(domaine);
        }
        if (responsable != null) {
            existingUE.setResponsable(responsable);
        }
        if (credits != null) {
            existingUE.setCredits(credits);
        }
        if (semestre != null) {
            existingUE.setSemestre(semestre);
        }

        return helper.updateUniteEnseignement(code, existingUE);
    }

    public boolean deleteUE(int code) {
        return helper.deleteUniteEnseignement(code);
    }




}
