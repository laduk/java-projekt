package cz.muni.fi.pa165.creaturehunting.service.area;

import cz.muni.fi.pa165.creaturehunting.dao.area.Area;
import cz.muni.fi.pa165.creaturehunting.dao.area.AreaDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public void setAreaDao(AreaDAO areaDAO) {
        if (areaDAO==null) {
            throw new IllegalArgumentException("areaDAO mus not be null!");           
        }
        this.areaDAO = areaDAO;
    }

    /**
     * Create an area.
     *
     * @param areaDTO - data transfer object as a source of data
     */
    public void create(AreaDTO areaDTO) {
        if (areaDTO==null) {
            throw new IllegalArgumentException("AreaExpDTO must not null!");
        }
        Area area = new Area();
        try {
            area = AreaTransformation.transformToEntity(areaDTO);
        } catch (Exception e) {
            throw new IllegalArgumentException("Id must not null!",e);
        }
        areaDAO.createArea(area);
    }

    /**
     * Update an area.
     *
     * @param areaDTO - data transfer object as a source of data
     */
    public void update(AreaDTO areaDTO) {
        if (areaDTO==null) {
            throw new IllegalArgumentException("AreaDTO must not null!");
        }
        Area area = new Area();
        try {
            area = AreaTransformation.transformToEntity(areaDTO);
        } catch (Exception e) {
            throw new IllegalArgumentException("HuntExpDTO must not null!",e);
        }
        areaDAO.updateArea(area);
    }

    /**
     * Delete an area.
     *
     * @param areaDTO - data transfer object as a source of data
     */
    public void delete(AreaDTO areaDTO) {
        if (areaDTO==null) {
            throw new IllegalArgumentException("AreaExpDTO must not null!");
        }
        areaDAO.deleteArea(AreaTransformation.transformToEntity(areaDTO));
    }

    /**
     * Find an area by ID. 
     * @param id given id to be find
     * @return area data transfer object
     */
    public AreaDTO findArea(Long id) {
        if (id==null) {
            throw new IllegalArgumentException("Id must not null!");
        }
        Area area;
        area = areaDAO.findArea(id);
        
        AreaDTO areaDTO = new AreaDTO();
        try {
            areaDTO = AreaTransformation.transformToDTO(area);
        } catch (IllegalArgumentException e) {
            return areaDTO;
        }
        return areaDTO;
    }

    /**
     * Find all areas.
     *
     * @return list of area data transfer objects
     */
    public List<AreaDTO> findAllAreas() {
        List<AreaDTO> listAreasDTO = new ArrayList<AreaDTO>();
        List<Area> listAreas;
        listAreas = areaDAO.findAllAreas();
        
        for(Area area : listAreas){
            AreaDTO areaDTO = new AreaDTO();
            try {
                areaDTO = AreaTransformation.transformToDTO(area);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Wrong transformation from entity to DTO",e);
            }
            listAreasDTO.add(areaDTO);
        }
        return listAreasDTO;
    }
    
}
