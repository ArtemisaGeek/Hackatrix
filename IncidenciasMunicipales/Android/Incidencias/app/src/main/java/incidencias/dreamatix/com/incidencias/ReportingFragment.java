package incidencias.dreamatix.com.incidencias;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class ReportingFragment extends Fragment {
    public static final int TAKE_PHOTO_CODE = 1337;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private  View rootView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ReportingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportingFragment newInstance() {
        ReportingFragment fragment = new ReportingFragment();
        Bundle args = new Bundle();
        /*args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    public ReportingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_register_incident, container, false);
        ImageView imvPicture  = (ImageView) rootView.findViewById(R.id.imvPicture);
        imvPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }
        });
        //
        Button btnSave = (Button) rootView.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           EditText edtDNI = (EditText) rootView.findViewById(R.id.edtDNI);
                                           EditText edtDescription = (EditText) rootView.findViewById(R.id.edtDescription);
                                           ParseManager parseManager = ParseManager.getInstance(getActivity().getApplicationContext());
                                           ImageView imvPicture= (ImageView) rootView.findViewById(R.id.imvPicture);
                                           imvPicture.setDrawingCacheEnabled(true);

                                           imvPicture.buildDrawingCache();

                                           Bitmap bm = imvPicture.getDrawingCache();
                                           ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                           bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                           byte[] byteArray = stream.toByteArray();
                                           parseManager.sendToParse(edtDNI.getText().toString(), edtDescription.getText().toString(),byteArray, 30, 40);

                                       }
                                   }
        );
        return rootView;

    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == TAKE_PHOTO_CODE && resultCode == getActivity().RESULT_OK) {
            Toast.makeText(getActivity().getApplicationContext(), "CameraDemo: Pic saved", Toast.LENGTH_LONG).show();
            ImageView imvPicture = (ImageView) rootView.findViewById(R.id.imvPicture);
            Bundle extras = data.getExtras();
            Bitmap mImageBitmap = (Bitmap) extras.get("data");
            imvPicture.setImageBitmap(mImageBitmap);
        }
    }

}
