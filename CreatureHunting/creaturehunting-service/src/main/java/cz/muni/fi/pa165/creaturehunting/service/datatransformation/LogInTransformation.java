package cz.muni.fi.pa165.creaturehunting.service.datatransformation;

import cz.muni.fi.pa165.creaturehunting.api.dto.LogInDTO;
import cz.muni.fi.pa165.creaturehunting.data.entity.LogIn;

/**
 *
 * @author Fita
 */
public class LogInTransformation {
    
    /**
     * Transform entity object into DTO.
     * 
     * @param logIn Given entity object.
     * @return LogInDTO so Data transfer object for given entity
     */
    public static LogInDTO transformToDTO(LogIn logIn) {
        if (logIn == null) {
            throw new NullPointerException("Entity object for transformation"
                    + " cannot be null");
        }
        
        LogInDTO logInDTO = new LogInDTO();
        logInDTO.setId(logIn.getId());
        logInDTO.setName(logIn.getName());
        logInDTO.setPassword(logIn.getPassword());
        logInDTO.setRole(logIn.getRole());
        return logInDTO;
    }
    
    /**
     * Transform DTO back to entity object.
     * 
     * @param logInDTO Given DTO object
     * @return LogIn that is entity object.
     */
    public static LogIn transformToEntity(LogInDTO logInDTO) {
        if (logInDTO == null) {
            throw new NullPointerException("Data transfer object for transformation"
                    + " cannot be null");
        }
        
        LogIn logIn = new LogIn();
        logIn.setId(logInDTO.getId());
        logIn.setName(logInDTO.getName());
        logIn.setPassword(logInDTO.getPassword());
        logIn.setRole(logInDTO.getRole());
        return logIn;
    }
}
