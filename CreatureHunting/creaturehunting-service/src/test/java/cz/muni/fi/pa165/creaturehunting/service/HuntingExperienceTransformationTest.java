package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.data.entity.Area;
import cz.muni.fi.pa165.creaturehunting.data.entity.Creature;
import cz.muni.fi.pa165.creaturehunting.data.entity.HuntingExperience;
import cz.muni.fi.pa165.creaturehunting.data.entity.Weapon;
import cz.muni.fi.pa165.creaturehunting.service.datatransformation.CreatureTransformation;
import cz.muni.fi.pa165.creaturehunting.api.dto.HuntingExperienceDTO;
import cz.muni.fi.pa165.creaturehunting.service.datatransformation.HuntingExperienceTransformation;
import cz.muni.fi.pa165.creaturehunting.service.datatransformation.WeaponTransformation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Fita
 */
public class HuntingExperienceTransformationTest {
    
    private HuntingExperienceDTO huntExperienceDTO;
    private HuntingExperience huntExperience;
            
    /**
     * Class where initiation of object id done, it is done before all test.
     */
    @Before
    public void setUp(){
        huntExperienceDTO = new HuntingExperienceDTO();

        List<Creature> listC = new ArrayList();
        Creature creature = new Creature();
        creature.setId(1);
        creature.setName("Witch");
        creature.setAgility(25);
        creature.setHeight(180);
        creature.setWeight(75);
        creature.setListOfAreas(null);
        listC.add(creature);
        
        Area area = new Area();
        area.setId(1);
        area.setName("Brasov");
        area.setDescription("Romania");
        area.setAcreage(100);
        area.setListOfCreatures(listC);
        
        List<Area> listA = new ArrayList();
        listA.add(area);
        Creature creature2 = new Creature();
        creature2.setId(2);
        creature2.setName("Dracula");
        creature2.setAgility(25);
        creature2.setHeight(180);
        creature2.setWeight(80);
        creature2.setListOfAreas(listA);
        
        Weapon weapon = new Weapon();
        weapon.setId(1);
        weapon.setName("Rifle");
        weapon.setAmmunition("0.30 cal");
        weapon.setGunReach(100);
        
        huntExperience = new HuntingExperience();
        huntExperience.setId(1);
        huntExperience.setWeapon(weapon);
        huntExperience.setCreature(creature);
        huntExperience.setEfficiency(100);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        String dateStr = "5-10-2014";
        Date date = new Date();
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(HuntingExperienceServiceImplTest.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        huntExperience.setDateOfExperience(date);
        huntExperience.setDescription("Headshot!");
        
        huntExperienceDTO.setId(1);
        huntExperienceDTO.setWeapon(WeaponTransformation.transformToDTO(weapon));
        huntExperienceDTO.setCreature(CreatureTransformation.transformToDTO(creature2));
        huntExperienceDTO.setEfficiency(100);       
        huntExperienceDTO.setDateOfExperience(date);
        huntExperienceDTO.setDescription("Headshot!");
    }
    
    /**
     * Testing transforming to DTO.
     */
    @Test
    public void testTransfrormToDTO(){       
        System.out.println("--- Transform to DTO");
        Assert.assertTrue("Transformation Errror or modification of parameters DTO",
                huntExperienceDTO.equals(HuntingExperienceTransformation.
                        transformToDTO(huntExperience)));
    }
    
    /**
     * Testing transforming to Entity.
     */
    @Test
    public void testTransfrormToEntity(){       
        System.out.println("--- Transform to Entity");
        Assert.assertTrue("Transformation Errror or modification of parameters DTO",
                huntExperience.equals(HuntingExperienceTransformation.transformToEntity(huntExperienceDTO)));
    }
    
    
}
