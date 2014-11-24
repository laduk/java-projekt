/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.creaturehunting.service.serviceimpl;

import cz.muni.fi.pa165.creaturehunting.service.datatransformation.HuntingExperienceTransformation;
import cz.muni.fi.pa165.creaturehunting.data.entity.HuntingExperience;
import cz.muni.fi.pa165.creaturehunting.data.dao.HuntingExperienceDAO;
import cz.muni.fi.pa165.creaturehunting.data.entity.Weapon;
import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.service.datatransformation.CreatureTransformation;
import cz.muni.fi.pa165.creaturehunting.api.dto.WeaponDTO;
import cz.muni.fi.pa165.creaturehunting.service.datatransformation.WeaponTransformation;
import cz.muni.fi.pa165.creaturehunting.api.dto.HuntingExperienceDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.HuntingExperienceService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class HuntingExperienceServiceImpl implements HuntingExperienceService {
    
    @Autowired
    private HuntingExperienceDAO hunExpDAO;
    
    /**
     *
     * @param hunExpDAO
     */
    public HuntingExperienceServiceImpl (HuntingExperienceDAO  hunExpDAO){
        if (hunExpDAO==null) {
            throw new NullPointerException("HunExpDAO mus not be null!");           
        }
        this.hunExpDAO = hunExpDAO;
    }    
    

    public void create(HuntingExperienceDTO huntExpDTO) {
        if (huntExpDTO==null) {
            throw new NullPointerException("HuntExpDTO must not be null!");
        }
        HuntingExperience huntExp =HuntingExperienceTransformation.
                transformToEntity(huntExpDTO);
        hunExpDAO.createHuntingExperience(huntExp);
    }

    public void update(HuntingExperienceDTO huntExpDTO) {
        if (huntExpDTO==null) {
            throw new NullPointerException("HuntExpDTO must not be null!");
        }
        HuntingExperience huntExp =  HuntingExperienceTransformation.
                transformToEntity(huntExpDTO);
        hunExpDAO.updateHuntingExperience(huntExp);
    }

    public void delete(HuntingExperienceDTO huntExpDTO) {
        if (huntExpDTO==null) {
            throw new NullPointerException("HuntExpDTO must not be null!");
        }
        hunExpDAO.deleteHuntingExperience(HuntingExperienceTransformation.
                    transformToEntity(huntExpDTO));        
    }

    public HuntingExperienceDTO findHuntExp(Long id) {
        if (id==null) {
            throw new NullPointerException("Id must not null!");
        }
        
        HuntingExperience huntExp = hunExpDAO.findHuntingExperience(id);       
        
        HuntingExperienceDTO huntExpDTO = HuntingExperienceTransformation.
                transformToDTO(huntExp);
        return huntExpDTO;
    }

    public List<HuntingExperienceDTO> findAllHuntExp() {
        List<HuntingExperienceDTO> listHuntExpsDTO = new ArrayList<HuntingExperienceDTO>();
        List<HuntingExperience> listHuntExps = hunExpDAO.findAllHuntingExperience();    
        
        for(HuntingExperience huntExp : listHuntExps){
            HuntingExperienceDTO huntExpDTO = HuntingExperienceTransformation.
                    transformToDTO(huntExp);
            listHuntExpsDTO.add(huntExpDTO);
        }
        return listHuntExpsDTO;
    }

    public List<WeaponDTO> findEfficientWeapons(CreatureDTO creatureDTO, int effeciency) {
        List<WeaponDTO> listWeaponsDTO = new ArrayList<WeaponDTO>();
        List<Weapon> listWeapons = new ArrayList<Weapon>();
        if (creatureDTO!=null && effeciency>=0 &&  effeciency<=100) {
            listWeapons = hunExpDAO.findEfficientWeapons(CreatureTransformation.
                    transformToEntity(creatureDTO), effeciency);        
        }else{
            throw new NullPointerException("Creature must not be null and"
                    + " effeciency must be in %");
        }
        WeaponDTO weaponDTO = new WeaponDTO();
        for(Weapon weapon : listWeapons){
            weaponDTO = WeaponTransformation.transformToDTO(weapon);
            listWeaponsDTO.add(weaponDTO);
        }
        return listWeaponsDTO;
        
    }
    
}
