package cz.muni.fi.pa165.creaturehunting.service.serviceimpl;

import cz.muni.fi.pa165.creaturehunting.api.dto.LogInDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.LogInService;
import cz.muni.fi.pa165.creaturehunting.data.dao.LogInDAO;
import cz.muni.fi.pa165.creaturehunting.data.entity.LogIn;
import cz.muni.fi.pa165.creaturehunting.service.datatransformation.LogInTransformation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of service layer interface.
 * 
 * @author Fita
 */
@Service
@Transactional
public class LogInServiceImpl implements LogInService{
    
    @Autowired
    private LogInDAO logInDAO;
    
    /**
     *
     * @param logInDAO
     */
    public LogInServiceImpl(LogInDAO logInDAO){
        if (logInDAO==null) {
            throw new NullPointerException("LogInDAO must not be null!");           
        }
        this.logInDAO=logInDAO;
    }

    /**
     * Create a Log In.
     * 
     * @param lidto data transfer object as a source of data
     */
    @Secured({"ROLE_ADMIN"})
    @Override
    public void create(LogInDTO lidto) {
        if (lidto == null) {
            throw new  NullPointerException("LogInDTO argument cannot be null.");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        lidto.setPassword(passwordEncoder.encode(lidto.getPassword()));
        logInDAO.create(LogInTransformation.transformToEntity(lidto));
    }

    /**
     *  Update the Log in.
     * 
     * @param lidto data transfer object as a source of data
     */
    @Secured({"ROLE_ADMIN"})
    @Override
    public void update(LogInDTO lidto) {
        if (lidto == null) {
            throw new  NullPointerException("LogInDTO argument cannot be null.");
        }
        String hash = findLogIn(lidto.getId()).getPassword();
        if (!lidto.getPassword().equals(hash)) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            lidto.setPassword(passwordEncoder.encode(lidto.getPassword()));    
        }
        logInDAO.update(LogInTransformation.transformToEntity(lidto));
    }

    /**
     *  Delete the Log In.
     * 
     * @param lidto data transfer object as a source of data
     */
    @Secured({"ROLE_ADMIN"})
    @Override
    public void delete(LogInDTO lidto) {
        if (lidto == null) {
            throw new  NullPointerException("LogInDTO argument cannot be null.");
        }
        logInDAO.delete(LogInTransformation.transformToEntity(lidto));
    }

    /**
     * Find DTO object with the same id. 
     * 
     * @param l is id of typy long to be find
     * @return 
     */
    @Secured({"ROLE_SURVIVOR", "ROLE_ADMIN"})
    @Override
    public LogInDTO findLogIn(long l) {
        if (l < 0) {
            throw new IllegalArgumentException("ID of the wanted login cannot "
                    + "be negative");
        }
        LogInDTO lidto = LogInTransformation.transformToDTO(logInDAO.findLogIn(l));
        return lidto;
    }

    /**
     * Find All existing Logins.
     * 
     * @return List of data transfer objects. 
     */
    @Secured({"ROLE_ADMIN"})
    @Override
    public List<LogInDTO> findAllLogIns() {
        List<LogIn> logIns = logInDAO.findAll();
        List<LogInDTO> logInDTOs = new ArrayList<LogInDTO>();
        for (LogIn loq : logIns) {
            logInDTOs.add(LogInTransformation.transformToDTO(loq));
        }
        return logInDTOs;
    }

    /**
     * Find All of given name.
     * 
     * @param string Given name to be find in All LogIns.
     * @return List of data transfer objects. 
     */
    @Secured({"ROLE_SURVIVOR", "ROLE_ADMIN"})
    @Override
    public LogInDTO findByName(String string) {
        if (string == null) {
            throw new NullPointerException("LogInDTO argument cannot be null.");
        }
        for (LogInDTO logInDTO : this.findAllLogIns()){
            if (logInDTO.getName().toLowerCase().equals(string.toLowerCase())) {
                return logInDTO;               
            }
        }
        return null;
    }    
}
