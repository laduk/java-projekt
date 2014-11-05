package cz.muni.fi.pa165.creaturehunting.service.creature;

import cz.muni.fi.pa165.creaturehunting.dao.DAOException;
import cz.muni.fi.pa165.creaturehunting.dao.creature.Creature;
import cz.muni.fi.pa165.creaturehunting.dao.creature.CreatureDAO;
import cz.muni.fi.pa165.creaturehunting.service.exception.DataAccessExceptionService;
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
  
    private final CreatureDAO creatureDAO;

    public CreatureServiceImpl(CreatureDAO creatureDAO) {
        this.creatureDAO = creatureDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void create(CreatureDTO creatureDTO) {
        if (creatureDTO==null) {
            throw new NullPointerException("CreatureDTO must not be null.");
        }
        try {
            creatureDAO.createCreature(CreatureTransformation.
                    transformToEntity(creatureDTO));
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not "
                    + "succesful or data was corrupted.",e);
        }
    }
    
    @Override
    @Transactional
    public void update(CreatureDTO creatureDTO) {
        if (creatureDTO==null) {
            throw new NullPointerException("CreatureDTO must not be null.");
        }        
        try {
            creatureDAO.updateCreature(CreatureTransformation.
                    transformToEntity(creatureDTO));
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not "
                    + "succesful or data was corrupted.",e);
        }
    }
    
    @Override
    @Transactional
    public void delete(CreatureDTO creatureDTO) {
        if (creatureDTO==null) {
            throw new NullPointerException("CreatureDTO must not be null.");
        }
        try {
            creatureDAO.deleteCreature(CreatureTransformation.
                transformToEntity(creatureDTO));
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not "
                    + "succesful or data was corrupted.",e);
        }
    }
    
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public CreatureDTO findCreature(long id) {
        CreatureDTO creatureDTO = new CreatureDTO();
        try {
             creatureDTO = CreatureTransformation.transformToDTO(
                creatureDAO.findCreature(id));
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not "
                    + "succesful or data was corrupted.",e);
        }
        return creatureDTO;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CreatureDTO> findAllCreatures() {
        List<Creature> creatures = new ArrayList();
        try {
            creatures = creatureDAO.findAllCreatures();
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not "
                    + "succesful or data was corrupted.",e);            
        }
        List<CreatureDTO> creaturesDTO = new ArrayList();
        for (Creature creature : creatures) {
            creaturesDTO.add(CreatureTransformation.transformToDTO(creature));
        }
        return creaturesDTO;
    }
}
