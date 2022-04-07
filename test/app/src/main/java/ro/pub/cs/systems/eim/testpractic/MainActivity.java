package ro.pub.cs.systems.eim.testpractic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox checkBoxName;
    CheckBox checkBoxGroup;

    Button navigateToAnotherActivity;
    Button displayInformation;

    TextView textView;

    EditText nameEditText;
    EditText groupEditText;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            String name = "";
            String group = "";
            String newStr = "";
            switch (view.getId()) {
                case R.id.display_edit_text:
                    if (checkBoxName.isChecked()) {
                        name = nameEditText.getText().toString();
                        newStr += name;
                    } else {
                        Toast.makeText(getApplicationContext(), "The activity returned with result " + Constants.NOT_CHECKED, Toast.LENGTH_LONG).show();
                    }
                    if (checkBoxGroup.isChecked()) {
                        group = groupEditText.getText().toString();
                        newStr += " " +  group;
                    } else {
                        Toast.makeText(getApplicationContext(), "The activity returned with result " + Constants.NOT_CHECKED, Toast.LENGTH_LONG).show();
                    }

                    textView.setText(newStr);
                    break;
                case R.id.navigate_to_another_activity:
                    Intent intent = new Intent(getApplicationContext(), SecondaryActivity.class);
                    String nameI = nameEditText.getText().toString();
                    String groupI = groupEditText.getText().toString();
                    intent.putExtra(Constants.NAME, nameI);
                    intent.putExtra(Constants.GROUP, groupI);
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxGroup = (CheckBox) findViewById(R.id.checkbox_group);
        checkBoxName= (CheckBox) findViewById(R.id.checkbox_name);

        navigateToAnotherActivity = (Button) findViewById(R.id.navigate_to_another_activity);
        displayInformation = (Button) findViewById(R.id.display_edit_text);
        displayInformation.setOnClickListener(buttonClickListener);

        textView = (TextView) findViewById(R.id.textview);
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        groupEditText = (EditText) findViewById(R.id.group_edit_text);


        if (savedInstanceState != null) {

            if (savedInstanceState.containsKey(Constants.GROUP)) {
                groupEditText.setText(savedInstanceState.getString(Constants.GROUP));
            } else{
                groupEditText.setText("");
            }

            if (savedInstanceState.containsKey(Constants.NAME)) {
                nameEditText.setText(savedInstanceState.getString(Constants.NAME));
            } else {
                nameEditText.setText("");
            }
        } else {
            nameEditText.setText("");
            groupEditText.setText("");
        }

        navigateToAnotherActivity = (Button)findViewById(R.id.navigate_to_another_activity);
        navigateToAnotherActivity.setOnClickListener(buttonClickListener);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.NAME, nameEditText.getText().toString());
        outState.putString(Constants.GROUP, groupEditText.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.NAME)) {
            nameEditText.setText(savedInstanceState.getString(Constants.NAME));
        } else {
            nameEditText.setText("");
        }

        if (savedInstanceState.containsKey(Constants.GROUP)) {
            groupEditText.setText(savedInstanceState.getString(Constants.GROUP));
        } else {
            groupEditText.setText("");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

//    @Override
//    protected void onDestroy() {
//        Intent intent = new Intent(this, PracticalTestService.class);
//        stopService(intent);
//        super.onDestroy();
//    }
//}
}