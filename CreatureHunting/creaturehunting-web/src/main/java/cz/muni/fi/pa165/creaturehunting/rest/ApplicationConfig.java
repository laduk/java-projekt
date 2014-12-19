/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.rest;


import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author laduska
 */
@javax.ws.rs.ApplicationPath("/rest")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(cz.muni.fi.pa165.creaturehunting.rest.AreaRest.class);
        resources.add(cz.muni.fi.pa165.creaturehunting.rest.CreatureRest.class);
    }
}
