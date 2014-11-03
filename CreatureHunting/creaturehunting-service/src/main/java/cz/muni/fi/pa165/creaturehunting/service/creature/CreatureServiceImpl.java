package cz.muni.fi.pa165.creaturehunting.service.creature;

import cz.muni.fi.pa165.creaturehunting.dao.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.dao.creature.CreatureDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Radoslav Zajonc
 */
@Service
public class CreatureServiceImpl implements CreatureService {
  
    private CreatureDAO creatureDAO;

    public CreatureServiceImpl(CreatureDAO creatureDAO) {
        this.creatureDAO = creatureDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void create(CreatureDTO creatureDTO) {
        creatureDAO.createCreature(CreatureTransformation.getObject().
                transformToEntity(creatureDTO));
    }
    
    @Override
    @Transactional
    public void update(CreatureDTO creatureDTO) {
        creatureDAO.updateCreature(CreatureTransformation.getObject().
                transformToEntity(creatureDTO));
    }
    
    @Override
    @Transactional
    public void delete(CreatureDTO creatureDTO) {
        creatureDAO.deleteCreature(CreatureTransformation.getObject().
                transformToEntity(creatureDTO));
    }
    
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public CreatureDTO findCreature(long id) {
        return CreatureTransformation.getObject().transformToDTO(
                creatureDAO.findCreature(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CreatureDTO> findAllCreatures() {
        List<Creature> creatures = creatureDAO.findAllCreatures();
        List<CreatureDTO> creaturesDTO = new ArrayList();
        for (Creature creature : creatures) {
            creaturesDTO.add(
                    CreatureTransformation.getObject().transformToDTO(creature));
        }
        return creaturesDTO;
    }
}
