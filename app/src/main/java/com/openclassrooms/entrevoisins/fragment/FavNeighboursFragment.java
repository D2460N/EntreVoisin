package com.openclassrooms.entrevoisins.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.DetailVoisinActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FavNeighboursFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavNeighboursFragment extends Fragment implements MyNeighbourRecyclerViewAdapter.onItemListener {

    private NeighbourApiService mApiService;
    private List<Neighbour> mFavNeighbours;
    private RecyclerView mRecyclerView;
    private MyNeighbourRecyclerViewAdapter mAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FavNeighboursFragment.
     */
    public static FavNeighboursFragment newInstance() {

        FavNeighboursFragment fragment = new FavNeighboursFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav_neighbours, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initList();
        return view;
    }
    /**
     * Init the List of Favneighbours
     */

    private void initList() {
        mFavNeighbours = mApiService.getFavorites();
        mAdapter = new MyNeighbourRecyclerViewAdapter(this.mFavNeighbours,this,MyNeighbourRecyclerViewAdapter.ListType.FAVORITE);
        mRecyclerView.setAdapter(mAdapter);


    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }
    @Override
    public void onResume() {
        super.onResume();
        initList();
    }
    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteFavNeighbour(DeleteFavNeighbourEvent event) {
        mApiService.deleteFavorites(event.neighbour);
        initList();
    }

    /**
     * get position when the item is clicked
     * start activity detailVoisin
     * @param position
     */

    @Override
    public void onItemClick(int position) {
        Context context = getActivity();
        Intent intent = new Intent(context, DetailVoisinActivity.class);
        intent.putExtra("Neighbour", mFavNeighbours.get(position));
        startActivity(intent);
    }

}

