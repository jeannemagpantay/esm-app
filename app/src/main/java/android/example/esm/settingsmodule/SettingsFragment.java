package android.example.esm.settingsmodule;

import android.content.Context;
import android.content.Intent;
import android.example.esm.loginmodule.LoginActivity;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.esm.R;
import android.widget.Button;


public class SettingsFragment extends Fragment {

    CardView logoutButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        logoutButton = (CardView) rootView.findViewById(R.id.logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        return rootView;



    }

    private void logout(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }


}
