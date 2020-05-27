package com.example.privapp.ui.gallery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.privapp.Adaptador;
import com.example.privapp.Modelo;
import com.example.privapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GalleryFragment extends Fragment {

    private ListView lista;
    private TextView appsGPSR, appsTotalR, porcentaje;
    private Button analizar;
    private ArrayList<Modelo> mList = new ArrayList<>();
    private Adaptador mAdapter;

    private GalleryViewModel galleryViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        lista = (ListView) getView().findViewById(R.id.lista);
        appsGPSR = (TextView) getView().findViewById(R.id.appsGPSR);
        appsTotalR = (TextView) getView().findViewById(R.id.appsTotalR);
        porcentaje = (TextView) getView().findViewById(R.id.porcentaje);


        analizar = (Button) getView().findViewById(R.id.analizar);
        analizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    PackageManager pm = getActivity().getPackageManager();
                    List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
                    int appsGPS = 0;
                    appsTotalR.setText(Integer.toString(packages.size()));
                    for (ApplicationInfo applicationInfo : packages) {
                        try {
                            PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
                            //Get Permissions
                            String[] requestedPermissions = packageInfo.requestedPermissions;
                            if(requestedPermissions != null) {
                                for (int i = 0; i < requestedPermissions.length; i++) {
                                    if (requestedPermissions[i].equals("android.permission.ACCESS_FINE_LOCATION")){
                                        appsGPS++;
                                        String name = ""+pm.getApplicationLabel(applicationInfo);
                                        Drawable image = pm.getApplicationIcon(applicationInfo.packageName);
                                        mList.add(new Modelo(name, image));

                                    }
                                }
                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                appsGPSR.setText(Integer.toString(appsGPS));
                    porcentaje.setText(Integer.toString(appsGPS*100/packages.size()) + "%");
                mAdapter = new Adaptador(getActivity().getBaseContext(), R.layout.list_custom, mList);
                lista.setAdapter(mAdapter);
            }
        });
    }


}
