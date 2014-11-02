/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.creaturehunting.huntingexperience;

import cz.muni.fi.pa165.creaturehunting.huntingexperience.HuntingExperience;

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
    public HuntingExperienceTrans transformToTrans(HuntingExperience huntExp){
        if (huntExp==null) {
            throw new NullPointerException("HuntingExperience given is null.");
        }
        
        HuntingExperienceTrans huntExpTrans = new HuntingExperienceTrans();
        huntExpTrans.setId(huntExp.getId());
        huntExpTrans.setWeapon(huntExp.getWeapon());
        huntExpTrans.setCreature(huntExp.getCreature());
        huntExpTrans.setDateOfExperience(huntExp.getDateOfExperience());
        huntExpTrans.setEfficiency(huntExp.getEfficiency());
        huntExpTrans.setDescription(huntExp.getDescription());
        
        return huntExpTrans;
    }
    
    /**
     * Transformate DTO to entity.
     * @param huntExpTrans This DTO will be transformed into entity.
     * @return Entity created from DTO.
     */
    public HuntingExperience transformToEntity(HuntingExperienceTrans huntExpTrans){
        if (huntExpTrans==null) {
            throw new NullPointerException("HuntingExperienceTrans given is null.");
        }
        
        HuntingExperience huntExp = new HuntingExperience();
        huntExp.setId(huntExpTrans.getId());
        huntExp.setWeapon(huntExpTrans.getWeapon());
        huntExp.setCreature(huntExpTrans.getCreature());
        huntExp.setDateOfExperience(huntExpTrans.getDateOfExperience());
        huntExp.setEfficiency(huntExpTrans.getEfficiency());
        huntExp.setDescription(huntExpTrans.getDescription());
        
        return huntExp;
    }
    
}
