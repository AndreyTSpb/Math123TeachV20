package potaskun.enot.math123teachv20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /**
         * Кнопка входа
         */
        Button buttonEnter = findViewById(R.id.buttonEnter);
        buttonEnter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(testAccess()){
            Intent intent = new Intent(this, SelectGroupActivity.class);
            /**
             * передадим айди и имя препода
             */
            String idTeach = "1234";
            String nameTeach = "Васько Пупкин";

            intent.putExtra("idTeach", idTeach);
            intent.putExtra("nameTeach", nameTeach);

            startActivity(intent);
        }else
        {
            error = findViewById(R.id.error);
            error.setText("Неправильный логин или пароль");
        }
    }

    /**
     * проверка ожно ли войти
     * @return
     */
    private boolean testAccess(){
        return true;
    }
}
