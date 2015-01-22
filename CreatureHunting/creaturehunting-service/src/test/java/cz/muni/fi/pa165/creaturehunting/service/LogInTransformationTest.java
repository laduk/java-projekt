package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.api.dto.LogInDTO;
import cz.muni.fi.pa165.creaturehunting.data.entity.LogIn;
import cz.muni.fi.pa165.creaturehunting.service.datatransformation.LogInTransformation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Fita
 */
public class LogInTransformationTest {
    
    private LogInDTO logInDTO;
    private LogIn logIn;
    
    /**
     * Creating testing objects.
     */
    @Before
    public void setUp() {
        logInDTO = new LogInDTO();
        logIn = new LogIn();
        
        logInDTO.setId(1);
        logInDTO.setIsRoot(Boolean.TRUE);
        logInDTO.setName("creator");
        logInDTO.setPassword("aS1dF2gH3jK4l");
        
        logIn.setId(1);
        logIn.setIsRoot(Boolean.TRUE);
        logIn.setName("creator");
        logIn.setPassword("aS1dF2gH3jK4l");          
    }
    
    /**
     * Testing transforming to DTO.
     */
    @Test
    public void testTransfrormToDTO(){       
        System.out.println("--- Transform to DTO");
        Assert.assertTrue("Transformation Errror or modification of parameters DTO",
                logInDTO.equals(LogInTransformation.transformToDTO(logIn)));
    }
    
     /**
     * Testing transforming to Entity.
     */
    @Test
    public void testTransfrormToEntity(){       
        System.out.println("--- Transform to Entity");
        Assert.assertTrue("Transformation Errror or modification of parameters DTO",
                logIn.equals(LogInTransformation.transformToEntity(logInDTO)));
    }
}
