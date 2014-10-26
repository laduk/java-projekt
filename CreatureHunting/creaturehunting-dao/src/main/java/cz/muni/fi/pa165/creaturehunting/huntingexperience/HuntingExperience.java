/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.huntingexperience;

import cz.muni.fi.pa165.creaturehunting.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.weapon.Weapon;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class representing hunting exprience.
 * @author laduska
 */

@Entity
public class HuntingExperience implements Serializable {
    
    /**
     * Indentifier that is automatically generated.
     */    
    @Id
    @GeneratedValue
    private long id = -1;    

    /**
     * Weapon that should be stored.
     */   
    @OneToOne
    private Weapon weapon;
    
    /**
     * Creature that should be stored.
     */
    @OneToOne
    private Creature creature;    
    
    /**
     * Date from which is the experience.
     */
    @Temporal(TemporalType.DATE)
    private Date dateOfExperience;    
    
    /**
     * Number that measure effeciency in % of the attack can have value from 0 to 100.
     * Sure death of the creature is value 100.
     */
    @Column
    private int efficiency;

    /**
     * Text description of the experience.
     */   
    @Column(length=1000)
    private String description;
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateOfExperience() {
        return dateOfExperience;
    }

    public void setDateOfExperience(Date dateOfExperience) {
        this.dateOfExperience = dateOfExperience;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
         if (efficiency < 0 || efficiency > 100) {
            throw new IllegalArgumentException("Efficiency is expressed in percent.");
        } else {
            this.efficiency = efficiency;
        }      
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
   
     public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {    
        this.weapon = weapon;
    }

    
    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }
    
    
    
     @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
     

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HuntingExperience other = (HuntingExperience) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
      
    @Override
    public String toString() {
        return "HuntingExperience [" + "id=" + id + ", weapon=" + weapon + ", creature=" + creature + ", "
                + "date of experience:" + dateOfExperience + ", description=" + description + ", "  + " efficiency="  + efficiency +  "% ]";
    }
        
    
}
