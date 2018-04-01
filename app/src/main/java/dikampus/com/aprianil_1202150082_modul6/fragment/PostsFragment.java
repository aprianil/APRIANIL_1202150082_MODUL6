package dikampus.com.aprianil_1202150082_modul6.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by MOTION-2 on 01/04/2018.
 */

public class PostsFragment extends Fragment{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Post> listPosts;

    DatabaseReference database;

    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        database = FirebaseDatabase.getInstance().getReference(MainActivity.table_1);

        recyclerView = view.findViewById(R.id.rv_posts);
        listPosts = new ArrayList<>();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        database.orderByChild("timestamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPosts.clear();

                for (DataSnapshot posts : dataSnapshot.getChildren()){
                    Post post = posts.getValue(Post.class);
                    listPosts.add(post);
                }
                recyclerView.setHasFixedSize(true);
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
