/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fi.muni.pa165.creaturehunting.huntingexperience;

import java.util.List;

/**
 *
 * @author Fita
 */
public interface HuntingService {

    /**
     * Create a hunting experience.
     * @param huntExpTrans instance of Hunting experince 
     */
    public void create(HuntingExperienceTrans huntExpTrans);
    
    /**
     * Update the hunting experience.
     * @param huntExpTrans instance of Hunting experince 
     */
    public void update(HuntingExperienceTrans huntExpTrans);
    
    /**
     * Delete the hunting experience
     * @param huntExpTrans instance of Hunting experince 
     */
    public void delete(HuntingExperienceTrans huntExpTrans);
    
    /**
     * Find Hunting experience in database.
     * @param huntExpTrans instance of Hunting experince 
     * @return Hunting experience transefer object
     */
    public HuntingExperienceTrans findHuntExp(HuntingExperienceTrans huntExpTrans);
    
    /**
     * Find all entities of Hunting experience in database.
     * @param huntExpTrans instance of Hunting experince 
     * @return list of  Hunting experience transefer objects
     */
    public List<HuntingExperienceTrans> findAllHuntExp(HuntingExperienceTrans huntExpTrans);     
    
    /**
     * 
     * @param creatureTrans instance of creatureTrans
     * @param effeciency paramentr of effeciency of the weapon
     * @return  list of weapon transefer object
     */
    public List<WeaponTrans> findEfficientWeapons(CreatureTrans creatureTrans, int effeciency);
}
