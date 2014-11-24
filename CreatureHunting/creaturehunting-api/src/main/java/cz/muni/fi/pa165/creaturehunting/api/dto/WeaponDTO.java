/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.api.dto;

/**
 *
 * @author laduska
 */
public class WeaponDTO {

    private long id = -1;
    private String name;
    private int gunReach;
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WeaponDTO other = (WeaponDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.gunReach != other.gunReach) {
            return false;
        }
        if ((this.ammunition == null) ? (other.ammunition != null) : !this.ammunition.equals(other.ammunition)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 31 * hash + (this.ammunition != null ? this.ammunition.hashCode() : 0);
        return hash;
    }

    
    
    @Override
    public String toString() {
        return "WeaponDTO{" + "id=" + id + ", name=" + name + ", gunReach=" + gunReach + ", ammunition=" + ammunition + '}';
    }

           
    
}
