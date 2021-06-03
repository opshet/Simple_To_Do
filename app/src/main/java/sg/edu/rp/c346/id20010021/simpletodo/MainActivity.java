package sg.edu.rp.c346.id20010021.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView editTD;
    Button add;
    Button clear;
    Button delete;
    ListView lvtodo;
    Spinner spinAR;

    ArrayList<String> alTD;
    ArrayAdapter<String> aaTD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTD=findViewById(R.id.editTD);
        add=findViewById(R.id.add);
        delete=findViewById(R.id.delete);
        clear=findViewById(R.id.clear);
        lvtodo=findViewById(R.id.lvTd);
        spinAR=findViewById(R.id.spinner);


        alTD= new ArrayList<>();

        aaTD= new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alTD);
        lvtodo.setAdapter(aaTD);


        clear.setOnClickListener((v -> {
            alTD.clear();
            aaTD.notifyDataSetChanged();
        }));



        spinAR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        editTD.setHint("Type in a new task here");
                        delete.setEnabled(false);
                        add.setOnClickListener(v -> {
                            String newtd= editTD.getText().toString();
                            alTD.add(newtd);
                            aaTD.notifyDataSetChanged();
                            editTD.setText(null);
                        });

                    case 1:
                        editTD.setHint("Type in the index of the task to be removed");
                        add.setEnabled(false);
                        delete.setOnClickListener(v -> {
                            String check="";
                            if(editTD.getText().equals("")){
                                Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                            } else {
                                for(int i=0; i < alTD.size(); i++){
                                    if(alTD.get(i).equals(editTD.getId()) ){
                                        alTD.remove(i);
                                        aaTD.notifyDataSetChanged();
                                    } else{
                                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }



                        });

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}