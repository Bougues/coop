package be.groupe7lsinf1225.minipoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AccountCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);
    }

    /** Called when the user taps the Send button */
    public void createAccount(View view) {
        Intent intent = new Intent(this, ProfileCreationActivity.class);
        startActivity(intent);
        // Do something in response to button
    }
}
