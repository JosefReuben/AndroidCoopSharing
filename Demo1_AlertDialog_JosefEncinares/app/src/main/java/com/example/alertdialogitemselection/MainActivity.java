package com.example.alertdialogitemselection;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
// Manually imported this
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

/**
 * Demo to use an alert dialog with item selection
 * Author: Josef Reuben Encinares
 */
public class MainActivity extends AppCompatActivity {

    private Button btnDialog;

    private static final CharSequence[] FEED_ITEMS =
            {"Business", "Breaking News", "World News", "Basketball News"};

    private int checkItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDialog = findViewById(R.id.btnDialog);

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });
    }



    private void showAlertDialog() {
        // Prepare the dialog by setting up a Builder.
        final String fDialogTitle = "Select News Type";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(fDialogTitle);



        // Add an OnClickListener to the dialog, so that the selection will be handled.
        builder.setSingleChoiceItems(
                FEED_ITEMS,
                checkItem,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {
                        // Locally create a finalised object.

                        // Perform an action depending on which item was selected.
                        switch (item) {
                            case 1:
                                checkItem = 1;
                                Toast.makeText(MainActivity.this, "Breaking News Selected",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                checkItem = 2;
                                Toast.makeText(MainActivity.this, "World News Selected",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 3:
                                checkItem = 3;
                                Toast.makeText(MainActivity.this, "Basketball News Selected",
                                        Toast.LENGTH_LONG).show();
                                break;
                            default:
                                checkItem = 0;
                                Toast.makeText(MainActivity.this, "Business News Selected",
                                        Toast.LENGTH_LONG).show();
                        }
                        dialog.dismiss();
                    }
                }
        );

        // Build the dialog and show it.
        AlertDialog feedTypeDialog = builder.create();
        feedTypeDialog.setCanceledOnTouchOutside(true);
        feedTypeDialog.show();
    }
}
