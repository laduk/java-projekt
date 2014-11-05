/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.service.area.AreaDTO;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Radoslav Zajonc
 */
public class CreatureServiceImplTest {
    
    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring.xml");
        CreatureService creatureService = 
                (CreatureService) ctx.getBean("creatureService");
        
        CreatureDTO hulk = new CreatureDTO();
        hulk.setName("Hulk");
        hulk.setAgility(1);
        hulk.setHeight(180);
        hulk.setWeight(120);
        List<AreaDTO> listA = new ArrayList<AreaDTO>();
        hulk.setListOfAreas(listA);
        
        AreaDTO area = new AreaDTO();
        area.setAcreage(15);
        area.setName("NYC");
        area.setDescription("Just a New York.");
        List<CreatureDTO> listC = new ArrayList<CreatureDTO>();
        listC.add(hulk);
        area.setListOfCreatures(listC);
                
        creatureService.create(hulk);
        
        //System.out.println(creatureService.findCreature(hulk.getId()));
    }
}
