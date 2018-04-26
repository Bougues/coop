package be.groupe7lsinf1225.minipoll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ProfileCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);
        ImageButton profileImage = (ImageButton) findViewById(R.id.imageButton4);
        profileImage.setImageResource(R.drawable.ic_launcher_background);
    }
}
