package dikampus.com.aprianil_1202150082_modul6.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by MOTION-2 on 01/04/2018.
 */

public class MyPostFragment extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Post> listPosts;

    Query database;

    public MyPostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //initialize firebaseauth instance
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_posts, container, false);

        recyclerView = view.findViewById(R.id.rv_myposts);
        recyclerView.setHasFixedSize(true);

        database = FirebaseDatabase.getInstance().getReference(MainActivity.table_1).orderByChild("userID").equalTo(mAuth.getCurrentUser().getUid());
        listPosts = new ArrayList<>();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPosts.clear();

                for (DataSnapshot posts : dataSnapshot.getChildren()){
                    Post post = posts.getValue(Post.class);
                    listPosts.add(post);
                }
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                PostAdapter postAdapter = new PostAdapter(getContext(), listPosts);
                recyclerView.setAdapter(postAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
