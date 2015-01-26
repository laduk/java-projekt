package cz.muni.fi.pa165.creaturehunting.service.serviceimpl;

import cz.muni.fi.pa165.creaturehunting.service.datatransformation.AreaTransformation;
import cz.muni.fi.pa165.creaturehunting.data.entity.Area;
import cz.muni.fi.pa165.creaturehunting.data.dao.AreaDAO;
import cz.muni.fi.pa165.creaturehunting.api.dto.AreaDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.AreaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matej Cizik
 */
@Service
@Transactional
public class AreaServiceImpl implements AreaService {
    
    private AreaDAO areaDAO;

    public AreaServiceImpl(AreaDAO areaDAO) {
        this.areaDAO = areaDAO;
    }

    /**
     * Create an area.
     *
     * @param areaDTO - data transfer object as a source of data
     */
    @Secured({"ROLE_ADMIN", "ROLE_REST"})
    @Override
    public void create(AreaDTO areaDTO) {
        if (areaDTO==null) {
            throw new NullPointerException("AreaExpDTO must not null!");
        }
        Area area = AreaTransformation.transformToEntity(areaDTO);
        areaDAO.createArea(area);        
    }

    /**
     * Update an area.
     *
     * @param areaDTO - data transfer object as a source of data     
     */
    @Secured({"ROLE_ADMIN", "ROLE_REST"})
    @Override
    public void update(AreaDTO areaDTO) {
        if (areaDTO==null) {
            throw new NullPointerException("AreaDTO must not null!");
        }
        Area area = AreaTransformation.transformToEntity(areaDTO);
        areaDAO.updateArea(area);        
    }

    /**
     * Delete an area.
     *
     * @param areaDTO - data transfer object as a source of data
     */
    @Secured({"ROLE_ADMIN", "ROLE_REST"})
    @Override
    public void delete(AreaDTO areaDTO) {
        if (areaDTO==null) {
            throw new NullPointerException("AreaExpDTO must not null!");
        }
        areaDAO.deleteArea(AreaTransformation.transformToEntity(areaDTO));        
    }

    /**
     * Find an area by ID. 
     * @param id given id to be find
     * @return area data transfer object
     */
    @Secured({"ROLE_SURVIVOR", "ROLE_ADMIN", "ROLE_REST"})
    @Override
    public AreaDTO findArea(Long id) {
        if (id==null) {
            throw new NullPointerException("Id must not null!");
        }
        Area area =  areaDAO.findArea(id);              
        AreaDTO areaDTO = AreaTransformation.transformToDTO(area);
      
        return areaDTO;
    }

    /**
     * Find all areas.
     *
     * @return list of area data transfer objects
     */
    @Secured({"ROLE_SURVIVOR", "ROLE_ADMIN", "ROLE_REST"})
    @Override
    public List<AreaDTO> findAllAreas() {
        List<AreaDTO> listAreasDTO = new ArrayList<AreaDTO>();
        List<Area> listAreas = areaDAO.findAllAreas();
        
        for(Area area : listAreas){
            AreaDTO areaDTO = AreaTransformation.transformToDTO(area);          
            listAreasDTO.add(areaDTO);
        }
        return listAreasDTO;
    }

    /**
     * Find all areas by name.
     * @param name
     * @return list of area data transfer objects
     */
    @Secured({"ROLE_SURVIVOR", "ROLE_ADMIN"})
    @Override
    public List<AreaDTO> findAllByName(String name) {
        List<AreaDTO> areaByName = new ArrayList();

        for (AreaDTO areaDTO : this.findAllAreas()){
            if (areaDTO.getName().toLowerCase().contains(name.toLowerCase())) {
                areaByName.add(areaDTO);
            }
        }
        return areaByName;
    }    
}
