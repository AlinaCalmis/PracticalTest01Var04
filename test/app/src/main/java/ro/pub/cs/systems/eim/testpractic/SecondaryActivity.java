package ro.pub.cs.systems.eim.testpractic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondaryActivity extends AppCompatActivity {

    private TextView name;
    private TextView group;
    private Button okButton, cancelButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ok_button:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel_button:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        name = (TextView)findViewById(R.id.name);
        group = (TextView)findViewById(R.id.group);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.NAME)) {
//            String nameF = intent.getDataString();
            String nameF = intent.getStringExtra(Constants.NAME);
            name.setText(nameF);
        }
        if (intent != null && intent.getExtras().containsKey(Constants.GROUP)) {
//            String groupF = intent.getDataString();
            String groupF = intent.getStringExtra(Constants.GROUP);
            group.setText(groupF);
        }

        okButton = (Button)findViewById(R.id.ok_button);
        okButton.setOnClickListener(buttonClickListener);
        cancelButton = (Button)findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(buttonClickListener);
    }
}