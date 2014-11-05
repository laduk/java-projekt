/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.creaturehunting.service;

import cz.muni.fi.pa165.creaturehunting.dao.huntingexperience.HuntingExperienceDAO;
import cz.muni.fi.pa165.creaturehunting.service.huntingexperience.HuntingExperienceDTO;
import cz.muni.fi.pa165.creaturehunting.service.huntingexperience.HuntingExperienceServiceImpl;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Fita
 */
public class HuntingExperienceServiceImplTest {
    
    @InjectMocks
    private HuntingExperienceServiceImpl huntExpSerImpl;
    
    @Mock
    private HuntingExperienceDAO huntingExperienceDAO;
    
    private HuntingExperienceDTO experienceDTO;
    
    @Before
    public void initMock(){
        MockitoAnnotations.initMocks(this);
        
        experienceDTO =

    }
    
}
