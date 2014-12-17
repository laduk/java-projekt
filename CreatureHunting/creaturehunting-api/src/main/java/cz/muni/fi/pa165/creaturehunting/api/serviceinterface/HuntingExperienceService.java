package cz.muni.fi.pa165.creaturehunting.api.serviceinterface;

import cz.muni.fi.pa165.creaturehunting.api.dto.HuntingExperienceDTO;
import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import java.util.List;

/**
 *
 * @author Fita
 */
public interface HuntingExperienceService {

    /**
     * Create a hunting experience.
     * @param huntExpDTO instance of Hunting experience 
     */
    public void create(HuntingExperienceDTO huntExpDTO);
    
    /**
     * Update the hunting experience.
     * @param huntExpDTO instance of Hunting experience 
     */
    public void update(HuntingExperienceDTO huntExpDTO);
    
    /**
     * Delete the hunting experience
     * @param huntExpDTO instance of Hunting experience 
     */
    public void delete(HuntingExperienceDTO huntExpDTO);
    
    /**
     * Find Hunting experience in database.
     * @param huntExpDTO instance of Hunting experience 
     * @return Hunting experience transfer object
     */
    public HuntingExperienceDTO findHuntExp(Long id);
    
    /**
     * Find all entities of Hunting experience in database.
     * @param huntExpDTO instance of Hunting experience 
     * @return list of  Hunting experience transfer objects
     */
    public List<HuntingExperienceDTO> findAllHuntExp();     
    
    /**
     * 
     * @param creatureDTO parameter of Creature
     * @param effeciency parameter of efficiency of the weapon
     * @return  list of weapon transfer object
     */
    public List<HuntingExperienceDTO> findEfficientWeaponExperiences(CreatureDTO creatureDTO, int effeciency);
    
}
