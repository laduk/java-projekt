/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.rest;

import cz.muni.fi.pa165.creaturehunting.api.dto.AreaDTO;
import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.AreaService;
import java.util.ArrayList;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 *
 * @author Fita
 */
@Path("/areas")
@Component
@Singleton
public class AreaRest {
    
    private static final XmlWebApplicationContext app_config =
            new XmlWebApplicationContext();
    
    @Autowired
    private AreaService service;
    
    public AreaRest() {
        app_config.setNamespace("appContext");
    }    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAreas(){
        List<AreaDTO> areas = service.findAllAreas();
        
        GenericEntity<List<AreaDTO>> areaEntity = new GenericEntity<List<AreaDTO>>(areas) {
        };        
        return Response.status(Response.Status.OK).entity(areaEntity).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArea(@PathParam("id") Long id) {
        try{
            AreaDTO areaDTO = service.findArea(id);
            return Response.status(Response.Status.OK).entity(areaDTO).build();       
        }catch(DataAccessException ex){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addArea(AreaDTO areaDTO) {
        if (areaDTO.getListOfCreatures()==null) {
            List<CreatureDTO> creatures = new ArrayList();
            areaDTO.setListOfCreatures(creatures);
        }
        service.create(areaDTO);        
        return Response.status(Response.Status.CREATED).build(); 
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateArea(@PathParam("id") Long id,AreaDTO areaDTO){
        try{
           service.findArea(id); //overi jestli kreaturka je v db
           areaDTO.setId(id);
           service.update(areaDTO);
           return Response.status(Response.Status.OK).build();
       }catch(DataAccessException ex){
           return Response.status(Response.Status.NOT_FOUND).build();       
       }
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteArea(@PathParam("id") Long id){
        try{
            AreaDTO areaDTO = service.findArea(id);
            service.delete(areaDTO);
            return Response.status(Response.Status.OK).build();
        
        }catch(DataAccessException ex){            
            return Response.status(Response.Status.NOT_FOUND).build();        
        }
    }
}
