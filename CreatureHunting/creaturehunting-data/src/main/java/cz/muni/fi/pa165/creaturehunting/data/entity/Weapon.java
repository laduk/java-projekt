/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.creaturehunting.data.entity;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Class representing weapon.
 * @author Fita
 */
@Entity
public class Weapon {
    
    /**
     * Automatically generated unique identifier.
     */
    @Id
    @GeneratedValue
    private long id=-1;
    
    /**
     * Text name of the weapon that must be filled.
     */
    @Column(nullable=false)
    private String name;
    
    /**
     * Number that specify reach of the weapon.
     */
    private int gunReach;
    
    /**
     * Text where ammunition of the weapon will be stored.
     */
    @Column
    private String ammunition;

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

    public int getGunReach() {
        return gunReach;
    }

    public void setGunReach(int gunReach) {
        this.gunReach = gunReach;
    }

    public String getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(String ammunition) {
        this.ammunition = ammunition;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Weapon other = (Weapon) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Weapon{" + "id=" + id + ", name=" + name + ", gunReach=" + gunReach + ", ammunition=" + ammunition + '}';
    }
    
    
    
    
    
}
