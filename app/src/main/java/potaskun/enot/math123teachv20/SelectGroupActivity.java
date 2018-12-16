package potaskun.enot.math123teachv20;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SelectGroupActivity extends AppCompatActivity {
    private TextView textDate;
    private Calendar calendar = Calendar.getInstance();
    private ArrayList<SelectGroups> selectGroups;
    private SelectGroupsAdapter adapter;

    /*Конст*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_group);
        /*
         * Кнопка возврата
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Global.NAME_TECH = intent.getStringExtra("nameTeach");
        Global.ID_TECH = intent.getStringExtra("idTeach");

        Global.HESH_KEY = Generator.getRandomString(15);
        /*Menu*/
        /*
        Текущяя дата
         */
        textDate = findViewById(R.id.textDate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date now = new Date(calendar.getTimeInMillis());
        textDate.setText(sdf.format(now));

        /**
         * Выбор даты при нажатии на текущию
         */
        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, day);
                        setTextView();
                    }
                };
                DatePickerDialog dateDialog = new DatePickerDialog(SelectGroupActivity.this, dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                dateDialog.show();
            }
        });

        /**
         * вЫВОД списка групп где есть текущая дата
         */
        selectGroups = new ArrayList<>();
        for(int i=1; i<9; i++){
            selectGroups.add(new SelectGroups("Группа№ "+i, i));
        }

        ListView listGroup = findViewById(R.id.listGroup);
        adapter = new SelectGroupsAdapter(this, R.layout.items_select_groups, selectGroups);
        listGroup.setAdapter(adapter);
    }
    private void setTextView() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date d = new Date(calendar.getTimeInMillis());
        textDate.setText(sdf.format(d));
    }

    /**
     * Переход в QR код сканер
     * @param name
     * @param id
     */
    public void goQrCode(String name, int id){
        Intent intent = new Intent(this, QrCodeScannerActivity.class);
        intent.putExtra("NameGroup", name);
        intent.putExtra("idGroup", id);
        startActivity(intent);
    }

    /**
     * Переход в список учеников группы
     * @param name
     * @param id
     */
    public void goToGroup(String name, int id){
        Intent intent = new Intent(this, StudentsInGroupActivity.class);
        intent.putExtra("NameGroup", name);
        //intent.putExtra("idGroup", ""+id);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.service_menu, menu);
        return true;
    }

    /**
     * Метод для кнопки возврата
     * возврата к предыдущему экрану, в котором мы просто завершаем работу текущего:
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.test:
            {
                Intent intent = new Intent(this, TestActivity.class);
                startActivity(intent);
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
