package cz.muni.fi.pa165.creaturehunting.service.serviceimpl;

import cz.muni.fi.pa165.creaturehunting.service.datatransformation.HuntingExperienceTransformation;
import cz.muni.fi.pa165.creaturehunting.data.entity.HuntingExperience;
import cz.muni.fi.pa165.creaturehunting.data.dao.HuntingExperienceDAO;
import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import cz.muni.fi.pa165.creaturehunting.service.datatransformation.CreatureTransformation;
import cz.muni.fi.pa165.creaturehunting.api.dto.HuntingExperienceDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.HuntingExperienceService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class HuntingExperienceServiceImpl implements HuntingExperienceService {
    
    @Autowired
    private HuntingExperienceDAO hunExpDAO;
    
    /**
     *
     * @param hunExpDAO
     */
    public HuntingExperienceServiceImpl (HuntingExperienceDAO  hunExpDAO){
        if (hunExpDAO==null) {
            throw new NullPointerException("HunExpDAO mus not be null!");           
        }
        this.hunExpDAO = hunExpDAO;
    }    
    
    @Secured({"ROLE_SURVIVOR", "ROLE_ADMIN"})
    @Override
    public void create(HuntingExperienceDTO huntExpDTO) {
        if (huntExpDTO==null) {
            throw new NullPointerException("HuntExpDTO must not be null!");
        }
        HuntingExperience huntExp =HuntingExperienceTransformation.
                transformToEntity(huntExpDTO);
        hunExpDAO.createHuntingExperience(huntExp);
    }

    @Secured({"ROLE_ADMIN"})
    @Override
    public void update(HuntingExperienceDTO huntExpDTO) {
        if (huntExpDTO==null) {
            throw new NullPointerException("HuntExpDTO must not be null!");
        }
        HuntingExperience huntExp =  HuntingExperienceTransformation.
                transformToEntity(huntExpDTO);
        hunExpDAO.updateHuntingExperience(huntExp);
    }

    @Secured({"ROLE_ADMIN"})
    @Override
    public void delete(HuntingExperienceDTO huntExpDTO) {
        if (huntExpDTO==null) {
            throw new NullPointerException("HuntExpDTO must not be null!");
        }
        hunExpDAO.deleteHuntingExperience(HuntingExperienceTransformation.
                    transformToEntity(huntExpDTO));        
    }

    @Secured({"ROLE_SURVIVOR", "ROLE_ADMIN"})
    @Override
    public HuntingExperienceDTO findHuntExp(Long id) {
        if (id==null) {
            throw new NullPointerException("Id must not null!");
        }
        
        HuntingExperience huntExp = hunExpDAO.findHuntingExperience(id);       
        
        HuntingExperienceDTO huntExpDTO = HuntingExperienceTransformation.
                transformToDTO(huntExp);
        return huntExpDTO;
    }

    @Secured({"ROLE_SURVIVOR", "ROLE_ADMIN"})
    @Override
    public List<HuntingExperienceDTO> findAllHuntExp() {
        List<HuntingExperienceDTO> listHuntExpsDTO = new ArrayList<HuntingExperienceDTO>();
        List<HuntingExperience> listHuntExps = hunExpDAO.findAllHuntingExperience();    
        
        for(HuntingExperience huntExp : listHuntExps){
            HuntingExperienceDTO huntExpDTO = HuntingExperienceTransformation.
                    transformToDTO(huntExp);
            listHuntExpsDTO.add(huntExpDTO);
        }
        return listHuntExpsDTO;
    }
    
    @Secured({"ROLE_SURVIVOR", "ROLE_ADMIN"})
    @Override
    public List<HuntingExperienceDTO> findEfficientWeaponExperiences(CreatureDTO creatureDTO, int effeciency){
        List<HuntingExperienceDTO> listHuntingExpDTO = new ArrayList<HuntingExperienceDTO>();
        List<HuntingExperience> listHuntExp;
        
        if (creatureDTO!=null && effeciency>=0 &&  effeciency<=100) {
            
            listHuntExp = hunExpDAO.findEfficientWeaponExperiences(CreatureTransformation.
                    transformToEntity(creatureDTO), effeciency);        
        } else {
            throw new NullPointerException("Creature must not be null and"
                    + " effeciency must be in %");
        }
        
        HuntingExperienceDTO huntExpDTO;
        for(HuntingExperience exp : listHuntExp){
            huntExpDTO = HuntingExperienceTransformation.transformToDTO(exp);
            listHuntingExpDTO.add(huntExpDTO);
        }
        
        return listHuntingExpDTO;
    }
    
}
