package com.example.will.sharedpref;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    static private String SHARE_NAME = "SHARE_PREF";
    static SharedPreferences sharePref = null;
    static SharedPreferences.Editor editor = null;
    TextView tvList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvList = (TextView)findViewById(R.id.tvList);

        sharePref = getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        editor = sharePref.edit();
    }

    public void btnClick(View view) {
        if(view.getId() == R.id.btn_onclick1) {  // Buttoon의 ID를 찾아서 실행이 된다.
            saveData();
        } else if(view.getId() == R.id.btn_onclick2) {
            updateData();
        } else if(view.getId() == R.id.btn_onclick3) {
            deleteData();
        } else if(view.getId() == R.id.btn_onclick4) {
            editor.clear();
            editor.commit();
        }
        listData();
    }

    public void saveData() {
        editor.putBoolean("isShare", true);
        editor.putFloat("fRate", 1.33f);
        editor.putInt("nValue", 100);
        editor.putString("name", "copycoding");
        editor.commit();

    }

    public void updateData() {
        editor.putBoolean("isShare", false);
        editor.putFloat("fRate", 3.33f);
        editor.putInt("nValue", 5000);
        editor.putString("name", "copycoding.tistory");
        editor.commit();
    }

    public void deleteData() {
        editor.remove("nValue");
        editor.commit();
    }

    public void listData() {
        String dataList = "";
        Map<String, ?> totalValue = sharePref.getAll();
        for(Map.Entry<String, ?> entry : totalValue.entrySet()) {
            dataList += entry.getKey().toString()+ ": " + entry.getValue().toString() + "\r\n";
            Log.d("share : ", entry.getKey() + ": " + entry.getValue());
        }
        tvList.setText(dataList);
        // 하나 출력하기, 위에서 삭제시 오류 발생
//        Log.d("share int: ", totalValue.get("nValue").toString());
    }

}
