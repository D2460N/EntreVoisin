package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailVoisinActivity extends AppCompatActivity {

    private NeighbourApiService mApiService = DI.getNeighbourApiService();
    private Neighbour mNeighbour;
    private Intent m_intent;

    //UI COMPENANTS
    @BindView(R.id.imageButtonBack)
    ImageButton mButtonBack;

    @BindView(R.id.imageButtonFav)
    ImageButton mImageButtonFav;

    @BindView(R.id.image_avatar)
    ImageView mImageViewAvatar;

    @BindView(R.id.nameProfil)
    TextView mTextViewNameProfil;

    @BindView(R.id.activity_name_text)
    TextView mTextViewNameText;

    @BindView(R.id.activity_phone_text)
    TextView mTextViewPhoneText;

    @BindView(R.id.activity_mail_text)
    TextView mTextViewMailText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_voisin);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();

        if (getIntent().hasExtra("Neighbour")) {
            Neighbour neighbour = getIntent().getParcelableExtra("Neighbour");

            Glide.with(this)
                    .load(neighbour.getAvatarUrl())
                    .into(mImageViewAvatar);

            mTextViewNameProfil.setText(neighbour.getName());
            mTextViewMailText.setText("www.facebook.fr/" + neighbour.getName());
        }


        mImageButtonFav.setOnClickListener(v -> {


        });

        mButtonBack.setOnClickListener(v -> {
            Intent intent = new Intent(DetailVoisinActivity.this, ListNeighbourActivity.class);
            startActivity(intent);


        });
    }


}







