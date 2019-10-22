package com.openclassrooms.entrevoisins.service;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favorites = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * return a list of favorites neighbour
     *
     * @return
     */
    @Override
    public List<Neighbour> getFavorites() {

      return favorites;
    }

    /**
     * add neighbours to favorites list
     * @param neighbour
     */

    @Override
    public void addFavorites(Neighbour neighbour) {
        if(!favorites.contains(neighbour)){
            favorites.add(neighbour);
        }
    }

    /**
     * delete neighbours from favorites list
     * @param neighbour
     */
    @Override
    public void deleteFavorites(Neighbour neighbour) {

        if(favorites.contains(neighbour)){

            favorites.remove(neighbour);
        }
    }
}
