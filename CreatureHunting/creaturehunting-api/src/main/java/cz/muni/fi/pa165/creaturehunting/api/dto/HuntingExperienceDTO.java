/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.creaturehunting.api.dto;


import java.util.Date;

/**
 * Data Transfer Object for Hunting Experience.
 * @author Fita
 */
public class HuntingExperienceDTO {
    private long id = -1;
    private WeaponDTO weapon;
    private CreatureDTO creature;
    private Date dateOfExperience;
    private int efficiency;
    private String description;

    public void setId(long id) {
        this.id = id;
    }

    public void setWeapon(WeaponDTO weapon) {
        this.weapon = weapon;
    }

    public void setCreature(CreatureDTO creature) {
        this.creature = creature;
    }

    public void setDateOfExperience(Date dateOfExperience) {
        this.dateOfExperience = dateOfExperience;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public WeaponDTO getWeapon() {
        return weapon;
    }

    public CreatureDTO getCreature() {
        return creature;
    }

    public Date getDateOfExperience() {
        return dateOfExperience;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public String getDescription() {
        return description;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ( !(o instanceof HuntingExperienceDTO) ) return false;

        final HuntingExperienceDTO huntExpDTO = (HuntingExperienceDTO) o;

        if (!new Long(id).equals(huntExpDTO.id)) return false;
        if (!dateOfExperience.equals(huntExpDTO.dateOfExperience)) return false;
        if (!(efficiency==huntExpDTO.efficiency)) return false;
        if (!description.equals(huntExpDTO.description)) return false;

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
        return "HuntingExperienceDTO{" + "id=" + id + ", weapon=" + weapon + ","
                + " creature=" + creature + ", dateOfExperience=" +
                dateOfExperience + ", efficiency=" + efficiency +
                ", description=" + description + '}';
    } 
    
    
}
