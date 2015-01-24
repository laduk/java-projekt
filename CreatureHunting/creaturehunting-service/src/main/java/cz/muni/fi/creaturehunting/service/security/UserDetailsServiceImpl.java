package cz.muni.fi.creaturehunting.service.security;

import cz.muni.fi.pa165.creaturehunting.api.dto.LogInDTO;
import cz.muni.fi.pa165.creaturehunting.api.serviceinterface.LogInService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
    
        final LogInDTO logInDTO = service.findByName(string);
        
        if (logInDTO==null) {
            throw new UsernameNotFoundException("Searching login of name: " + string +" was not succesful.");
        }
        
        
        return new UserDetails(){
        
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                GrantedAuthority grantedAuthority = new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return "ROLE_SURVIVOR"; 
                    }
                };
                
                 List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
                listAuthorities.add(grantedAuthority);
                
                if (logInDTO.isIsRoot()) {
                    GrantedAuthority adminAuthority = new GrantedAuthority() {
                        @Override
                        public String getAuthority() {
                            return "ROLE_ADMIN";
                        }
                    };
                    listAuthorities.add(adminAuthority);
                }

                return Collections.unmodifiableList(listAuthorities);
                
            }
            
                        @Override
            public String getPassword() {
                return logInDTO.getPassword();
            }

            @Override
            public String getUsername() {
                return logInDTO.getName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
            
            
            };
        }
        
        //return null;
  }

