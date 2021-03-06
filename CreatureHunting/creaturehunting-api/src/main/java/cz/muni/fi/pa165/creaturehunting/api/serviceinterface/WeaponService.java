package cz.muni.fi.pa165.creaturehunting.api.serviceinterface;

import cz.muni.fi.pa165.creaturehunting.api.dto.WeaponDTO;
import java.util.List;

/**
 * Interface with CRUD methods of Weapon entity for service tier.
 *
 * @author laduska
 */
public interface WeaponService {

    /*
     * Create Weapon on the service tier.
     * @param weaponDTO weapon DTO object
     */
    public void create(WeaponDTO weaponDTO);

    /*
     * Update Weapon on the service tier.
     * @param weaponDTO weapon DTO object
     */
    public void update(WeaponDTO weaponDTO);

    /*
     * Delete Weapon on the service tier.
     * @param weaponDTO weapon DTO object
     */
    public void delete(WeaponDTO weaponDTO);

    /*
     * Find Weapon by the ID.
     * @param long id - ID of the weapon
     * @return WeaponDTO object
     */
    public WeaponDTO findWeapon(long id);

    
    /*
     * Find all Weapons.
     * @return list of WeaponDTO objects
     */
    public List<WeaponDTO> findAllWeapons();
    
    /**
     * Find all weapons that contains string name.
     * @param name String value
     * @return List of weapons.
     */
    public List<WeaponDTO> findAllByName(String name);
}
