package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

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
     * @return
     */
    @Override
    public List<Neighbour> getFavorites() {
        return favorites;
    }

    @Override
    public void addFavorites(Neighbour neighbour) {

        favorites.add(neighbour);


    }

    @Override
    public void deleteFavorites(Neighbour neighbour) {
        favorites.remove(neighbour);


    }


}