package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    
    public static void main( String[] args ) {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring.xml");
        CreatureService creatureService = (CreatureService) ctx.getBean("creatureService");

        CreatureDTO hulk = new CreatureDTO();
        hulk.setName("Hulk");
        hulk.setAgility(1);
        hulk.setHeight(180);
        hulk.setWeight(120);

        creatureService.create(hulk);
        System.out.println(hulk);

        System.out.println(creatureService.findCreature(1));
    }
}
