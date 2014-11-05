/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.creaturehunting.service.exception;

import org.springframework.dao.DataAccessException;

/**
 * Exception on Service Layer that extend DataAccessException class.
 * @author Fita
 */
public class DataAccessExceptionService extends DataAccessException{

    /**
     * New exception with messege about cause.
     * @param msg Messege with description of error to be thrown.
     */
    public DataAccessExceptionService(String msg) {
        super(msg);
    }
    
    /**
     * New exception that propage a cause of error.
     * @param message Messege with description of error to be thrown.
     * @param cause The init cuase of exception.
     */
    public DataAccessExceptionService(String message, Throwable cause) {
        super(message, cause);
    }
}
