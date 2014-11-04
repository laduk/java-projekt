/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.creaturehunting.service.area;

import cz.muni.fi.pa165.creaturehunting.dao.area.Area;

/**
 *
 * @author Fita
 */
public class AreaTransformation {
    
    //!JUST A FAKE FOR COMPILING
    public static AreaDTO transformToDTO(Area area){
        AreaDTO areaDTO = new AreaDTO();
        return areaDTO;
    }
    
    //!JUST A FAKE FOR COMPILING
    public static Area transformToEntity(AreaDTO areaDTO){
        Area area = new Area();
        return area;
    }
}
