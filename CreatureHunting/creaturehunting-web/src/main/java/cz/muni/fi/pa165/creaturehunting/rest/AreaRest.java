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
@Path("/areas")
@Component
@Singleton
public class AreaRest {
    
    @Autowired
    private AreaService service;
    
    @Context
    private UriInfo contextInfo;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AreaDTO> getAllAreas(){
        return service.findAllAreas();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AreaDTO getArea(@PathParam("id") Long id) {
        return service.findArea(id);
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
        return Response.created(contextInfo.getAbsolutePath()).build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateArea(@PathParam("id") Long id,AreaDTO areaDTO){
        areaDTO.setId(id);                
        service.update(areaDTO);
        return Response.created(contextInfo.getAbsolutePath()).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteArea(@PathParam("id") Long id){
        service.delete(service.findArea(id));
        return Response.status(Response.Status.OK).build();
    }
}
