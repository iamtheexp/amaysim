/**
 * 
 */
package com.amaysim.dao.interfaces;

/**
 * An interface that contains the generic database functionalities to access a
 * table.
 * 
 * @author r
 * 
 */
public interface IBaseDao<T> {

    /**
     * Inserts a new record in a table.
     * 
     * @param params
     */
    public void create(T params);

    /**
     * Selects a record from a table given the primary key.
     * 
     * @param id
     * @return
     */
    public T retrieve(int id);

    /**
     * Updates a record in a table given the primary key.
     * 
     * @param params
     */
    public void update(T params);

    /**
     * Deletes a record in a table given the primary key.
     * 
     * @param id
     */
    public void delete(int id);
}