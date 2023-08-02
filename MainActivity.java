package com.example.textview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

//문의 코드
public class MainActivity extends AppCompatActivity {
    private TextToSpeech tts;
    EditText ed_id, ed_category, ed_content;
    Button btn_test1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });

        Button Button_req = (Button) findViewById(R.id.Button_req);
        Button_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭했을 때 다른 화면으로 전환
                String text = "문의 버튼을 클릭하셨습니다. 이동합니다.";
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                Intent intent = new Intent(getApplicationContext(), RequestMain.class);
                startActivity(intent);
            }
        });
        Button Button_loc = (Button) findViewById(R.id.Button_loc);
        Button_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭했을 때 다른 화면으로 전환
                String text = "위치 전송 서비스에 클릭하셨습니다. 이동합니다.";
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                Intent intent = new Intent(getApplicationContext(), locationActivity.class);
                startActivity(intent);
            }
        });
        Button Button_todayexer = (Button) findViewById(R.id.Button_todayexer);
        Button_todayexer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭했을 때 다른 화면으로 전환
                String text = "오늘의 운동에 클릭하셨습니다. 만보기를 실행합니다.";
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                Intent intent = new Intent(getApplicationContext(), TodayExercise.class);
                startActivity(intent);
            }
        });
        Button Button_warning = (Button) findViewById(R.id.Button_warning);
        Button_warning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭했을 때 다른 화면으로 전환
                String text = "SOS 버튼을 누르셨습니다.";
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                Intent intent = new Intent(getApplicationContext(), safety.class);
                startActivity(intent);
            }
        });
    }
}
