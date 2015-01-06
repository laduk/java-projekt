package cz.muni.fi.pa165.creaturehunting.api.serviceinterface;

import cz.muni.fi.pa165.creaturehunting.api.dto.LogInDTO;
import java.util.List;

/**
 *
 * @author Fita
 */
public interface LogInService {
    
    /**
     * Create Log in
     * @param logInDTO LogIN DTO boject.
     */
    public void create(LogInDTO logInDTO);

    /**
     * Update Log in
     * @param logInDTO LogIN DTO boject.
     */
    public void update(LogInDTO logInDTO);

    /**
     * Delete Log in
     * @param logInDTO LogIN DTO boject.
     */
    public void delete(LogInDTO logInDTO);

    /**
     * Find Log In by ID.
     * @param id unique indentifier of long type from Log In
     * @return Log in DTO
     */
    public LogInDTO findLogIn(long id);

    /**
     * Find All Log In.
     * @return List of LoginDTO objects.
     */
    public List<LogInDTO> findAllLogIns();

    /**
     * Find All Log in by name.
     * @param name String to be find.
     * @return List of LoginDTO objects.
     */
    public List<LogInDTO> findAllByName(String name);
    
}
