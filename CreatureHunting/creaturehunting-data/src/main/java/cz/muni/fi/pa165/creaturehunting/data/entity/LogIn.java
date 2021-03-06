package cz.muni.fi.pa165.creaturehunting.data.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class represents the Log in.
 * 
 * @author Fita
 */
@Entity
public class LogIn implements Serializable {
    
    /**
     * Unique identifier of logIn.
     * If Id > 0 then login is already created.
     */
    @Id
    @GeneratedValue
    private long id = -1;
    
    /**
     * Name or nickname of the user.
     * Maximal length is 50 chars.
     */
    @Column(nullable=false,unique=true,length=50)
    private String name;
    
    /**
     * Password for the account.
     * Not allowed to be null.
     */
    @Column(nullable=false)
    private String password;
    
    /**
     * Identifier whether user has or has not root rights.
     * Not allowed to be null.
     */
    @Column(nullable=false)
    private String role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LogIn other = (LogIn) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;    }
    
    
    
    @Override
    public String toString() {
        return "LogIn [id=" + id + ", name=" + name + ", password=" + password 
                + ", role=" + role +  "]";              
    }
}
