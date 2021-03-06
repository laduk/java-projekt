package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.data.entity.Weapon;
import cz.muni.fi.pa165.creaturehunting.data.dao.WeaponDAO;
import cz.muni.fi.pa165.creaturehunting.api.dto.WeaponDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.WeaponService;
import cz.muni.fi.pa165.creaturehunting.service.serviceimpl.WeaponServiceImpl;
import cz.muni.fi.pa165.creaturehunting.service.datatransformation.WeaponTransformation;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;

/**
 * Test class for Weapon at service tier.
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

    /**
     *
     * Testing create method on service tier using mock DAO object
     */
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

        verify(weaponDao).createWeapon(captor.capture());
        assertEquals(captor.getValue().getName(), weaponDto.getName());
        assertEquals(captor.getValue().getGunReach(), weaponDto.getGunReach());
        assertEquals(captor.getValue().getAmmunition(), weaponDto.getAmmunition());

        verify(weaponDao, never()).updateWeapon(any(Weapon.class));
        verify(weaponDao, never()).deleteWeapon(any(Weapon.class));

    }

    
    /**
     *
     * Testing update method on service tier using mock DAO object
     */
    @Test
    public void updateWeaponTest() {
        try {
            weaponService.update(null);
            fail();
        } catch (NullPointerException ex) {
        }

        WeaponDTO weaponDto = new WeaponDTO();

        weaponDto.setId(20);
        weaponDto.setName("Updated Sword Name");
        weaponDto.setAmmunition("Updated Hand Power");
        weaponDto.setGunReach(1);

        weaponService.update(weaponDto);
        ArgumentCaptor<Weapon> captor2 = ArgumentCaptor.forClass(Weapon.class);
        verify(weaponDao).updateWeapon(captor2.capture());
        verify(weaponDao, never()).deleteWeapon(any(Weapon.class));
        verify(weaponDao, never()).createWeapon(any(Weapon.class));

        assertEquals(captor2.getValue().getId(), weaponDto.getId());
        assertEquals(captor2.getValue().getName(), weaponDto.getName());
        assertEquals(captor2.getValue().getGunReach(), weaponDto.getGunReach());
        assertEquals(captor2.getValue().getAmmunition(), weaponDto.getAmmunition());

    }

    
    /**
     *
     * Testing delete method on service tier using mock DAO object
     */
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

    
    /**
     *
     * Testing findWeapon method on service tier using mock DAO object
     */
    @Test
    public void findWeaponByIdTest() {
        try {
            weaponService.findWeapon(-1);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        WeaponDTO weaponDto = new WeaponDTO();
        weaponDto.setId(5);
        weaponDto.setName("Secret");
        weaponDto.setGunReach(1);
        weaponDto.setAmmunition("Power of Mind");

        // test return NullPointerException if any id is not in DB
        when(weaponDao.findWeapon(weaponDto.getId())).thenReturn(null);
        try {
            weaponService.findWeapon(weaponDto.getId());
            fail();
        } catch (NullPointerException ex) {
        }

        // if in DB exist id
        when(weaponDao.findWeapon(weaponDto.getId())).thenReturn(WeaponTransformation.transformToEntity(weaponDto));
        WeaponDTO returnDto = weaponService.findWeapon(weaponDto.getId());
        assertEquals(returnDto, weaponDto);
        verify(weaponDao, times(2)).findWeapon(weaponDto.getId());

    }

    
    /**
     *
     * Testing findAllWeapons method on service tier using mock dao object.
     */
    @Test
    public void findAllWeaponsTest() {

        // if doesn't exist record id DB
        List<WeaponDTO> listOfWeaponsDto = new ArrayList<WeaponDTO>();
        when(weaponDao.findAllWeapons()).thenReturn(new ArrayList<Weapon>());
        List<WeaponDTO> returnedList = weaponService.findAllWeapons();
        assertEquals(returnedList, listOfWeaponsDto);

        WeaponDTO weaponDto = new WeaponDTO();
        weaponDto.setId(5);
        weaponDto.setName("Secret");
        weaponDto.setGunReach(1);
        weaponDto.setAmmunition("Power of Mind");

        listOfWeaponsDto.add(weaponDto);

        List<Weapon> weapons = new ArrayList<Weapon>();
        Weapon weapon = new Weapon();
        weapon.setId(5);
        weapon.setName("Secret");
        weapon.setGunReach(1);
        weapon.setAmmunition("Power of Mind");

        weapons.add(weapon);

        // if exist record in DB
        when(weaponDao.findAllWeapons()).thenReturn(weapons);
        returnedList = weaponService.findAllWeapons();
        assertEquals(returnedList.size(), listOfWeaponsDto.size());
        assertEquals(returnedList.get(0), listOfWeaponsDto.get(0));
        verify(weaponDao, times(2)).findAllWeapons();

    }
    
    /**
     * Testing finding weapons by given name.
     */
    @Test
    public void findAllByNameTest(){
        List<Weapon> weaponsByName = new ArrayList();
        WeaponDTO weaponDTO = new WeaponDTO();
        weaponDTO.setId(5);
        weaponDTO.setName("Bow");
        weaponDTO.setGunReach(1);
        weaponDTO.setAmmunition("Arrow");
        
        WeaponDTO weaponDTO2 = new WeaponDTO();
        weaponDTO2.setId(5);
        weaponDTO2.setName("CrossBow");
        weaponDTO2.setGunReach(1);
        weaponDTO2.setAmmunition("Bolt");
        
        weaponsByName.add(WeaponTransformation.transformToEntity(weaponDTO));
        weaponsByName.add(WeaponTransformation.transformToEntity(weaponDTO2));
        when(weaponDao.findAllWeapons()).thenReturn(weaponsByName);

        Assert.assertTrue("Wrong weapon by name was found.",
                weaponService.findAllByName("Bow").get(0).equals(weaponDTO));
        Assert.assertTrue("Wrong list of creatures by name was found.",
                weaponService.findAllByName("Bow").size()==1);
        Assert.assertTrue("Wrong list of creatures by name was found.",
                weaponService.findAllByName("CrossBow").size()==1);        
    }
}
