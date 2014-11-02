/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.creaturehunting.service.huntingexperience;

//import cz.muni.fi.pa165.creaturehunting.service.weapon.WeaponDTO;
import cz.muni.fi.pa165.creaturehunting.dao.huntingexperience.HuntingExperience;

/**
 *
 * @author Fita
 */
public class HuntingExperienceTransformation {
    
    /**
     * Transformate entity to DTO object.
     * @param huntExp This entity will be transformed into DTO.
     * @return DTO object that was created.
     */
    public HuntingExperienceDTO transformToTrans(HuntingExperience huntExp){
        if (huntExp==null) {
            throw new NullPointerException("HuntingExperience given is null.");
        }
        
        HuntingExperienceDTO huntExpDTO = new HuntingExperienceDTO();
        huntExpDTO.setId(huntExp.getId());
        huntExpDTO.setWeapon(huntExp.getWeapon());
        huntExpDTO.setCreature(huntExp.getCreature());
        huntExpDTO.setDateOfExperience(huntExp.getDateOfExperience());
        huntExpDTO.setEfficiency(huntExp.getEfficiency());
        huntExpDTO.setDescription(huntExp.getDescription());
        
        return huntExpDTO;
    }
    
    /**
     * Transformate DTO to entity.
     * @param huntExpDTO This DTO will be transformed into entity.
     * @return Entity created from DTO.
     */
    public HuntingExperience transformToEntity(HuntingExperienceDTO huntExpDTO){
        if (huntExpDTO==null) {
            throw new NullPointerException("HuntingExperienceTrans given is null.");
        }
        
        HuntingExperience huntExp = new HuntingExperience();
        huntExp.setId(huntExpDTO.getId());
        huntExp.setWeapon(huntExpDTO.getWeapon());
        huntExp.setCreature(huntExpDTO.getCreature());
        huntExp.setDateOfExperience(huntExpDTO.getDateOfExperience());
        huntExp.setEfficiency(huntExpDTO.getEfficiency());
        huntExp.setDescription(huntExpDTO.getDescription());
        
        return huntExp;
    }
    
}