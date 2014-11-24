package cz.muni.fi.pa165.creaturehunting.service.creature;

import cz.muni.fi.pa165.creaturehunting.dao.entity.Creature;
import cz.muni.fi.pa165.creaturehunting.dao.dao.CreatureDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Radoslav Zajonc
 */
@Service
@Transactional
public class CreatureServiceImpl implements CreatureService {
  
    private final CreatureDAO creatureDAO;

    public CreatureServiceImpl(CreatureDAO creatureDAO) {
        this.creatureDAO = creatureDAO;
    }

    @Override
    public void create(CreatureDTO creatureDTO) {
        if (creatureDTO==null) {
            throw new NullPointerException("CreatureDTO must not be null.");
        }
        creatureDAO.createCreature(CreatureTransformation.
                    transformToEntity(creatureDTO));
    }
    
    @Override
    public void update(CreatureDTO creatureDTO) {
        if (creatureDTO==null) {
            throw new NullPointerException("CreatureDTO must not be null.");
        }
        creatureDAO.updateCreature(CreatureTransformation.
                    transformToEntity(creatureDTO));
    }
    
    @Override
    public void delete(CreatureDTO creatureDTO) {
        if (creatureDTO==null) {
            throw new NullPointerException("CreatureDTO must not be null.");
        }
        creatureDAO.deleteCreature(CreatureTransformation.
                transformToEntity(creatureDTO));
    }
    
    @Override
    public CreatureDTO findCreature(long id) {
        CreatureDTO creatureDTO = new CreatureDTO();
        creatureDTO = CreatureTransformation.transformToDTO(
                creatureDAO.findCreature(id));
        
        return creatureDTO;
    }
    
    @Override
    public List<CreatureDTO> findAllCreatures() {
        List<Creature> creatures = creatureDAO.findAllCreatures();      
        List<CreatureDTO> creaturesDTO = new ArrayList();
        for (Creature creature : creatures) {
            creaturesDTO.add(CreatureTransformation.transformToDTO(creature));
        }
        return creaturesDTO;
    }
    
    @Override
    public List<CreatureDTO> findAllCreaturesByName(String name) {
        List creaturesDTO = new ArrayList();
        for (CreatureDTO creatureDTO : this.findAllCreatures()) {
            if (creatureDTO.getName().equals(name)) {
                creaturesDTO.add(creatureDTO);
            }
        }
        return creaturesDTO;
    }
    
}
