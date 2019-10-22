package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getNeighbourFavorite (){
        List<Neighbour> mFavneighbours = service.getNeighbours();
        List<Neighbour> expectedmFavNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(mFavneighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedmFavNeighbours.toArray()));


    }

    @Test
    public void addNeighbourFavorite (){
        Neighbour neighbour = new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d");
        service.addFavorites(neighbour);
        assertEquals(1, service.getFavorites().size());



    }

    @Test
    public void deleteNeighbourFavorite (){
        Neighbour neighbour = new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d");
        service.addFavorites(neighbour);
        Neighbour neighbourToDelete = service.getFavorites().get(0);
        service.deleteFavorites(neighbourToDelete);
        assertFalse(service.getFavorites().contains(neighbourToDelete));
    }
}
