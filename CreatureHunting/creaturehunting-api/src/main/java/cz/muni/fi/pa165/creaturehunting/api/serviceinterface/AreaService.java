package cz.muni.fi.pa165.creaturehunting.api.serviceinterface;

import cz.muni.fi.pa165.creaturehunting.api.dto.AreaDTO;
import java.util.List;

/**
 *
 * @author Fita
 */
public interface AreaService {
    /**
     * Create Area
     * @param areaDTO area DTO object
     */
    public void create(AreaDTO areaDTO);

    /**
     * Update Area
     * @param areaDTO area DTO object
     */
    public void update(AreaDTO areaDTO);

    /**
     * Delete Area 
     * @param areaDTO area DTO object
     */
    public void delete(AreaDTO areaDTO);

    /**
     * Find Area by the ID.
     * @param long id - ID of the area
     * @return AreaDTO object
     */
    public AreaDTO findArea(Long id);

    
    /**
     * Find all Areas.
     * @return list of AreaDTO objects
     */
    public List<AreaDTO> findAllAreas();
    
    /**
     * Find all Areas by given name.
     * @param name String parameter it is name of area to be search
     * @return list of areasSTO
     * @returnlist of AreaDto
     */
    public List<AreaDTO> findAllByName(String name);
}
