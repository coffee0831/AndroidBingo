package com.example.mybingo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     private int maxNumber=75;
     private ArrayList<String> histroy=new ArrayList<>();
     private EditText maxNumberEditText;
     private Button registerMaxNumberButton;
     private Button nextNumberButton;
     private TextView currentNumberTextView;
     private TextView historyTextview;
     private Button restButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maxNumberEditText=findViewById(R.id.max_number);
        registerMaxNumberButton=findViewById(R.id.register_max_number);
        nextNumberButton =findViewById(R.id.next_number);
        currentNumberTextView =findViewById(R.id.current_number);
        historyTextview=findViewById(R.id.history);
        restButton=findViewById(R.id.reset_button);
        Log.d("MainActivity","maxNumber:"+maxNumber);
        maxNumberEditText.setText(""+maxNumber);
     registerMaxNumberButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String maxNumberString=maxNumberEditText.getText().toString();
             maxNumber=Integer.valueOf(maxNumberString);
             Log.d("MainActivity","maxNumber:"+maxNumber);

         }
     });
    //表示中の数字を更新する
        nextNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNextNumber();
            }
        });
    }
    //nextNumberButtonがタップされた時の処理
    private void onClickNextNumber(){
        Log.d("MainActivity" ,"onClickNextNumber");
        //maxNumberを考慮したランダムな数値
        int nextNumber =createRandomNumber();
       //重複していた数値だった場合は、数値の生成をやり直す
        while(histroy.contains(""+nextNumber)){
            Log.d("MainActivity","重複したので再生成");
            nextNumber=nextNumber =createRandomNumber();
        }

        //nextNumberを文字列に変換する
        String nextnNumberStr=""+nextNumber;
       //nextNumberを画面に表示する
        currentNumberTextView.setText(nextnNumberStr);
        //履歴を残す
        histroy.add(nextnNumberStr);
        Log.d("MainActivity",histroy.toString());
        //履歴を表示する
        historyTextview.setText(histroy.toString());
    }

    //リセットボタンが押されたときの処理
    private void onClickReset(){

    }

    //maxNumberを考慮した数値を生成する。
    private int createRandomNumber() {
        //0.0~74.0の数値を生成する
        double randomNumber =Math.random()*(maxNumber-1);
        //1~75のの整数値を生成する
        return (int) randomNumber +1 ;
    }
}