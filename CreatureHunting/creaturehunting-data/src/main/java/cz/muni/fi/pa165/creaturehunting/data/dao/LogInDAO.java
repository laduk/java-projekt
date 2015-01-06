package cz.muni.fi.pa165.creaturehunting.data.dao;

import cz.muni.fi.pa165.creaturehunting.data.entity.LogIn;
import java.util.List;

/**
 * Interface of Data Access Object for logging in.
 * @author Fita
 */
public interface LogInDAO {
    
    /**
     * Create the given login in the database. The login ID must not be > 0,
     * otherwise it will throw IllegalArgumentException. After creating,
     * the DAO will set the obtained ID in the given login.
     * @param logIn The login to be created in the database.
     * @throws IllegalArgumentException If the login ID is null.
     */
    public void create(LogIn logIn)throws IllegalArgumentException;
    
    /**
     * Update the given login in the database. The login ID must be > 0,
     * otherwise it will throw IllegalArgumentException.
     * @param logIn The login to be updated in the database.
     * @throws IllegalArgumentException If the login ID is null.
     */
    public void update(LogIn logIn) throws IllegalArgumentException;
    
    /**
     * Delete the given login from the database. After deleting,
     * the DAO will set the ID of the given login to not > 0.
     * @param logIn The login to be deleted from the database.
     */
    public void delete(LogIn logIn);
    
     /**
     * Returns the login from the database matching the given ID, otherwise null.
     * @param id The ID of the login to be returned.
     * @return The login from the database matching the given ID, otherwise null.
     */
    public LogIn findLogIn(long id);
    
    /**
     * Returns a list of all logins from the database ordered by area ID. 
     * The list is never null and is empty when the database does not contain 
     * any login.
     * @return A list of all log in datas from the database ordered by area ID.
     */
    public List<LogIn> findAll();
}
