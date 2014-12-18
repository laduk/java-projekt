package cz.muni.fi.pa165.creaturehunting.data.dao;

import cz.muni.fi.pa165.creaturehunting.data.entity.Creature;
import java.util.List;

/**
 * Interface of Data Access Object for Creature.
 * 
 * @author Matej Čižik
 * 
 */
public interface CreatureDAO {

    /**
     * Create the given creature in the database. The creature ID must not be > 0,
     * otherwise it will throw IllegalArgumentException. After creating,
     * the DAO will set the obtained ID in the given creature.
     * @param creature The creature to be in the database.
     * @throws IllegalArgumentException
     */
    public void createCreature(Creature creature) throws IllegalArgumentException;
    
    /**
     *Update the given creature in the database. The creature ID must be > 0,
     * otherwise it will throw IllegalArgumentException.
     * @param creature The creature to be updated in the database.
     * @throws IllegalArgumentException
     */
    public void updateCreature(Creature creature) throws IllegalArgumentException;
    
    /**
     * Delete the given creature from the database. After deleting,
     * the DAO will set the ID of the given creature to null.
     * @param creature The creature to be deleted from the database.
     */
    public void deleteCreature(Creature creature);
    
    /**
     * Returns the creature from the database matching the given ID, otherwise null.
     * @param id The ID of the creature to be returned.
     * @return The creature from the database matching the given ID, otherwise null.
     */
    public Creature findCreature(long id);
    
    /**
     * Returns a list of all creatures from the database ordered by creature ID. The list
     * is never null and is empty when the database does not contain any creature.
     * @return A list of all creatures from the database ordered by creature ID.
     */
    public List<Creature> findAllCreatures();
}
