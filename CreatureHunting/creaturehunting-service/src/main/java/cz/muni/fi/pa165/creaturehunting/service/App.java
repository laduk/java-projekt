package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.service.area.AreaDTO;
import cz.muni.fi.pa165.creaturehunting.service.area.AreaService;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    
    public static void main( String[] args ) {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring.xml");
        CreatureService creatureService = (CreatureService) ctx.getBean("creatureService");
        AreaService areaService = (AreaService) ctx.getBean("areaService");

        AreaDTO area = new AreaDTO();
        area.setAcreage(15);
        area.setName("NYC");
        area.setDescription("Just a New York.");
        areaService.create(area);
        area = areaService.findAllAreas().get(0);
        
        CreatureDTO hulk = new CreatureDTO();
        hulk.setName("Hulk");
        hulk.setAgility(1);
        hulk.setHeight(180);
        hulk.setWeight(120);
        List<AreaDTO> listA = new ArrayList();
        listA.add(area);
        hulk.setListOfAreas(listA);
        creatureService.create(hulk);
        hulk = creatureService.findAllCreaturesByName("Hulk").get(0);
        
        hulk.setHeight(200);
        creatureService.update(hulk);
        hulk = creatureService.findAllCreaturesByName("Hulk").get(0);
        
        System.out.println(hulk);
        System.out.println(hulk.getListOfAreas().get(0));
    }
}
