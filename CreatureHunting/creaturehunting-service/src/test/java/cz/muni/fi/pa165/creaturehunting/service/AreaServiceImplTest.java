package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.dao.entity.Area;
import cz.muni.fi.pa165.creaturehunting.dao.dao.AreaDAO;
import cz.muni.fi.pa165.creaturehunting.api.dto.AreaDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.AreaService;
import cz.muni.fi.pa165.creaturehunting.service.serviceimpl.AreaServiceImpl;
import cz.muni.fi.pa165.creaturehunting.service.datatransformation.AreaTransformation;
import cz.muni.fi.pa165.creaturehunting.api.dto.CreatureDTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class tests methods from AreaServiceImpl.
 * 
 * @author Matej Čižik
 */
public class AreaServiceImplTest {
    private AreaDTO areaDTO;
    private AreaDAO areaDAO;
    private AreaService areaService;
    
    @Before
    public void beforeClass() {
        areaDTO = new AreaDTO();
        areaDTO.setId(51);
        areaDTO.setName("Brno");
        areaDTO.setAcreage(236);
        areaDTO.setDescription("Beautiful city");
        
        List<CreatureDTO> creaturesDTO = new ArrayList();
        CreatureDTO creatureDTO = new CreatureDTO();
        creatureDTO.setId(51);
        creaturesDTO.add(creatureDTO);
        areaDTO.setListOfCreatures(creaturesDTO);
        
        areaDAO = mock(AreaDAO.class);
        areaService = new AreaServiceImpl(areaDAO);
    }
    
    /**
     * Test create area.
     */
    @Test
    public void testCreateArea() {
        areaService.create(areaDTO);
        
        verify(areaDAO).
                createArea(AreaTransformation.transformToEntity(areaDTO));
    }
    
    /**
     * Test update area.
     */
    @Test
    public void testUpdateArea() {
        areaService.update(areaDTO);
        
        verify(areaDAO).
                updateArea(AreaTransformation.transformToEntity(areaDTO));
    }
    
    /**
     * Test delete area.
     */
    @Test
    public void testDeleteArea() {
        areaService.delete(areaDTO);
        
        verify(areaDAO).
                deleteArea(AreaTransformation.transformToEntity(areaDTO));
    }
    
    /**
     * Test find area by id.
     */
    @Test
    public void testFindArea() {
        when(areaDAO.findArea(any(Long.class))).
                thenReturn(AreaTransformation.transformToEntity(areaDTO));
        
        long pom = 51;
        Assert.assertTrue("Wrong area was found.", 
                areaService.findArea(pom).equals(areaDTO));
    }
    
    /**
     * Test find all areas by id.
     */
    @Test
    public void testFindAllAreas() {
        List<Area> areas = new ArrayList();
        areas.add(AreaTransformation.transformToEntity(areaDTO));
        when(areaDAO.findAllAreas()).thenReturn(areas);
        
        Assert.assertTrue("Wrong list of areas was found.", 
                areaService.findAllAreas().get(0).equals(areaDTO));
    }
}
