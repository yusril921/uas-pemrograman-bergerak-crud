package co.dcc.registrasipesertaworkshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import co.dcc.registrasipesertaworkshop.db.SQLiteDB;
import co.dcc.registrasipesertaworkshop.model.Contact;

public class SpalshActivity extends AppCompatActivity {
    private SQLiteDB sqLiteDB;
    private Contact contact;

//    public static void start(Context context) {
//        Intent intent = new Intent(context, ActActivity.class);
//        context.startActivity(intent);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spalshscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), RegistrasiActivity.class));
                finish();
            }
        },2000);
    }
}
