/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.rest;

import cz.muni.fi.pa165.creaturehunting.api.dto.WeaponDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.WeaponService;
import java.util.List;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Fita
 */
@Path("/weapons")
@Component
@Singleton
public class WeaponRest {
    
    @Autowired
    private WeaponService service;
    
    @Context
    private UriInfo contextInfo;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<WeaponDTO> getAllWeapons(){
        return service.findAllWeapons();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public WeaponDTO getWeapon(@PathParam("id") Long id) {
        return service.findWeapon(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addWeapon(WeaponDTO weaponDTO) {
        service.create(weaponDTO);        
        return Response.created(contextInfo.getAbsolutePath()).build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateWeapon(@PathParam("id") Long id, WeaponDTO weaponDTO){
        weaponDTO.setId(id);                
        service.update(weaponDTO);
        return Response.created(contextInfo.getAbsolutePath()).build();
    }
    
        
    @DELETE
    @Path("/{id}")
    public Response deleteWeapon(@PathParam("id") Long id){
        service.delete(service.findWeapon(id));
        return Response.status(Response.Status.OK).build();
    }
    
}
