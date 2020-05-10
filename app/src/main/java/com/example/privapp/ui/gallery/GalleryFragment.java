package com.example.privapp.ui.gallery;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.privapp.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {
    ListView lista;
    Button analizar;
    ArrayAdapter<String> adapter;
    ArrayList<String> permisos;

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
        permisos = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, permisos);
        lista.setAdapter(adapter);

        analizar = (Button) getView().findViewById(R.id.analizar);
        analizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    PackageManager pm = getActivity().getPackageManager();
                    List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

                    for (ApplicationInfo applicationInfo : packages) {
                        //Log.d("test", "App: " + applicationInfo.name + " Package: " + applicationInfo.packageName);

                        //Log.d("test", "" + pm.getApplicationLabel(applicationInfo));

                        try {
                            PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);

                            //Get Permissions
                            String[] requestedPermissions = packageInfo.requestedPermissions;

                            if(requestedPermissions != null) {
                                for (int i = 0; i < requestedPermissions.length; i++) {
                                    //Log.d("test", requestedPermissions[i]);
                                    if (requestedPermissions[i].equals("android.permission.ACCESS_FINE_LOCATION")){
                                        //Log.d("test", requestedPermissions[i]);
                                        Log.d("test", "" + pm.getApplicationLabel(applicationInfo));
                                        permisos.add(""+pm.getApplicationLabel(applicationInfo));
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
            }
        });
    }



}