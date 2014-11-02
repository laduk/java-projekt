package cz.muni.fi.pa165.creaturehunting.service.creature;

import java.util.List;

/**
 * Interface defines service method for Creature.
 *
 * @author Radoslav Zajonc
 */
public interface CreatureService {

    /**
     * Create the given creature.
     * @param creatureDTO The creature to be created.
     */
    public void create(CreatureDTO creatureDTO);
    
    /**
     * Update the given creature.
     * @param creatureDTO The creature to be updated.
     */
    public void update(CreatureDTO creatureDTO);
    
    /**
     * Delete the given creature.
     * @param creatureDTO The creature to be deleted.
     */
    public void delete(CreatureDTO creatureDTO);
    
    /**
     * Finds and returns the creature by ID.
     * @param id The ID of the creature to be returned.
     * @return The matching creature, otherwise null.
     */
    public CreatureDTO findCreature(long id);
    
    /**
     * Finds and returns a list of all creatures ordered by ID.
     * @return A list of all creatures.
     */
    public List<CreatureDTO> findAllCreatures();
}
