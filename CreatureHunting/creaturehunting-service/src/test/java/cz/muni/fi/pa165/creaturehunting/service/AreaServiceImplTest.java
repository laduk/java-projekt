package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.dao.DAOException;
import cz.muni.fi.pa165.creaturehunting.dao.area.Area;
import cz.muni.fi.pa165.creaturehunting.dao.area.AreaDAO;
import cz.muni.fi.pa165.creaturehunting.service.area.AreaDTO;
import cz.muni.fi.pa165.creaturehunting.service.area.AreaService;
import cz.muni.fi.pa165.creaturehunting.service.area.AreaServiceImpl;
import cz.muni.fi.pa165.creaturehunting.service.area.AreaTransformation;
import cz.muni.fi.pa165.creaturehunting.service.creature.CreatureDTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.dao.DataAccessException;

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
    @Test (expected = DataAccessException.class)
    public void testCreateArea() {
        areaService.create(areaDTO);
        
        verify(areaDAO).
                createArea(AreaTransformation.transformToEntity(areaDTO));
        doThrow(DAOException.class).when(areaDAO).
                createArea(any(Area.class));

        areaService.create(areaDTO);
    }
    
    /**
     * Test update area.
     */
    @Test (expected = DataAccessException.class)
    public void testUpdateArea() {
        areaService.update(areaDTO);
        
        verify(areaDAO).
                updateArea(AreaTransformation.transformToEntity(areaDTO));
        doThrow(DAOException.class).when(areaDAO).
                updateArea(any(Area.class));

        areaService.update(areaDTO);
    }
    
    /**
     * Test delete area.
     */
    @Test (expected = DataAccessException.class)
    public void testDeleteArea() {
        areaService.delete(areaDTO);
        
        verify(areaDAO).
                deleteArea(AreaTransformation.transformToEntity(areaDTO));
        doThrow(DAOException.class).when(areaDAO).
                deleteArea(any(Area.class));

        areaService.delete(areaDTO);
    }
    
    /**
     * Test find area by id.
     */
    @Test (expected = DataAccessException.class)
    public void testFindArea() {
        when(areaDAO.findArea(any(Long.class))).
                thenReturn(AreaTransformation.transformToEntity(areaDTO));
        
        long pom = 51;
        Assert.assertTrue("Wrong area was found.", 
                areaService.findArea(pom).equals(areaDTO));
        
        doThrow(DAOException.class).when(areaDAO).
                findArea(anyInt());

        areaService.findArea(pom);
    }
    
    /**
     * Test find all areas by id.
     */
    @Test (expected = DataAccessException.class)
    public void testFindAllAreas() {
        List<Area> areas = new ArrayList();
        areas.add(AreaTransformation.transformToEntity(areaDTO));
        when(areaDAO.findAllAreas()).thenReturn(areas);
        
        Assert.assertTrue("Wrong list of areas was found.", 
                areaService.findAllAreas().get(0).equals(areaDTO));

        doThrow(DAOException.class).when(areaDAO).findAllAreas();

        areaService.findAllAreas();
    }
}
