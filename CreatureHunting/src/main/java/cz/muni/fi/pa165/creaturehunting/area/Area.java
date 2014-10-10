package cz.muni.fi.pa165.creaturehunting.area;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class represents the Area.
 * 
 * @author Radoslav Zajonc
 */
@Entity
public class Area implements Serializable {
    
    /**
     * Unique identifier of area.
     * Id > 0 means, area is already created.
     */
    @Id
    @GeneratedValue
    private long id = -1;
    
    /**
     * Represents name of area.
     * Maximal length is 20 chars.
     */
    @Column(length=20)
    private String name;
    
    /**
     * This is description of area.
     * Maximal length is 200 chars.
     */
    @Column(length=200)
    private String description;
    
    /**
     * Acreage of area in meters squared.
     */
    @Column
    private double acreage;
    
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
    
    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Area other = (Area) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Area [id=" + id + ", name=" + name + ", description=" + description + ", acreage=" + acreage + "m^2]";
    }
    
}
