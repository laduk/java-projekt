/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.huntingexperience;

import cz.muni.fi.pa165.creaturehunting.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.weapon.Weapon;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author laduska
 */

@Entity
public class HuntingExperience {
    
        
    @Id
    @GeneratedValue
    private long id = -1;    

       
    @OneToOne
    private Weapon weapon = null;
    
    
    @OneToOne
    private Creature creature = null;    
    
    @Temporal(TemporalType.DATE)
    private Date dateOfExperience;    
    
    @Column
    private int efficiency;

       
    @Column
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
        this.efficiency = efficiency;
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
        
    
}
