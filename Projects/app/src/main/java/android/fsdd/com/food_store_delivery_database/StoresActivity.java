package android.fsdd.com.food_store_delivery_database;

        import android.app.Activity;
        import android.app.ListActivity;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;

public class StoresActivity extends ListActivity {
    ListView listView = null;
    ArrayAdapter adapter = null;
    EventListener el = new EventListener();
   // ArrayList<String> nameList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference storeRef = database.getReference("Supermarkets");
        // myRef.setValue("Hello, World!");
        storeRef.addValueEventListener(el);
        el.adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1,el.nameList);
        adapter = el.adapter;
        listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toNewClass(view);
            }
        });


    }

    public void toNewClass(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        //based on item add info to intent
        startActivity(intent);
    }

    public void refreshList(View view) {
        el.update();
    }

}

class EventListener implements ValueEventListener {
    public static ArrayList<String> nameList = new ArrayList<String>();
    public ArrayAdapter<String> adapter;
    ArrayList<String> temp;

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        nameList.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Log.println(Log.ASSERT, "MyActivity", ds.getKey());
            nameList.add(ds.getKey());
        }

        //nameArray = nameList.toArray(new String[nameList.size()]);

        if (nameList != null) {
            // Log.println(Log.ASSERT, "MyActivity", nameList.get(0));
            temp = new ArrayList<String>(nameList);
            // Log.println(Log.ASSERT, "MyActivity", temp.get(0));
            adapter.clear();
            adapter.addAll(nameList);
            adapter.notifyDataSetChanged();
            // Log.println(Log.ASSERT, "MyActivity", temp.size() + "");
        }
        nameList = temp;
}

    void update() {
        Log.println(Log.ASSERT, "MyActivity", nameList.size() + "");
        for (String str : nameList)
            Log.println(Log.ASSERT, "MyActivity", str);
        adapter.clear();
        adapter.addAll(nameList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
    }
};

