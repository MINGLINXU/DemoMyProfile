package sg.edu.rp.c346.emptyactivity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup radioGP;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA=findViewById(R.id.editTextGPA);
        radioGP = findViewById(R.id.rgGender);
        btnSave = findViewById(R.id.buttonSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Step 1a: Get the user input from the EditText and store it in a variable
                String strName = etName.getText().toString();
                Float GPA = Float.parseFloat(etGPA.getText().toString());
                int RG = radioGP.getCheckedRadioButtonId();

                //Step 1b: Obtain an instance of the SharedPreference
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                //Step 1c: Obtain an instance of the SharedPreference Editor for update later
                SharedPreferences.Editor preEdit = prefs.edit();
                //Step 1d: Add the key-value pair
                preEdit.putString("name", strName);
                preEdit.putFloat("gpa", GPA);
                preEdit.putInt("radiobox", RG);

                //Step 1e: Call commit(); to save the change into SharedPreferences
                preEdit.commit();
            }

        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Step 1a: Get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();
        Float GPA = Float.parseFloat(etGPA.getText().toString());
        int RG = radioGP.getCheckedRadioButtonId();

        //Step 1b: Obtain an instance of the SharedPreference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 1c: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor preEdit = prefs.edit();
        //Step 1d: Add the key-value pair
        preEdit.putString("name",strName);
        preEdit.putFloat("gpa",GPA);
        preEdit.putInt("radiobox",RG);

        //Step 1e: Call commit(); to save the change into SharedPreferences
        preEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 2b: Retrieve the saved data from the SharedPreferences object
        String msg = prefs.getString("name","");
        Float GPA = prefs.getFloat("gpe",2.0f);
        int Rg = prefs.getInt("radiobox",0);
        //Step 2c: Update the UI element with the value
        etName.setText(msg);
        etGPA.setText(""+GPA);
        radioGP.check(Rg);

    }
}
