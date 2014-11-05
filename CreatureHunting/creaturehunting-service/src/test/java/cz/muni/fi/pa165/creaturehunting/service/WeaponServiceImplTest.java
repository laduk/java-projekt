/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.dao.weapon.Weapon;
import cz.muni.fi.pa165.creaturehunting.dao.weapon.WeaponDAO;
import cz.muni.fi.pa165.creaturehunting.service.weapon.WeaponDTO;
import cz.muni.fi.pa165.creaturehunting.service.weapon.WeaponService;
import cz.muni.fi.pa165.creaturehunting.service.weapon.WeaponServiceImpl;
import cz.muni.fi.pa165.creaturehunting.service.weapon.WeaponTransformation;
import org.junit.Before;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author laduska
 */
public class WeaponServiceImplTest {
    
    private WeaponService weaponService;
    private WeaponDAO weaponDao;
    
    @Before
    public void setUp(){
        weaponDao = mock(WeaponDAO.class);
        WeaponServiceImpl weaponServiceImpl = new WeaponServiceImpl();
        weaponServiceImpl.setWeaponDao(weaponDao);
        weaponService = weaponServiceImpl;        
    }
    
    
    @Test
    public void testCreateWeapon(){
        try {
            weaponService.create(null);
            fail();
        } catch (DataAccessException ex) {
        }

        WeaponDTO weaponDto = new WeaponDTO();
        weaponDto.setName("Sword");
        weaponDto.setGunReach(1);
        weaponDto.setAmmunition("Power of Hands");
        doNothing().when(weaponDao).update(WeaponTransformation.convertToEntity(weaponDto));
        weaponService.create(weaponDto);
        ArgumentCaptor<Weapon> captor = ArgumentCaptor.forClass(Weapon.class);
        verify(weaponDao).create(captor.capture());
        assertEquals(captor.getValue().getName(), weaponDto.getName());
        assertEquals(captor.getValue().getGunReach(), weaponDto.getGunReach());
        assertEquals(captor.getValue().getAmmunition(), weaponDto.getAmmunition());
        
        verify(weaponDao, never()).update(any(Weapon.class));
        verify(weaponDao, never()).delete(anyLong());
    
    }
    
    
    
}
