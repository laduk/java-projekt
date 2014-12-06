package cz.muni.fi.pa165.creaturehunting.service.datatransformation;

import cz.muni.fi.pa165.creaturehunting.api.dto.AreaDTO;
import cz.muni.fi.pa165.creaturehunting.data.entity.Area;
import cz.muni.fi.pa165.creaturehunting.data.entity.Creature;
import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * This class transforms data transform object to entity and back.
 *
 * @author Radoslav Zajonc
 */
public class CreatureTransformation {
    
    /**
     * Transforms entity to data transfer object (with filled listOfAreas).
     * @param creature This entity will be transformed into DTO.
     * @return DTO object that was created.
     */
    public static CreatureDTO transformToDTO(Creature creature) {
        return transformToDTO(creature, true);
    }
    
    /**
     * Transforms entity to data transfer object.
     * @param creature This entity will be transformed into DTO.
     * @param isParrent true - witch listOfAreas; false - without it.
     * @return DTO object that was created.
     */
    public static CreatureDTO transformToDTO(Creature creature, Boolean isParrent) {
        if (creature == null) {
            throw new NullPointerException("Creature given is null.");
        }
        
        CreatureDTO creatureDTO = new CreatureDTO();
        creatureDTO.setId(creature.getId());
        creatureDTO.setName(creature.getName());
        creatureDTO.setHeight(creature.getHeight());
        creatureDTO.setWeight(creature.getWeight());
        creatureDTO.setAgility(creature.getAgility());
        List<AreaDTO> areasDTO = new ArrayList();
        List<Long> idAreasDTO = new ArrayList();
        List<Area> areas = creature.getListOfAreas();
        if (isParrent && areas != null && areas.size() > 0) {
            for (Area area : areas) {
                areasDTO.add(AreaTransformation.transformToDTO(area, false));
                idAreasDTO.add(area.getId());
            }
            creatureDTO.setListOfAreas(areasDTO);
            creatureDTO.setIdAreas(idAreasDTO);
        }
        
        return creatureDTO;
    }
    
    /**
     * Transforms data transfer object to entity (with filled listOfAreas).
     * If attributes name, height, weight and agility are overflowed,
     * they will be cut off.
     * @param creatureDTO This DTO will be transformed into entity.
     * @return Entity created from DTO.
     */
    public static Creature transformToEntity(CreatureDTO creatureDTO) {
        return transformToEntity(creatureDTO, true);
    }
    
    /**
     * Transforms data transfer object to entity.
     * @param creatureDTO This DTO will be transformed into entity.
     * @param isParrent true - witch listOfAreas; false - without it.
     * @return Entity created from DTO.
     */
    public static Creature transformToEntity(CreatureDTO creatureDTO, Boolean isParrent) {
        if (creatureDTO == null) {
            throw new NullPointerException("CreatureDTO given is null.");
        }
        
        Creature creature = new Creature();
        creature.setId(creatureDTO.getId());
        
        String name = creatureDTO.getName();
        if (name != null && name.length() > 20) name = name.substring(0,20);
        creature.setName(name);
        
        int height = creatureDTO.getHeight();
        if (height < 0) height = 0;
        creature.setHeight(height);
        
        int weight = creatureDTO.getWeight();
        if (weight < 0) weight = 0;
        creature.setWeight(weight);
        
        int agility = creatureDTO.getAgility();
        if (agility < 0) agility = 0;
        if (agility > 100) agility = 100;
        creature.setAgility(agility);
        
        List<Area> areas = new ArrayList();
        List<Long> idAreasDTO = creatureDTO.getIdAreas();
        if (isParrent && idAreasDTO != null && idAreasDTO.size() > 0) {
            for (Long idAreaDTO : idAreasDTO) {
                areas.add(new Area(idAreaDTO));
            }
            creature.setListOfAreas(areas);
        }
        
        return creature;
    }
    
}
