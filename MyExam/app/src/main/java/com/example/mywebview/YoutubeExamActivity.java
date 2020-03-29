package com.example.mywebview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeExamActivity extends YouTubeBaseActivity {
    YouTubePlayerView playerView;
    YouTubePlayer player;
    EditText editText;
    Button playbtn;
    Button stopbtn;

    private static String API_KEY = "AIzaSyCNU7ip85SAhFbyv0id83D-UB8kXNjKj_w";
    private static String videoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//돌아가기.
        setContentView(R.layout.activity_youtube_exam);

        Button go = findViewById(R.id.gogo);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        initPlayer();
        playbtn = findViewById(R.id.playbtn);
        stopbtn = findViewById(R.id.stopbtn);
        editText = findViewById(R.id.editText222);




        //플레이버튼 클릭시
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });
    }

    private void playVideo() {
        //입력한 url을 받아온다.
        String VideoUrl = editText.getText().toString();
        int idx = VideoUrl.indexOf("=");

        // 뒷부분을 추출
        videoID = VideoUrl.substring(idx+1);
        Toast.makeText(this, "videoID : "+videoID, Toast.LENGTH_SHORT).show();

        if(player!= null){
            //실행중이면 멈춰라.
            if(player.isPlaying()){
                player.pause();
            }
            playbtn.setVisibility(View.GONE); // 화면에 안보이게 한다.
            stopbtn.setVisibility(View.VISIBLE); // 화면에 보이게 한다.
            player.cueVideo(videoID);

            //스탑버튼을 눌렀을 시,
            stopbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //동영상 정지 시킨다.
                    player.pause();
                    playbtn.setVisibility(View.VISIBLE);
                    stopbtn.setVisibility(View.GONE);

                }
            });
        }
    }

    private void initPlayer() {
        playerView = findViewById(R.id.playerView);

        playerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                player = youTubePlayer;
                player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {

                    }

                    //로드가 됐을 때,
                    @Override
                    public void onLoaded(String s) {
                        Log.d("PlayerView","onloaded 호출됨 : "+s);
                        player.play();
                    }

                    @Override
                    public void onAdStarted() { }

                    @Override
                    public void onVideoStarted() { }

                    @Override
                    public void onVideoEnded() { }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {

                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

}
