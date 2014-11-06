/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.creaturehunting.service.huntingexperience;

import cz.muni.fi.pa165.creaturehunting.dao.DAOException;
import cz.muni.fi.pa165.creaturehunting.dao.huntingexperience.HuntingExperience;
import cz.muni.fi.pa165.creaturehunting.dao.huntingexperience.HuntingExperienceDAO;
import cz.muni.fi.pa165.creaturehunting.dao.weapon.Weapon;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureTransformation;
import cz.muni.fi.pa165.creaturehunting.service.exception.DataAccessExceptionService;
import cz.muni.fi.pa165.creaturehunting.service.weapon.WeaponDTO;
import cz.muni.fi.pa165.creaturehunting.service.weapon.WeaponTransformation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class HuntingExperienceServiceImpl implements HuntingExperienceService {
    
    @Autowired
    private HuntingExperienceDAO hunExpDAO;
    
    public void setHuntingExperienceDAO (HuntingExperienceDAO  hunExpDAO){
        if (hunExpDAO==null) {
            throw new NullPointerException("HunExpDAO mus not be null!");           
        }
        this.hunExpDAO = hunExpDAO;
    }
    
    

    public void create(HuntingExperienceDTO huntExpDTO) {
        if (huntExpDTO==null) {
            throw new NullPointerException("HuntExpDTO must not be null!");
        }
        HuntingExperience huntExp = new HuntingExperience();
        try {
            huntExp = HuntingExperienceTransformation.transformToEntity(huntExpDTO);
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not succesful or data was corrupted.",e);
        }
        hunExpDAO.createHuntingExperience(huntExp);
    }

    public void update(HuntingExperienceDTO huntExpDTO) {
        if (huntExpDTO==null) {
            throw new NullPointerException("HuntExpDTO must not be null!");
        }
        HuntingExperience huntExp = new HuntingExperience();
        try {
            huntExp = HuntingExperienceTransformation.transformToEntity(huntExpDTO);
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not succesful or data was corrupted.",e);
        }
        hunExpDAO.updateHuntingExperience(huntExp);
    }

    public void delete(HuntingExperienceDTO huntExpDTO) {
        if (huntExpDTO==null) {
            throw new NullPointerException("HuntExpDTO must not be null!");
        }
        try {
            hunExpDAO.deleteHuntingExperience(HuntingExperienceTransformation.transformToEntity(huntExpDTO));
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not succesful or data was corrupted.",e);
        }
    }

    public HuntingExperienceDTO findHuntExp(Long id) {
        if (id==null) {
            throw new NullPointerException("Id must not null!");
        }
        
        HuntingExperience huntExp = new HuntingExperience();
        try {
            huntExp = hunExpDAO.findHuntingExperience(id);
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not succesful or data was corrupted.",e);
        }
        
        HuntingExperienceDTO huntExpDTO = HuntingExperienceTransformation.transformToDTO(huntExp);
        return huntExpDTO;
    }

    public List<HuntingExperienceDTO> findAllHuntExp() {
        List<HuntingExperienceDTO> listHuntExpsDTO = new ArrayList<HuntingExperienceDTO>();
        List<HuntingExperience> listHuntExps = new ArrayList<HuntingExperience>();
        try {
            listHuntExps = hunExpDAO.findAllHuntingExperience();
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not succesful or data was corrupted.",e);
        }
        
        for(HuntingExperience huntExp : listHuntExps){
            HuntingExperienceDTO huntExpDTO = HuntingExperienceTransformation.transformToDTO(huntExp);
            listHuntExpsDTO.add(huntExpDTO);
        }
        return listHuntExpsDTO;
    }

    public List<WeaponDTO> findEfficientWeapons(CreatureDTO creatureDTO, int effeciency) {
        List<WeaponDTO> listWeaponsDTO = new ArrayList<WeaponDTO>();
        List<Weapon> listWeapons = new ArrayList<Weapon>();
        if (creatureDTO!=null && effeciency>=0 &&  effeciency<=100) {
            try {
                listWeapons = hunExpDAO.findEfficientWeapons(CreatureTransformation.transformToEntity(creatureDTO), effeciency);
            } catch (DAOException e) {
                throw new DataAccessExceptionService("Transformation was not succesful or data was corrupted.",e);
            }
            
        }else{
            throw new NullPointerException("Creature must not be null and effeciency must be in %");
        }
        WeaponDTO weaponDTO = new WeaponDTO();
        for(Weapon weapon : listWeapons){
            weaponDTO = WeaponTransformation.transformToDTO(weapon);
            listWeaponsDTO.add(weaponDTO);
        }
        return listWeaponsDTO;
        
    }
    
}
