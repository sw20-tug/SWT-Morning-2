package com.swt20.swt_morning2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class WhiteTilesMenuFragment extends Fragment {


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tiles_menu, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WhiteTilesSettings settings =
                new WhiteTilesSettings(getContext().getApplicationContext());

        boolean timerEnabled = settings.getTimerEnabled();
        Integer timeSetting = settings.getTimerSetting();

        getTimerSwitch(view).setChecked(timerEnabled);
        getTimerSetting(view).setText(String.format(timeSetting.toString()));


        view.findViewById(R.id.tiles_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                boolean timerEnabled = getTimerSwitch(view).isChecked();
                int timeSetting = Integer.parseInt(getTimerSetting(view).getText().toString());

                WhiteTilesSettings settings =
                        new WhiteTilesSettings(getContext().getApplicationContext());

                settings.setTimerEnabled(timerEnabled);
                settings.setTimerSetting(timeSetting);

                NavHostFragment.findNavController(WhiteTilesMenuFragment.this)
                        .navigate(R.id.action_Menu_to_Game);
            }
        });
    }

    private Switch getTimerSwitch(View view) {
        return (Switch) view.findViewById(R.id.whiteTilesTimerSwitch);
    }

    private EditText getTimerSetting(View view) {
        return (EditText) view.findViewById(R.id.whiteTilesTimeSetting);

    }


}
