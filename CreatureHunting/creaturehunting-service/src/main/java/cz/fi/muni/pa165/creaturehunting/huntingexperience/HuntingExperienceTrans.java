/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.creaturehunting.huntingexperience;

import cz.muni.fi.pa165.creaturehunting.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.weapon.Weapon;
import java.util.Date;

/**
 * Data Transfer Object for Hunting Experience.
 * @author Fita
 */
public class HuntingExperienceTrans {
    private long id = -1;
    private Weapon weapon;
    private Creature creature;
    private Date dateOfExperience;
    private int efficiency;
    private String description;

    public void setId(long id) {
        this.id = id;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setCreature(Creature creature) {
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

    public Weapon getWeapon() {
        return weapon;
    }

    public Creature getCreature() {
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
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if ( !(o instanceof HuntingExperienceTrans) ) return false;

        final HuntingExperienceTrans huntExpTrans = (HuntingExperienceTrans) o;

        if (!new Long(id).equals(new Long(huntExpTrans.id))) return false;
        if (!weapon.equals(huntExpTrans.weapon)) return false;
        if (!creature.equals(huntExpTrans.creature))return false;
        if (!dateOfExperience.equals(huntExpTrans.dateOfExperience)) return false;
        if (!(efficiency==huntExpTrans.efficiency)) return false;
        if (!description.equals(huntExpTrans.description)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    
}
