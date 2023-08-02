package com.example.textview;
// 문의 세부내용 들어가게 되는 코드
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class RequestMain extends AppCompatActivity {
    private EditText ed_id, ed_category, ed_content;
    private TextToSpeech tts;

    private Button btn_test1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Button_add = (Button) findViewById(R.id.Button_add);
        Button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "문의 넣으러 가기 칸을 눌렀습니다. 이동합니다.";
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                // 버튼 클릭했을 때 다른 화면으로 전환
                Intent intent = new Intent(getApplicationContext(), MainScreen.class);
                startActivity(intent);
            }
        });
    }
}
