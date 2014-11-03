/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureService;
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
                (CreatureService) ctx.getBean("creatureServiceImpl");
        
        CreatureDTO hulk = new CreatureDTO();
        hulk.setName("Hulk");
        creatureService.create(hulk);
        
        //System.out.println(creatureService.findCreature(hulk.getId()));
    }
}
