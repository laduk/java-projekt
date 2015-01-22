package cz.muni.fi.creaturehunting.service.security;

import cz.muni.fi.pa165.creaturehunting.api.dto.LogInDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 
 */
@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    LogInService service;

    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
    
        final LogInDTO logInDTO = service.findByName(string);
        
        if (logInDTO==null) {
            throw new UsernameNotFoundException("Searching login of name: " + string +" was not succesful.");
        }
        return null;
   }
    
}
