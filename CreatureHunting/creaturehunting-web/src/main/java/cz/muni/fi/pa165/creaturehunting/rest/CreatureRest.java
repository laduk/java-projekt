/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.rest;

import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.AreaService;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.CreatureService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author laduska
 */
@Path("/creatures")
public class CreatureRest {
    
    
    @Autowired
    CreatureService creatureService;
    
//    @Autowired
//    AreaService areaService;
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCreatureByID(@PathParam("id") long id){
        try{
            CreatureDTO creatureDto = creatureService.findCreature(id);
            return Response.status(Response.Status.OK).entity(creatureDto).build();
        
        }catch(DataAccessException ex){
            return Response.status(Response.Status.NOT_FOUND).build();
        }    
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCreature(CreatureDTO creatureDto){
        
        creatureService.create(creatureDto);
        return Response.status(Response.Status.CREATED).build();        
    }
    
    
    
   @PUT
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   @Path("{id}")
   public Response update(@PathParam("id") long id, CreatureDTO creatureDto){
   
       try{
           creatureService.findCreature(id); //overi jestli kreaturka je v db
           creatureService.update(creatureDto);
           return Response.status(Response.Status.OK).build();
       }catch(DataAccessException ex){
           return Response.status(Response.Status.NOT_FOUND).build();
       
       }
   
   
   }
    
    
    
    
    
    
    
    
}
