package com.spurs.datastorageinternal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    EditText edit;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit=(EditText)findViewById(R.id.edit);
        text=(TextView)findViewById(R.id.text);
    }

    //데이터 저장
    public void clickSave(View v){
        String data=edit.getText().toString();
        edit.setText("");

        //이 엑티비티 클래스가 이미 내부메모리에 File을 저장할 수 있도록
        //Stream을 여는(open)할 수 있는 기능(Method)가 존재
        try {
            FileOutputStream fos = openFileOutput("data.txt", MODE_APPEND);
            PrintWriter writer = new PrintWriter(fos);

            writer.println(data);
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //데이터 읽어오기
    public void clickLoad(View v){

        try {
            FileInputStream fis = openFileInput("data.txt");

            InputStreamReader isr=new InputStreamReader(fis);//문자열단위로 바꺼줌

            BufferedReader reader=new BufferedReader(isr);//줄바꿈 문자를 만나면 바꺼줌

            StringBuffer buffer=new StringBuffer();

            String line=reader.readLine(); //한줄 한줄
            while (line!=null){
                buffer.append(line+"\n");
                line=reader.readLine();
            }

            text.setText(buffer.toString()
            );
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
