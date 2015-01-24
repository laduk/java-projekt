package cz.muni.fi.pa165.creaturehunting.service.serviceimpl;

import cz.muni.fi.pa165.creaturehunting.service.datatransformation.CreatureTransformation;
import cz.muni.fi.pa165.creaturehunting.data.entity.Creature;
import cz.muni.fi.pa165.creaturehunting.data.dao.CreatureDAO;
import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.CreatureService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.access.annotation.Secured;
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

    @Secured({"ROLE_SURVIVOR", "ROLE_ADMIN"})
    @Override
    public void create(CreatureDTO creatureDTO) {
        if (creatureDTO==null) {
            throw new NullPointerException("CreatureDTO must not be null.");
        }
        creatureDAO.createCreature(CreatureTransformation.
                    transformToEntity(creatureDTO));
    }
    
    @Secured({"ROLE_SURVIVOR", "ROLE_ADMIN"})
    @Override
    public void update(CreatureDTO creatureDTO) {
        if (creatureDTO==null) {
            throw new NullPointerException("CreatureDTO must not be null.");
        }
        creatureDAO.updateCreature(CreatureTransformation.
                    transformToEntity(creatureDTO));
    }
    
    @Secured({"ROLE_ADMIN"})
    @Override
    public void delete(CreatureDTO creatureDTO) {
        if (creatureDTO==null) {
            throw new NullPointerException("CreatureDTO must not be null.");
        }
        creatureDAO.deleteCreature(CreatureTransformation.
                transformToEntity(creatureDTO));
    }
    
    @Secured({"ROLE_SURVIVOR", "ROLE_ADMIN"})
    @Override
    public CreatureDTO findCreature(long id) {
        CreatureDTO creatureDTO = CreatureTransformation.transformToDTO(
                creatureDAO.findCreature(id));
        return creatureDTO;
    }
    
    @Secured({"ROLE_SURVIVOR", "ROLE_ADMIN"})
    @Override
    public List<CreatureDTO> findAllCreatures() {
        List<Creature> creatures = creatureDAO.findAllCreatures();      
        List<CreatureDTO> creaturesDTO = new ArrayList();
        for (Creature creature : creatures) {
            creaturesDTO.add(CreatureTransformation.transformToDTO(creature));
        }
        return creaturesDTO;
    }
    
    @Secured({"ROLE_SURVIVOR", "ROLE_ADMIN"})
    @Override
    public List<CreatureDTO> findAllCreaturesByName(String name) {
        List creaturesDTO = new ArrayList();
        for (CreatureDTO creatureDTO : this.findAllCreatures()) {
            if (creatureDTO.getName().toLowerCase().contains(name.toLowerCase())) {
                creaturesDTO.add(creatureDTO);
            }
        }
        return creaturesDTO;
    }
    
}
