/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.dao.DAOException;
import cz.muni.fi.pa165.creaturehunting.dao.weapon.Weapon;
import cz.muni.fi.pa165.creaturehunting.dao.weapon.WeaponDAO;
import cz.muni.fi.pa165.creaturehunting.service.weapon.WeaponDTO;
import cz.muni.fi.pa165.creaturehunting.service.weapon.WeaponService;
import cz.muni.fi.pa165.creaturehunting.service.weapon.WeaponServiceImpl;
import cz.muni.fi.pa165.creaturehunting.service.weapon.WeaponTransformation;
import org.junit.Before;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;

/**
 *
 * @author laduska
 */
public class WeaponServiceImplTest {

    private WeaponService weaponService;
    private WeaponDAO weaponDao;

    @Before
    public void setUp() {
        weaponDao = mock(WeaponDAO.class);
        WeaponServiceImpl weaponServiceImpl = new WeaponServiceImpl();
        weaponServiceImpl.setWeaponDao(weaponDao);
        weaponService = weaponServiceImpl;
    }

    @Test
    public void createWeaponTest() {
        try {
            weaponService.create(null);
            fail();
        } catch (NullPointerException ex) {
        }
        
        verify(weaponDao, never()).createWeapon(null);    
        
        WeaponDTO weaponDto = new WeaponDTO();
        weaponDto.setId(10);
        weaponDto.setName("Sword");
        weaponDto.setGunReach(1);
        weaponDto.setAmmunition("Power of Hands");
        
        ArgumentCaptor<Weapon> captor = ArgumentCaptor.forClass(Weapon.class);
        weaponService.create(weaponDto);
        
        //doNothing().when(weaponDao).updateWeapon(WeaponTransformation.transformToEntity(weaponDto));
        
        Weapon weapon = WeaponTransformation.transformToEntity(weaponDto);
                
        verify(weaponDao).createWeapon(captor.capture());
        assertEquals(captor.getValue().getName(), weaponDto.getName());
        assertEquals(captor.getValue().getGunReach(), weaponDto.getGunReach());
        assertEquals(captor.getValue().getAmmunition(), weaponDto.getAmmunition());
        
        doThrow(DAOException.class).when(weaponDao).createWeapon(any(Weapon.class));
  
        verify(weaponDao, never()).updateWeapon(any(Weapon.class));
        verify(weaponDao, never()).deleteWeapon(any(Weapon.class));

    }

    @Test
    public void updateWeaponTest() {
        long id = 20;
        
        try {
            weaponService.update(null);
            fail();
        } catch (NullPointerException ex) {
        }
        
        WeaponDTO weaponDto = new WeaponDTO();
                
        weaponDto.setId(id);
        weaponDto.setName("Dual Sword");
        weaponDto.setGunReach(2);
        weaponDto.setAmmunition("Hand Power");
        
        weaponService.create(weaponDto);
        ArgumentCaptor<Weapon> captor = ArgumentCaptor.forClass(Weapon.class);
        verify(weaponDao).createWeapon(captor.capture());
        assertEquals(captor.getValue().getName(), weaponDto.getName());
        assertEquals(captor.getValue().getGunReach(), weaponDto.getGunReach());
        assertEquals(captor.getValue().getAmmunition(), weaponDto.getAmmunition());
        
        weaponDto.setName("Updated Sword Name");
        weaponDto.setAmmunition("Updated Hand Power");
        weaponDto.setGunReach(1);
        
        weaponService.update(weaponDto);
        ArgumentCaptor<Weapon> captor2 = ArgumentCaptor.forClass(Weapon.class);
        verify(weaponDao).updateWeapon(captor2.capture());
        verify(weaponDao, never()).deleteWeapon(any(Weapon.class));
        //verify(weaponDao, never()).createWeapon(any(Weapon.class));
        
        
        assertEquals(captor2.getValue().getName(), weaponDto.getName());
        assertEquals(captor2.getValue().getGunReach(), weaponDto.getGunReach());
        assertEquals(captor2.getValue().getAmmunition(), weaponDto.getAmmunition());
               
        doThrow(DAOException.class).when(weaponDao).updateWeapon(any(Weapon.class));
        
    }


    @Test
    public void deleteWeaponTest() {
        
        try {
            weaponService.delete(null);
            fail();
        } catch (NullPointerException ex) {
        }
    
        WeaponDTO weaponDto = new WeaponDTO();
        weaponDto.setId(1);
        weaponService.delete(weaponDto);
        
        verify(weaponDao).deleteWeapon(WeaponTransformation.transformToEntity(weaponDto));
        verify(weaponDao, never()).createWeapon(any(Weapon.class));
        verify(weaponDao, never()).updateWeapon(any(Weapon.class));
        
    }

    
    
    
    
    
    
    
    
    
    
    

}
