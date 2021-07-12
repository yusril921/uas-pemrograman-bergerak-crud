package co.dcc.registrasipesertaworkshop;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import co.dcc.registrasipesertaworkshop.db.SQLiteDB;
import co.dcc.registrasipesertaworkshop.model.Contact;

import co.dcc.registrasipesertaworkshop.db.SQLiteDB;

/**
 * Created by docotel on 5/2/16.
 */
public class ActActivity extends AppCompatActivity {

    private EditText personName;
    private EditText phone;
    private EditText email;
    private TextView head;
    private Button btnAdd, btnEdit, btnDelete;

            private SQLiteDB sqLiteDB;
            private Contact contact;

    public static void start(Context context){
        Intent intent = new Intent(context, ActActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context, Contact contact){
        Intent intent = new Intent(context, ActActivity.class);
        intent.putExtra(ActActivity.class.getSimpleName(), contact);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        personName = findViewById(R.id.Enama);
        phone = findViewById(R.id.Etlp);
        email = findViewById(R.id.Eemail);
        head = findViewById(R.id.header);


        btnAdd = findViewById(R.id.btnDaftar);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

//        btnAdd.setOnClickListener(this);
//        btnEdit.setOnClickListener(this);
//        btnDelete.setOnClickListener(this);

        contact = getIntent().getParcelableExtra(ActActivity.class.getSimpleName());
        if(contact != null){
            btnAdd.setVisibility(View.GONE);

            head.setText("Profil");
            personName.setText(contact.getName());
            phone.setText(contact.getPhone());
            email.setText(contact.getEmail());
        }
        else{
            btnEdit.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
        }

        sqLiteDB = new SQLiteDB(this);

       btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (personName.getText().toString().equals("")|| phone.getText().toString().equals("") || email.getText().toString().equals(""))
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(ActActivity.this);
                    builder.setMessage("Data Tidak Boleh Kosong...!!!")
                            .setIcon(R.drawable.ic_allert_red)
                            .setCancelable(true)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).show();

                }
                else
                {
                    contact = new Contact();
                contact.setName(personName.getText().toString());
                contact.setPhone(phone.getText().toString());
                contact.setEmail(email.getText().toString());
                sqLiteDB.create(contact);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(ActActivity.this);
                    builder.setMessage("Pendaftaran Berhasil...!!!")
                            .setIcon(R.drawable.ic_allert_red)
                            .setCancelable(true)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    personName.setText("");
                                    phone.setText("");
                                    email.setText("");
                                    finish();

                                }
                            }).show();
                }
            }
        });
       btnEdit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               contact.setName(personName.getText().toString());
            contact.setPhone(phone.getText().toString());
            contact.setEmail(email.getText().toString());
            sqLiteDB.update(contact);

               final AlertDialog.Builder builder = new AlertDialog.Builder(ActActivity.this);
               builder.setMessage("Update Berhasil...!!!")
                       .setIcon(R.drawable.ic_allert_red)
                       .setCancelable(true)
                       .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int id) {
                               dialog.cancel();
                               personName.setText("");
                               phone.setText("");
                               email.setText("");
                               finish();

                           }
                       }).show();
            Toast.makeText(ActActivity.this, "Edited!", Toast.LENGTH_SHORT).show();

           }
       });
       btnDelete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final AlertDialog.Builder builder = new AlertDialog.Builder(ActActivity.this);
               builder.setMessage("Anda Yakin Ingin Menghapus Data...!!!")
                       .setIcon(R.drawable.ic_allert_red)
                       .setCancelable(true)
                       .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               sqLiteDB.delete(contact.getId());
                               finish();
                           }
                       })
                       .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                               finish();
                           }
                       }).show();
//               Toast.makeText(ActActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();

           }
       });
    }


//    @Override
//    public void onClick(View v) {
//        if(v == btnAdd){
//            if (personName.equals("")|| phone.equals("") || email.equals(""))
//            {
//                final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                builder.setMessage("Data Tidak Boleh Kosong...!!!")
//                .setIcon(R.drawable.ic_allert_red)
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//
//                }
//            }).show();
//
//            }
//            else
//            {
//                contact = new Contact();
//                contact.setName(personName.getText().toString());
//                contact.setPhone(phone.getText().toString());
//                contact.setEmail(email.getText().toString());
//                sqLiteDB.create(contact);
//
////                Toast.makeText(getApplicationContext(), "adsafadsf",Toast.LENGTH_SHORT).show();
//
//                final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                builder.setMessage("Berhasil Daftar...!!!")
//                        .setIcon(R.drawable.ic_allert_red)
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                            }
//                        });
//                recreate();
//            }
//
//        }
//        else if(v == btnEdit){
//            contact.setName(personName.getText().toString());
//            contact.setPhone(phone.getText().toString());
//            contact.setEmail(email.getText().toString());
//            sqLiteDB.update(contact);
//
//            Toast.makeText(this, "Edited!", Toast.LENGTH_SHORT).show();
//            finish();
//        }else if(v == btnDelete){
//            sqLiteDB.delete(contact.getId());
//
//            Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
//            finish();
//        }
//    }
    private void dialogshow(){


    }
}
