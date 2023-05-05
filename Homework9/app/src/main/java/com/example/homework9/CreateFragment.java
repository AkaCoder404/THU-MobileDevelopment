package com.example.homework9;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int REQUEST_READ_EXTERNAL_STORAGE_PERMISSION = 2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final int PICK_IMAGE_REQUEST_CODE = 1;
    private static final int PICK_IMAGE_REQUEST = 1;
    // Custom
    private Uri imageUri;
    private ImageView imageView;
    private Button btnImage;
    private EditText title;
    private EditText description;
    private SharedPreferences prefs;

    public CreateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateFragment newInstance(String param1, String param2) {
        CreateFragment fragment = new CreateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create, container, false);

        title = view.findViewById(R.id.title_edittext);
        description = view.findViewById(R.id.description_edittext);

        // Set an onClickListener to handle publishing action
        Button btnPublish = view.findViewById(R.id.btn_publish);
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to create a new post object and save it to a data source
                Log.d("DEBUG", "Create posts finished");

                // TODO: Temporary photos auto upload
                int[] mImages = { R.drawable.testphoto1,
                        R.drawable.testphoto1,
                        R.drawable.testphoto1,
                        R.drawable.testphoto1,
                        R.drawable.ic_launcher_foreground,
                        R.drawable.ic_launcher_foreground,
                        R.drawable.ic_launcher_foreground,
                        R.drawable.ic_launcher_foreground,
                        R.drawable.ic_launcher_foreground,
                };
                Post mPost = new Post("litq18", title.getText().toString(), description.getText().toString(), mImages);

                // Send intent back to home activity
//                Intent resultIntent = new Intent();
//                resultIntent.putExtra("POST_RESULT", mPost); // add any data you want to send back
//                getActivity().setResult(Activity.RESULT_OK, resultIntent);
//                getActivity().finish(); // Close the activity after publishing

                // Send to new post fragment
                Bundle bundle = new Bundle();
                bundle.putParcelable("POST_RESULT", (Parcelable) mPost);

//                HomeNewPostsFragment receivingFragment = new HomeNewPostsFragment();
//                receivingFragment.setArguments(bundle);
//
//                FragmentManager fragmentManager = getParentFragmentManager();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container, receivingFragment)
//                        .commit();

                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.navigateToHomeFragment(bundle);
                }

            }
        });

        imageView = view.findViewById(R.id.imageView);

        loadDraft();

        // Set onclick listener for save
        Button btnSave = view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               saveDraft();
            }
        });

        // Set onclick Listener for upload image btn
        btnImage = view.findViewById(R.id.btn_upload_image);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // In your click listener for the image button:
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });


        return view;
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        saveDraft();
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveDraft();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            Log.d("DEBUG", "Select Image Success");
            imageUri = data.getData();
            Glide.with(this).load(imageUri).into(imageView);
        }
    }


    private void saveDraft() {
        // Get the current text values
        String titleText = title.getText().toString();
        String descriptionText = description.getText().toString();


        // Save the text values to SharedPreferences
        prefs = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        String imageUriStr =  imageUri != null ? imageUri.toString() : null;

        String imageUriStr = "";
        if (prefs.getString("image_uri", null) == null) {
            imageUriStr =  imageUri != null ? imageUri.toString() : null;
        }
        else imageUriStr = prefs.getString("image_uri", null);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("title", titleText);
        editor.putString("description", descriptionText);
        editor.putString("image_uri", imageUriStr);
        editor.apply(); // Commit the changes asynchronously
        Log.d("DEBUG", "Saved image: " + imageUriStr);
    }

    private void loadDraft() {
        // Load the saved text values and image URI from SharedPreferences
        prefs = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String titleText = prefs.getString("title", "");
        String descriptionText = prefs.getString("description", "");
        String imageUriStr = prefs.getString("image_uri", null);
        Log.d("DEBUG", "Load Image: " + ( imageUriStr == null ? "null" : imageUriStr) );


        // Set the text values to the views
        title.setText(titleText);
        description.setText(descriptionText);

        Glide.with(this).load(imageUriStr).into(imageView);

//        if (imageUriStr != null) {
//            if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                    == PackageManager.PERMISSION_GRANTED) {
//                Log.d("DEBUG", "Loading Image");
//                loadImage(imageUriStr);
//            } else {
//                Log.d("DEBUG", "Request Permissions");
//                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        REQUEST_READ_EXTERNAL_STORAGE_PERMISSION);
//            }
//        }

    }

    private void loadImage(String imageUrl) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Uri uri = Uri.parse(imageUrl);
//            imageView.setImageUri(uri);
            imageView.setImageURI(uri);
        } else {
            Glide.with(this).load(imageUrl).into(imageView);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("DEBUG", "OnRequestPermissionsResult");
        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String imageUrl = prefs.getString("image_uri", null);
                if (imageUrl != null) {
                    loadImage(imageUrl);
                }
            } else {
                Toast.makeText(getContext(), "Permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }


}