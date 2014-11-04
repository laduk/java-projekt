/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.service.weapon;

import cz.muni.fi.pa165.creaturehunting.dao.weapon.Weapon;
import cz.muni.fi.pa165.creaturehunting.dao.weapon.WeaponDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laduska
 */
@Service
@Transactional
public class WeaponServiceImpl implements WeaponService {

    private WeaponDAO weaponDao;
    
    @Autowired
    public void setWeaponDao(WeaponDAO weaponDao) {
        this.weaponDao = weaponDao;
    }

    /**
     * Create a weapon.
     *
     * @param weaponDTO - data transfer object as a source of data
     */
    public void create(WeaponDTO weaponDTO) {

        if (weaponDTO == null) {
            throw new NullPointerException("WeaponDTO argument for creation of entity cannot be null.");
        }

        //Weapon weapon = WeaponTranformation.transformToDTO(weaponDTO);
        weaponDao.createWeapon(WeaponTransformation.transformToEntity(weaponDTO));
    }

    /**
     * Update a weapon.
     *
     * @param weaponDTO - data transfer object as a source of data
     */
    public void update(WeaponDTO weaponDTO) {
        if (weaponDTO == null) {
            throw new NullPointerException("WeaponDTO argument for updating of entity cannot be null.");
        }

        //Weapon weapon = WeaponTranformation.transformToDTO(weaponDTO);
        weaponDao.updateWeapon(WeaponTransformation.transformToEntity(weaponDTO));
    }

    
    
    /**
     * Delete a weapon.
     *
     * @param weaponDTO - data transfer object as a source of data
     */
    public void delete(WeaponDTO weaponDTO) {
        if (weaponDTO == null) {
            throw new NullPointerException("WeaponDTO argument for deleting of entity cannot be null.");
        }
        //Weapon weapon = WeaponTranformation.transformToDTO(weaponDTO);
        weaponDao.deleteWeapon(WeaponTransformation.transformToEntity(weaponDTO));
    }

    
    
    
    /**
     * Find a weapon by ID. 
     * @param id given id to be find
     * @return weapon data transfer object
     */
    public WeaponDTO findWeapon(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID of the wanted weapon cannot be negative");
        }

        return WeaponTransformation.transformToDTO(weaponDao.findWeapon(id));
    }
    
        
    
    /**
     * Find all weapons.
     *
     * @return list of weapon data transfer objects
     */
    public List<WeaponDTO> findAllWeapons() {
        
        List<Weapon> weapons = weaponDao.findAllWeapons();
        
        List<WeaponDTO> weaponsDto = new ArrayList<WeaponDTO>();
        
        for(Weapon wep : weapons){
            weaponsDto.add(WeaponTransformation.transformToDTO(wep));
        }
        
        return weaponsDto;
    }
}