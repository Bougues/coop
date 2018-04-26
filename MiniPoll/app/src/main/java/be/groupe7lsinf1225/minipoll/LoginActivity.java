package be.groupe7lsinf1225.minipoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /** Called when the user taps the Send button */
    public void signUp(View view) {
        Intent intent = new Intent(this, AccountCreationActivity.class);
        startActivity(intent);
        // Do something in response to button
    }

}
