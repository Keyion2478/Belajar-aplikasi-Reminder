package com.example.reminderapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class AddEditReminderActivity extends AppCompatActivity {

    EditText edtTitle, edtDescription, edtDate, edtTime;
    Spinner spCategory, spRepeat;
    Button btnSave, btnCancel;

    DBHelper dbHelper;

    int reminderId = -1;

    String[] categories = {
            "Pribadi",
            "Pekerjaan",
            "Belanja",
            "Daftar Keinginan"
    };

    String[] repeats = {
            "Sekali",
            "Harian",
            "Mingguan"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        edtTitle = findViewById(R.id.edtTitle);
        edtDescription = findViewById(R.id.edtDescription);
        edtDate = findViewById(R.id.edtDate);
        edtTime = findViewById(R.id.edtTime);

        spCategory = findViewById(R.id.spCategory);
        spRepeat = findViewById(R.id.spRepeat);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        dbHelper = new DBHelper(this);

        setupPickers();

        ArrayAdapter<String> categoryAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        categories
                );

        spCategory.setAdapter(categoryAdapter);

        ArrayAdapter<String> repeatAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        repeats
                );

        spRepeat.setAdapter(repeatAdapter);

        if (getIntent().hasExtra("id")) {

            reminderId = getIntent().getIntExtra("id", -1);
            
            TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
            tvHeaderTitle.setText("Edit Pengingat");

            edtTitle.setText(getIntent().getStringExtra("title"));
            edtDescription.setText(getIntent().getStringExtra("description"));
            edtDate.setText(getIntent().getStringExtra("date"));
            edtTime.setText(getIntent().getStringExtra("time"));

            String category =
                    getIntent().getStringExtra("category");

            String repeat =
                    getIntent().getStringExtra("repeat");

            for (int i = 0; i < categories.length; i++) {
                if (categories[i].equals(category)) {
                    spCategory.setSelection(i);
                    break;
                }
            }

            for (int i = 0; i < repeats.length; i++) {
                if (repeats[i].equals(repeat)) {
                    spRepeat.setSelection(i);
                    break;
                }
            }
        }

        btnSave.setOnClickListener(v -> {

            String title = edtTitle.getText().toString().trim();
            String description = edtDescription.getText().toString().trim();
            String date = edtDate.getText().toString().trim();
            String time = edtTime.getText().toString().trim();

            String category =
                    spCategory.getSelectedItem().toString();

            String repeat =
                    spRepeat.getSelectedItem().toString();

            if (title.isEmpty()) {

                Toast.makeText(
                        this,
                        "Title wajib diisi",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            if (reminderId == -1) {

                dbHelper.insertReminder(
                        title,
                        description,
                        date,
                        time,
                        category,
                        repeat,
                        "Pending"
                );

                Toast.makeText(
                        this,
                        "Reminder berhasil ditambahkan",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                dbHelper.updateReminder(
                        reminderId,
                        title,
                        description,
                        date,
                        time,
                        category,
                        repeat,
                        "Pending"
                );

                Toast.makeText(
                        this,
                        "Reminder berhasil diupdate",
                        Toast.LENGTH_SHORT
                ).show();
            }

            setResult(RESULT_OK);
            finish();

        });

        btnCancel.setOnClickListener(v -> finish());

    }

    private void setupPickers() {
        edtDate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        edtDate.setText(String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year1));
                    }, year, month, day);
            datePickerDialog.show();
        });

        edtTime.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute1) -> {
                        edtTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute1));
                    }, hour, minute, true);
            timePickerDialog.show();
        });
    }

}
