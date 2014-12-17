package cz.muni.fi.pa165.creaturehunting.service.datatransformation;

import cz.muni.fi.pa165.creaturehunting.data.entity.Creature;
import cz.muni.fi.pa165.creaturehunting.data.entity.HuntingExperience;
import cz.muni.fi.pa165.creaturehunting.data.entity.Weapon;
import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.api.dto.HuntingExperienceDTO;
import cz.muni.fi.pa165.creaturehunting.api.dto.WeaponDTO;

/**
 *
 * @author Fita
 */
public class HuntingExperienceTransformation {
    
    /**
     * Transforms entity to DTO object.
     * @param huntExp This entity will be transformed into DTO.
     * @return DTO object that was created.
     */
    public static HuntingExperienceDTO transformToDTO(HuntingExperience huntExp){
        if (huntExp==null) {
            throw new NullPointerException("HuntingExperience given is null.");
        }
        
        HuntingExperienceDTO huntExpDTO = new HuntingExperienceDTO();
        huntExpDTO.setId(huntExp.getId());
        
        Weapon weapon = huntExp.getWeapon();
        if (weapon!=null) {
            huntExpDTO.setWeapon(WeaponTransformation.transformToDTO(weapon));
        }
        
        Creature creature = huntExp.getCreature();
        if (creature!=null) {
            huntExpDTO.setCreature(CreatureTransformation.transformToDTO(creature));
        }
        huntExpDTO.setDateOfExperience(huntExp.getDateOfExperience());
        huntExpDTO.setEfficiency(huntExp.getEfficiency());
        huntExpDTO.setDescription(huntExp.getDescription());
        
        return huntExpDTO;
    }
    
    /**
     * Transforms DTO to entity.
     * @param huntExpDTO This DTO will be transformed into entity.
     * @return Entity created from DTO.
     */
    public static HuntingExperience transformToEntity(HuntingExperienceDTO huntExpDTO){
        if (huntExpDTO==null) {
            throw new NullPointerException("HuntingExperienceTrans given is null.");
        }
        
        HuntingExperience huntExp = new HuntingExperience();
        huntExp.setId(huntExpDTO.getId());
        WeaponDTO weaponDTO = huntExpDTO.getWeapon();
        if (weaponDTO!=null) {
            huntExp.setWeapon(WeaponTransformation.transformToEntity(weaponDTO));
        }
        CreatureDTO creatureDTO = huntExpDTO.getCreature();
        if (creatureDTO!=null) {
            huntExp.setCreature(CreatureTransformation.transformToEntity(creatureDTO));
        }        
        huntExp.setDateOfExperience(huntExpDTO.getDateOfExperience());
        huntExp.setEfficiency(huntExpDTO.getEfficiency());
        huntExp.setDescription(huntExpDTO.getDescription());
        
        return huntExp;
    }
    
}
