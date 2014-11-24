package cz.muni.fi.pa165.creaturehunting.service.datatransformation;

import cz.muni.fi.pa165.creaturehunting.data.entity.Area;
import cz.muni.fi.pa165.creaturehunting.data.entity.Creature;
import cz.muni.fi.pa165.creaturehunting.api.dto.AreaDTO;
import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matej Cizik
 */
@Transactional
public class AreaTransformation {
    
    private static final AreaTransformation instance = new AreaTransformation();
    
    //private AreaTransformation() { }
    
    @Autowired
    public static AreaTransformation getObject() {
        return instance;
    }
    
    /**
     * Transforms entity to data transfer object.
     * @param area This entity will be transformed into DTO.
     * @return DTO object that was created.
     */
    public static AreaDTO transformToDTO(Area area){
        if (area == null) {
            throw new NullPointerException("Area given is null.");
        }
        
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setId(area.getId());
        areaDTO.setDescription(area.getDescription());
        areaDTO.setAcreage(area.getAcreage());
        areaDTO.setName(area.getName());
//        List<Creature> creatures = area.getListOfCreatures();
//        List<CreatureDTO> creaturesDTO = new ArrayList();
//        if (creatures != null && creatures.size() > 0) {
//            for (Creature creature : creatures) {
//                creaturesDTO.add(CreatureTransformation.transformToDTO(creature));
//            }
//            areaDTO.setListOfCreatures(creaturesDTO);
//        }
        
        return areaDTO;
    }
    
    /**
     * Transforms data transfer object to entity.
     * @param areaDTO This DTO will be transformed into entity.
     * @return Entity created from DTO.
     */
    public static Area transformToEntity(AreaDTO areaDTO){
        if (areaDTO == null) {
            throw new NullPointerException("AreaDTO given is null.");
        }
        
        Area area = new Area();        
        area.setId(areaDTO.getId());
        area.setAcreage(areaDTO.getAcreage());
        area.setDescription(areaDTO.getDescription());
        area.setName(areaDTO.getName());
        
//        List<CreatureDTO> creaturesDTO = areaDTO.getListOfCreatures();
//        List<Creature> creatures = new ArrayList();
//        if (creaturesDTO != null && creaturesDTO.size() > 0) {
//            for (CreatureDTO creatureDTO : creaturesDTO) {
//                creatures.add(CreatureTransformation.transformToEntity(creatureDTO));
//            }
//            area.setListOfCreatures(creatures);
//        }
        
        return area;
    }
}
