package co.dcc.registrasipesertaworkshop;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import co.dcc.registrasipesertaworkshop.adapter.ContactListAdapter;
import co.dcc.registrasipesertaworkshop.db.SQLiteDB;
import co.dcc.registrasipesertaworkshop.listener.RecyclerItemClickListener;
import co.dcc.registrasipesertaworkshop.model.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerItemClickListener {

    private RecyclerView lvContact;
    private FloatingActionButton btnAdd;

    private ContactListAdapter contactListAdapter;
    private LinearLayoutManager linearLayoutManager;

    private SQLiteDB sqLiteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContact = (RecyclerView) findViewById(R.id.lvContact);
        btnAdd = (FloatingActionButton) findViewById(R.id.add);

        linearLayoutManager = new LinearLayoutManager(this);
        contactListAdapter = new ContactListAdapter(this);
        contactListAdapter.setOnItemClickListener(this);

        lvContact.setLayoutManager(linearLayoutManager);
        lvContact.setAdapter(contactListAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActActivity.start(MainActivity.this);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    void loadData(){
        sqLiteDB = new SQLiteDB(this);

        List<Contact> contactList = new ArrayList<>();

        Cursor cursor = sqLiteDB.retrieve();
        Contact contact;

        if (cursor.moveToFirst()) {
            do {

                contact = new Contact();

                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
                contact.setEmail(cursor.getString(3));

                contactList.add(contact);
            }while (cursor.moveToNext());
        }

        contactListAdapter.clear();
        contactListAdapter.addAll(contactList);
    }

    @Override
    public void onItemClick(int position, View view) {
        ActActivity.start(this, contactListAdapter.getItem(position));
    }
}
