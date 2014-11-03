/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.creaturehunting.service.huntingexperience;

import cz.muni.fi.pa165.creaturehunting.service.weapon.WeaponDTO;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureDTO;
import java.util.List;

/**
 *
 * @author Fita
 */
public interface HuntingExperienceService {

    /**
     * Create a hunting experience.
     * @param huntExpDTO instance of Hunting experince 
     */
    public void create(HuntingExperienceDTO huntExpDTO);
    
    /**
     * Update the hunting experience.
     * @param huntExpDTO instance of Hunting experince 
     */
    public void update(HuntingExperienceDTO huntExpDTO);
    
    /**
     * Delete the hunting experience
     * @param huntExpDTO instance of Hunting experince 
     */
    public void delete(HuntingExperienceDTO huntExpDTO);
    
    /**
     * Find Hunting experience in database.
     * @param huntExpDTO instance of Hunting experince 
     * @return Hunting experience transefer object
     */
    public HuntingExperienceDTO findHuntExp(Long id);
    
    /**
     * Find all entities of Hunting experience in database.
     * @param huntExpDTO instance of Hunting experince 
     * @return list of  Hunting experience transefer objects
     */
    public List<HuntingExperienceDTO> findAllHuntExp();     
    
    /**
     * 
     * @param creatureDTO paramentr of Creature
     * @param effeciency paramentr of effeciency of the weapon
     * @return  list of weapon transefer object
     */
    public List<WeaponDTO> findEfficientWeapons(CreatureDTO creatureDTO, int effeciency);
}
