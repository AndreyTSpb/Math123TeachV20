package potaskun.enot.math123teachv20;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RequestTask extends AsyncTask<String, String, String> {
    private ProgressDialog dialog;
    private String login;
    private String pass;

    public RequestTask(HashMap<String, String> param){
        login = param.get("login");
        pass = param.get("pass");
    }
    @Override
    protected String doInBackground(String... param) {
        try{
            //создаем запрос на сервер
            DefaultHttpClient hc = new DefaultHttpClient();
            ResponseHandler<String> res = new BasicResponseHandler();
            //он у нас будет посылать post запрос
            HttpPost postMethod = new HttpPost(param[0]);
            //будем передавать два параметра
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            //передаем параметры из наших текстбоксов
            //лоигн
            nameValuePairs.add(new BasicNameValuePair("login", login));
            //пароль
            nameValuePairs.add(new BasicNameValuePair("pass", pass));
            //собераем их вместе и посылаем на сервер
            postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //получаем ответ от сервера
            String response = hc.execute(postMethod, res);
            //посылаем на вторую активность полученные параметры
            //Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            //то что куда мы будем передавать и что, putExtra(куда, что);
            //intent.putExtra(SecondActivity.JsonURL, response);
            //startActivity(intent);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        dialog.dismiss();
        super.onPostExecute(result);
    }

    @Override
    protected void onPreExecute() {

        //dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Загружаюсь...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.show();
        super.onPreExecute();
    }
}
