/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.creaturehunting.api.dto;

import java.util.List;

/**
 *
 * @author Fita
 */
public class AreaDTO {
     private long id= -1;
     private String name;
     private String description;
     private double acreage;
     List<CreatureDTO> listOfCreatures;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAcreage() {
        return acreage;
    }

    public void setAcreage(double acreage) {
        this.acreage = acreage;
    }

    public List<CreatureDTO> getListOfCreatures() {
        return listOfCreatures;
    }

    public void setListOfCreatures(List<CreatureDTO> listOfCreatures) {
        this.listOfCreatures = listOfCreatures;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ( !(o instanceof AreaDTO) ) return false;

        final AreaDTO areaDTO = (AreaDTO) o;

        if (!new Long(id).equals(areaDTO.id)) return false;
        if (!name.equals(areaDTO.getName())) return false;
        if (!description.equals(areaDTO.getDescription()))return false;
        if (!new Double(acreage).equals(areaDTO.getAcreage())) return false;

        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "AreaDTO{" + "id=" + id + ", name=" + name + ", description=" 
                + description + ", acreage=" + acreage + ", listOfCreatures=" 
                + listOfCreatures + '}';
    }
     
     
}
