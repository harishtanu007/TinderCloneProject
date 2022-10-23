package com.harish.tinder.material_ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.harish.tinder.R;
import com.harish.tinder.fragments.ChatThreadFragment;
import com.harish.tinder.fragments.LikesFragment;
import com.harish.tinder.fragments.ProfileFragment;
import com.harish.tinder.fragments.SwipeFragment;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;


public class MainActivity extends AppCompatActivity {


    private SmoothBottomBar bottomBar;
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users");
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (user != null) {
            // User is signed in do nothing
            //Log.e("User:", user.getEmail().toString());
            ref = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());
        } else {
            // No user is signed in commence Login
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

        replace(new SwipeFragment());
        bottomBar = findViewById(R.id.bottomBar);

        bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                switch (i){
                    case 0:
                        replace(new SwipeFragment());
                        break;
                    case 1:
                        replace(new LikesFragment());
                        break;
                    case 2:
                        replace(new ChatThreadFragment());
                        break;
                    case 3:
                        replace(new ProfileFragment());
                        break;
                }
                return true;
            }
        });

    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(user != null){
            ref.child("online").setValue("true");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ref.child("online").setValue("false");
    }
}