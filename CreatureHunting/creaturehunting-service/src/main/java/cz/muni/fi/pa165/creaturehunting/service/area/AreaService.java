/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.creaturehunting.service.area;

import java.util.List;

/**
 *
 * @author Fita
 */
public interface AreaService {
    /*
     * Create Area
     * @param areaDTO area DTO object
     */
    public void create(AreaDTO areaDTO);

    /*
     * Update Area
     * @param areaDTO area DTO object
     */
    public void update(AreaDTO areaDTO);

    /*
     * Delete Area 
     * @param areaDTO area DTO object
     */
    public void delete(AreaDTO areaDTO);

    /*
     * Find Area by the ID.
     * @param long id - ID of the area
     * @return AreaDTO object
     */
    public AreaDTO findArea(long id);

    
    /*
     * Find all Areas.
     * @return list of AreaDTO objects
     */
    public List<AreaDTO> findAllAreas();
}
