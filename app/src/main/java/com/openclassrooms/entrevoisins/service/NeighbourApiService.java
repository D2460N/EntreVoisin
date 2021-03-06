package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);


    /**
     * Get all favorites Neighbours
     * @return a list
     */
    List<Neighbour> getFavorites();

    /** add Favorites neighbour.
     *
     * @param neighbour
     */
    void addFavorites(Neighbour neighbour);

    /**
     * delete Favortites neighbour
     * @param neighbour
     */

    void deleteFavorites (Neighbour neighbour);

}

