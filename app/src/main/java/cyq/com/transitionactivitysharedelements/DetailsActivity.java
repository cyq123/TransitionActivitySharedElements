package cyq.com.transitionactivitysharedelements;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;

    public static final String TRANSITION_NAME_IMG = "transitionImg";
    public static final String TRANSITION_NAME_TXT = "transitionTxt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = findViewById(R.id.img);
        textView = findViewById(R.id.txt);

        String itemText = getIntent().getStringExtra("item");

        ViewCompat.setTransitionName(imageView,TRANSITION_NAME_IMG);
        ViewCompat.setTransitionName(textView,TRANSITION_NAME_TXT);

        textView.setText(itemText);
    }
}
