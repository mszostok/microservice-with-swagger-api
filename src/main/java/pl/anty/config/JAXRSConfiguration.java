package pl.anty.config;

import io.swagger.jaxrs.config.BeanConfig;
import pl.anty.boundary.CatalogService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@ApplicationPath("rest")
public class JAXRSConfiguration extends Application {
    public JAXRSConfiguration() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setBasePath("http://localhost:8002/api");
        beanConfig.setResourcePackage("io.swagger.resources");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        addRestEndpoints(resources);

        return resources;
    }

    private void addRestEndpoints(Set<Class<?>> resources) {
        resources.add(CatalogService.class); // zdefiniowanie serwisów które wystawiamy (tutaj bezpośrednio)
    }
}
