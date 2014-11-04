package cz.muni.fi.pa165.creaturehunting.service.creature;

import cz.muni.fi.pa165.creaturehunting.service.area.AreaDTO;
import cz.muni.fi.pa165.creaturehunting.service.area.AreaTransformation;
import cz.muni.fi.pa165.creaturehunting.dao.area.Area;
import cz.muni.fi.pa165.creaturehunting.dao.creature.Creature;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class transforms data transform object to entity and back.
 * It's a singleton.
 *
 * @author Radoslav Zajonc
 */
@Transactional
public class CreatureTransformation {
    
    private static final CreatureTransformation instance = new CreatureTransformation();
    
    private CreatureTransformation() { }
    
    @Autowired
    public static CreatureTransformation getObject() {
        return instance;
    }
    
    /**
     * Transforms entity to data transfer object.
     * @param creature This entity will be transformed into DTO.
     * @return DTO object that was created.
     */
    public static CreatureDTO transformToDTO(Creature creature){
        if (creature == null) {
            throw new NullPointerException("Creature given is null.");
        }
        
        CreatureDTO creatureDTO = new CreatureDTO();
        creatureDTO.setId(creature.getId());
        creatureDTO.setName(creature.getName());
        creatureDTO.setHeight(creature.getHeight());
        creatureDTO.setWeight(creature.getWeight());
        creatureDTO.setAgility(creature.getAgility());
        List<Area> areas = creature.getListOfAreas();
        List<AreaDTO> areasDTO = new ArrayList();
        AreaTransformation areaTransformation = new AreaTransformation();
        for (Area area : areas) {
            areasDTO.add(areaTransformation.transformToDTO(area));
        }
        creatureDTO.setListOfAreas(areasDTO);
        
        return creatureDTO;
    }
    
    /**
     * Transforms data transfer object to entity.
     * @param creatureDTO This DTO will be transformed into entity.
     * @return Entity created from DTO.
     */
    public static Creature transformToEntity(CreatureDTO creatureDTO){
        if (creatureDTO == null) {
            throw new NullPointerException("CreatureDTO given is null.");
        }
        
        Creature creature = new Creature();
        creature.setId(creatureDTO.getId());
        creature.setName(creatureDTO.getName());
        creature.setHeight(creatureDTO.getHeight());
        creature.setWeight(creatureDTO.getWeight());
        creature.setAgility(creatureDTO.getAgility());
        List<AreaDTO> areasDTO = creatureDTO.getListOfAreas();
        List<Area> areas = new ArrayList();
        AreaTransformation areaTransformation = new AreaTransformation();
        for (AreaDTO areaDTO : areasDTO) {
            areas.add(areaTransformation.transformToEntity(areaDTO));
        }
        creature.setListOfAreas(areas);
        
        return creature;
    }
    
}
