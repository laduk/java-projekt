package cz.muni.fi.pa165.creaturehunting.service.datatransformation;

import cz.muni.fi.pa165.creaturehunting.data.entity.Weapon;
import cz.muni.fi.pa165.creaturehunting.api.dto.WeaponDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laduska
 */

@Transactional
public class WeaponTransformation {    
    
     /**
     * Creates DTO object from entity object
     * 
     * @param weapon entity object as a source of data
     * @return WeaponDto DTO object
     */
    public static WeaponDTO transformToDTO(Weapon weapon) {

        if (weapon == null) {
            throw new NullPointerException("Entity object for transformation cannot be null");
        }

        WeaponDTO weaponDto = new WeaponDTO();

        weaponDto.setId(weapon.getId());
        weaponDto.setName(weapon.getName());
        weaponDto.setAmmunition(weapon.getAmmunition());
        weaponDto.setGunReach(weapon.getGunReach());
        return weaponDto;

    }

    
    
    /**
     * Creates entity object from data transfer object
     * 
     * @param weaponDto data transfer object as a source of data
     * @return Weapon entity object
     */
    public static Weapon transformToEntity(WeaponDTO weaponDto) {
        if (weaponDto == null) {
            throw new NullPointerException("Data transfer object for transformation cannot be null");
        }

        Weapon weapon = new Weapon();

        weapon.setId(weaponDto.getId());
        weapon.setName(weaponDto.getName());
        weapon.setAmmunition(weaponDto.getAmmunition());
        weapon.setGunReach(weaponDto.getGunReach());
        return weapon;
    }
}
