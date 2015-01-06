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
    private Boolean isRoot;

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

    public Boolean getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Boolean isRoot) {
        this.isRoot = isRoot;
    }
}
