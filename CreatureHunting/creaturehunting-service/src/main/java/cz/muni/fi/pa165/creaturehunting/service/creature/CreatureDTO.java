package cz.muni.fi.pa165.creaturehunting.service.creature;


import cz.muni.fi.pa165.creaturehunting.service.area.AreaDTO;
import java.util.List;

/**
 * Data Transfer Object for Creature.
 * 
 * @author Radoslav Zajonc
 */
public class CreatureDTO {
    
    /**
     * Unique identifier of creature.
     * Id > 0 means that the creature is already created.
     */
    private long id = -1;
    
    /**
     * Represents name of creature.
     */
    private String name;
    
    /**
     * Height of the creature in cm.
     */
    private int height;
    
    /**
     * Weight of the creature in kg.
     */
    private int weight;
    
    /**
     * Agility of the creature in %.
     */
    private int agility;
    
    /**
     * Areas where the creature was spotted.
     */
    private List<AreaDTO> listOfAreas;

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
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public List<AreaDTO> getListOfAreas() {
        return listOfAreas;
    }

    public void setListOfAreas(List<AreaDTO> listOfAreas) {
        this.listOfAreas = listOfAreas;
    }
    
    @Override
    public int hashCode() {
        final int prime = 41;
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
        CreatureDTO other = (CreatureDTO) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CreatureTrans [id=" + id + ", name=" + name + ", height=" + height + " cm"
                + " weight=" + weight + " kg, agility=" + agility + " %]";
    }
}
