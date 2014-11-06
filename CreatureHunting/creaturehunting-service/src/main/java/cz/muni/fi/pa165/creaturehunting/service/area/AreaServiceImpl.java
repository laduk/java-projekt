package cz.muni.fi.pa165.creaturehunting.service.area;

import cz.muni.fi.pa165.creaturehunting.dao.DAOException;
import cz.muni.fi.pa165.creaturehunting.dao.area.Area;
import cz.muni.fi.pa165.creaturehunting.dao.area.AreaDAO;
import cz.muni.fi.pa165.creaturehunting.service.exception.DataAccessExceptionService;
import java.util.ArrayList;
import java.util.List;
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
    public void create(AreaDTO areaDTO) {
        if (areaDTO==null) {
            throw new NullPointerException("AreaExpDTO must not null!");
        }
        Area area = AreaTransformation.transformToEntity(areaDTO);
        try {
            areaDAO.createArea(area);
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not "
                    + "succesful or data was corrupted.",e);
        }
    }

    /**
     * Update an area.
     *
     * @param areaDTO - data transfer object as a source of data     
     */
    public void update(AreaDTO areaDTO) {
        if (areaDTO==null) {
            throw new NullPointerException("AreaDTO must not null!");
        }
        Area area = AreaTransformation.transformToEntity(areaDTO);
        try {
            areaDAO.updateArea(area);
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not "
                    + "succesful or data was corrupted.",e);
        }
    }

    /**
     * Delete an area.
     *
     * @param areaDTO - data transfer object as a source of data
     */
    public void delete(AreaDTO areaDTO) {
        if (areaDTO==null) {
            throw new NullPointerException("AreaExpDTO must not null!");
        }
        try {
            areaDAO.deleteArea(AreaTransformation.transformToEntity(areaDTO));
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not "
                    + "succesful or data was corrupted.",e);
        }
    }

    /**
     * Find an area by ID. 
     * @param id given id to be find
     * @return area data transfer object
     */
    public AreaDTO findArea(Long id) {
        if (id==null) {
            throw new NullPointerException("Id must not null!");
        }
        Area area = new Area();        
        try {
            area = areaDAO.findArea(id);
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not "
                    + "succesful or data was corrupted.",e);
        }        
        AreaDTO areaDTO = AreaTransformation.transformToDTO(area);
      
        return areaDTO;
    }

    /**
     * Find all areas.
     *
     * @return list of area data transfer objects
     */
    public List<AreaDTO> findAllAreas() {
        List<AreaDTO> listAreasDTO = new ArrayList<AreaDTO>();
        List<Area> listAreas = new ArrayList<Area>();
        try {
            listAreas = areaDAO.findAllAreas();
        } catch (DAOException e) {
            throw new DataAccessExceptionService("Transformation was not "
                    + "succesful or data was corrupted.",e);
        }
        
        for(Area area : listAreas){
            AreaDTO areaDTO = AreaTransformation.transformToDTO(area);          
            listAreasDTO.add(areaDTO);
        }
        return listAreasDTO;
    }
    
}
