/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwth.recommender.interestbased.api.exception;

/**
 *
 * @author Marco
 */
public class UserModelNotValidException extends Exception {

    /**
     * Creates a new instance of
     * <code>UserModelNotValidException</code> without detail message.
     */
    public UserModelNotValidException() {
    }

    /**
     * Constructs an instance of
     * <code>UserModelNotValidException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public UserModelNotValidException(String msg) {
	super(msg);
    }
}
