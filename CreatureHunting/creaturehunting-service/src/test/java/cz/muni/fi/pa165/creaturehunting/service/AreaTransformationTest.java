package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.dao.area.Area;
import cz.muni.fi.pa165.creaturehunting.dao.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.service.area.AreaDTO;
import cz.muni.fi.pa165.creaturehunting.service.area.AreaTransformation;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureDTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class tests methods from AreaTransformation.
 * 
 * @author Matej Čižik
 */
public class AreaTransformationTest {
    /**
     * Test area transform from DTO to entity.
     */
    @Test
    public void testTransformToEntity() {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setId(101);
        areaDTO.setName("Brno");
        areaDTO.setAcreage(230);
        areaDTO.setDescription("Beautiful city");
        
        CreatureDTO creatureDTO = new CreatureDTO();
        creatureDTO.setId(42);
        List<CreatureDTO> creaturesDTO = new ArrayList();
        creaturesDTO.add(creatureDTO);
        areaDTO.setListOfCreatures(creaturesDTO);
        
        Area area = AreaTransformation.transformToEntity(areaDTO);
        Assert.assertTrue("Wrong transform id of area to entity.",
                area.getId() == 101);
        Assert.assertTrue("Wrong transform name of area to entity.",
                area.getName().equals("Brno"));
        Assert.assertTrue("Wrong transform acreage of area to entity.",
                area.getAcreage() == 230);
        Assert.assertTrue("Wrong transform description of area to entity.",
                area.getDescription().equals("Beautiful city"));
//        Assert.assertTrue("Wrong transform listOfCreatures of area to entity.",
//                area.getListOfCreatures().get(0).getId() == 42);
    }
    
    /**
     * Test area transform from entity to DTO.
     */
    @Test
    public void testTransformToDTO() {
        Area area = new Area();
        area.setId(1010);
        area.setName("Bratislava");
        area.setAcreage(367);
        area.setDescription("Beautiful slovak city");
        
        Creature creature = new Creature();
        creature.setId(51);
        List<Creature> creatures = new ArrayList();
        creatures.add(creature);
        area.setListOfCreatures(creatures);
        
        AreaDTO areaDTO = AreaTransformation.transformToDTO(area);
        Assert.assertTrue("Wrong transform id of area to DTO.",
                areaDTO.getId() == 1010);
        Assert.assertTrue("Wrong transform name of area to DTO.",
                areaDTO.getName().equals("Bratislava"));
        Assert.assertTrue("Wrong transform acreage of area to DTO.",
                areaDTO.getAcreage() == 367);
        Assert.assertTrue("Wrong transform description of area to DTO.",
                areaDTO.getDescription().equals("Beautiful slovak city"));
//        Assert.assertTrue("Wrong transform listOfCreatures of area to DTO.",
//                areaDTO.getListOfCreatures().get(0).getId() == 51);
    }
}
