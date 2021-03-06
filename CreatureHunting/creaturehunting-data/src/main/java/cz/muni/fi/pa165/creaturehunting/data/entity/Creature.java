package cz.muni.fi.pa165.creaturehunting.data.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * This class represents a Creature
 * 
 * @author Matej Čižik
 */
@Entity
public class Creature implements Serializable {
    
    /**
     * Unique identifier of creature.
     * Id > 0 means that the creature is already created.
     */
    @Id
    @GeneratedValue
    private long id = -1;
    
     /**
     * Represents name of creature.
     * Maximal length is 20 chars.
     */
    @Column(length=20)
    private String name;
    
    /**
     * Height of the creature in cm.
     */
    @Column
    private int height;
    
    /**
     * Weight of the creature in kg.
     */
    @Column
    private int weight;
    
    /**
     * Agility of the creature in %.
     */
    @Column
    private int agility;
    
    /**
     * Areas where the creature was spotted.
     */
    @ManyToMany
    @JoinTable(
      name="CREATURE_AREA",
      joinColumns={@JoinColumn(name="CREATURE_ID", referencedColumnName="ID")},
      inverseJoinColumns={@JoinColumn(name="AREA_ID", referencedColumnName="ID")})
    private List<Area> listOfAreas;
    
    /**
     * List of Hunting Experiences for removing.
     */
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy="creature")
    private List<HuntingExperience> listOfHuntingExperiences;
    
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height must be positive number or zero.");
        } else {
            this.height = height;
        }
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight must be positive number or zero.");
        } else {
            this.weight = weight;
        }
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        if (agility < 0) {
            throw new IllegalArgumentException("Agility must be between 0 - 100.");
        } else if (agility > 100) {
            throw new IllegalArgumentException("Agility must be between 0 - 100.");
        } else {
            this.agility = agility;
        }
    }

    public List<Area> getListOfAreas() {
        return listOfAreas;
    }

    public void setListOfAreas(List<Area> listOfAreas) {
        this.listOfAreas = listOfAreas;
    }

    public List<HuntingExperience> getListOfHuntingExperiences() {
        return listOfHuntingExperiences;
    }

    public void setListOfHuntingExperiences(List<HuntingExperience> listOfHuntingExperiences) {
        this.listOfHuntingExperiences = listOfHuntingExperiences;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Creature other = (Creature) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Creature [" + "id=" + id + ", name=" + name + ", height=" + height + " cm"
                + " weight=" + weight + " kg, agility=" + agility + " %]";
    }
}
