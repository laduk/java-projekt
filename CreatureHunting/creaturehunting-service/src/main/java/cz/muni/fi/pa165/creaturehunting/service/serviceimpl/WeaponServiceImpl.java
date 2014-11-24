package cz.muni.fi.pa165.creaturehunting.service.serviceimpl;

import cz.muni.fi.pa165.creaturehunting.service.datatransformation.WeaponTransformation;
import cz.muni.fi.pa165.creaturehunting.data.entity.Weapon;
import cz.muni.fi.pa165.creaturehunting.data.dao.WeaponDAO;
import cz.muni.fi.pa165.creaturehunting.api.dto.WeaponDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.WeaponService;
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
        if (weaponDao == null) {
            throw new NullPointerException();
        }
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
        weaponDao.deleteWeapon(WeaponTransformation.transformToEntity(weaponDTO));      
    }

    /**
     * Find a weapon by ID.
     *
     * @param id given id to be find
     * @return weapon data transfer object
     */
    public WeaponDTO findWeapon(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID of the wanted weapon cannot be negative");
        }

        WeaponDTO weaponDto  = WeaponTransformation.transformToDTO(weaponDao.findWeapon(id));

        return weaponDto;                
    }

    /**
     * Find all weapons.
     *
     * @return list of weapon data transfer objects
     */
    public List<WeaponDTO> findAllWeapons() {

        List<Weapon> weapons = weaponDao.findAllWeapons();
        List<WeaponDTO> weaponsDto = new ArrayList<WeaponDTO>();

        for (Weapon wep : weapons) {
            weaponsDto.add(WeaponTransformation.transformToDTO(wep));
        }

        return weaponsDto;
    }
}
