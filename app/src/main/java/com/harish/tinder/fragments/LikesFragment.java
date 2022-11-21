package com.harish.tinder.fragments;

import static com.harish.tinder.model.Constants.CONNECTIONS;
import static com.harish.tinder.model.Constants.MATCHES;
import static com.harish.tinder.model.Constants.NAME;
import static com.harish.tinder.model.Constants.PROFILE_IMAGE_URL;
import static com.harish.tinder.model.Constants.USERS;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.harish.tinder.R;
import com.harish.tinder.adapter.LikesAdapter;
import com.harish.tinder.alert.SweetAlertDialog;
import com.harish.tinder.listeners.UserItemClickListener;
import com.harish.tinder.model.UserObject;
import com.harish.tinder.utils.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LikesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LikesFragment extends Fragment implements UserItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView.Adapter mLikesAdapter;

    private String currentUserID;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mLikesView;

    public LikesFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LikesFragment newInstance(String param1, String param2) {
        LikesFragment fragment = new LikesFragment();
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

    private void getUserMatchId() {
        DatabaseReference matchDb = FirebaseDatabase.getInstance().getReference().child(USERS).child(currentUserID).child(CONNECTIONS).child(MATCHES);
        matchDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot match : dataSnapshot.getChildren()) {
                        FetchMatchInformation(match.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void FetchMatchInformation(String key) {
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child(USERS).child(key);
        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String userId = dataSnapshot.getKey();
                    String name = "";
                    String profileImageUrl = "";
                    if (dataSnapshot.child(NAME).getValue() != null) {
                        name = Objects.requireNonNull(dataSnapshot.child(NAME).getValue()).toString();
                    }
                    if (dataSnapshot.child(PROFILE_IMAGE_URL).getValue() != null) {
                        profileImageUrl = Objects.requireNonNull(dataSnapshot.child(PROFILE_IMAGE_URL).getValue()).toString();
                    }


                    UserObject obj = new UserObject(userId, name, profileImageUrl);
                    resultsMatches.add(obj);
                    mLikesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private final ArrayList<UserObject> resultsMatches = new ArrayList<UserObject>();

    private List<UserObject> getDataSetMatches() {
        return resultsMatches;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mLikesView = inflater.inflate(R.layout.fragment_courses_stagged, container, false);
        Context mcontext = mLikesView.getContext();
        currentUserID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        mLikesAdapter = new LikesAdapter(getDataSetMatches(), mcontext, this);
        getUserMatchId();

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        RecyclerView mRecyclerView = mLikesView.findViewById(R.id.rv_courses);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setClipToPadding(false);
        mRecyclerView.setHasFixedSize(true);


        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.horizontal_card);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true, 0));

        mRecyclerView.setAdapter(mLikesAdapter);

        return mLikesView;
    }


    //TODO: This code must be replaced
    @Override
    public void onUserClick(UserObject userObject, ImageView imageView) {
        new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this file!")
                .setCancelText("No,cancel plx!")
                .setConfirmText("Yes,delete it!")
                .showCancelButton(true)
                .setCancelClickListener(sDialog -> {
                    // reuse previous dialog instance, keep widget user state, reset them if you need
                    sDialog.setTitleText("Cancelled!")
                            .setContentText("Your imaginary file is safe :)")
                            .setConfirmText("OK")
                            .showCancelButton(false)
                            .setCancelClickListener(null)
                            .setConfirmClickListener(null)
                            .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                    // or you can new a SweetAlertDialog to show
                           /* sDialog.dismiss();
                            new SweetAlertDialog(SampleActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Cancelled!")
                                    .setContentText("Your imaginary file is safe :)")
                                    .setConfirmText("OK")
                                    .show();*/
                })
                .setConfirmClickListener(sDialog -> sDialog.setTitleText("Deleted!")
                        .setContentText("Your imaginary file has been deleted!")
                        .setConfirmText("OK")
                        .showCancelButton(false)
                        .setCancelClickListener(null)
                        .setConfirmClickListener(null)
                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE))
                .show();
    }
}