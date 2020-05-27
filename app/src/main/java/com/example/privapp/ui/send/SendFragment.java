package com.example.privapp.ui.send;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.privapp.R;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;
    private MediaPlayer mediaPlayer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //View v = inflater.inflate(R.layout.fragment_slideshow, container, false);
        //ImageView imageView = (ImageView) v.findViewById(R.id.imageView3);

        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.crude_mobile_ringtone_converter);
        mediaPlayer.start();

        /*Glide.with(this)
                .asGif()
                .load(R.raw.gavilan)
                .into(imageView);*/

        return root;
    }
    /*@Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // Make sure that we are currently visible
        if (this.isVisible()) {
            // If we are becoming invisible, then...
            if (!isVisibleToUser) {
                mediaPlayer.stop();
            }
            else {
                // do what you like
            }
        }
    }*/
    @Override
    public void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }
}