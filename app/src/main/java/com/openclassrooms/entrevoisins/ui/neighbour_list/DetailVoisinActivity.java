package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailVoisinActivity extends AppCompatActivity {

    //UI COMPENANTS
    @BindView(R.id.imageButtonBack)
    ImageButton mButtonBack;

    @BindView(R.id.imageButtonFav)
    ImageButton mImageButtonFav;

    @BindView(R.id.imageViewAvatar)
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

    }
}
