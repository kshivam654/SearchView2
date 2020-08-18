package app.sharma.searchview2;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchField;
    private ImageButton mSearchBtn;

    private RecyclerView mResultList;

    private DatabaseReference mUserDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Locations");

        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);

        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);

            }
        });

    }

    private void firebaseUserSearch(String searchText) {

        Toast.makeText(MainActivity.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("names").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<Places, PlacesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Places, PlacesViewHolder>(

                Places.class,
                R.layout.list_layout,
                PlacesViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(PlacesViewHolder viewHolder, Places model, int position) {


                viewHolder.setDetails(getApplicationContext(), model.getName(), model.getDescription(), model.getImage());

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }

    // Places Holder Class

    public static class PlacesViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public PlacesViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(Context ctx, String userName, String userStatus, String userImage){
            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
            TextView user_status = (TextView) mView.findViewById(R.id.status_text);
            ImageView user_image = (ImageView) mView.findViewById(R.id.profile_image);

            user_name.setText(userName);
            user_status.setText(userStatus);

            Glide.with(ctx).load(userImage).into(user_image);

        }

    }


}