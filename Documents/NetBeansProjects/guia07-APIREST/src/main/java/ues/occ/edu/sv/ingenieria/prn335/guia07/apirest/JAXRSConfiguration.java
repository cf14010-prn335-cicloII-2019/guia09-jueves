package ues.occ.edu.sv.ingenieria.prn335.guia07.apirest;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS for the application.
 * @author Juneau
 */
@ApplicationPath("resources")
public class JAXRSConfiguration extends Application {
   
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
     resources.add(ues.occ.edu.sv.ingenieria.prn335.guia07.apirest.CaracteristicaAsientoFacadeREST.class);
     resources.add(ues.occ.edu.sv.ingenieria.prn335.guia07.apirest.SucursalFacadeREST.class);
        resources.add(ues.occ.edu.sv.ingenieria.prn335.guia07.apirest.resources.JavaEE8Resource.class);
     
    }
}
