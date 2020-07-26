package com.example.doldol4;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class button12_1_1Activity extends AppCompatActivity {

    private ImageButton[] image1;
    private ImageButton[] image2;
    private ImageView[] image3;//
    private TextView[] image4;
    private Button[] image5;
    private Button[] image6;
    private ImageButton[] image7;//
    ImageButton showDialog1;
    ImageButton ima1;//열린 세탁기
    ImageButton ima12;//닫힌 세탁기
    ImageView ima14;//열기
    ImageView a1;
    TextView ima13;//타이머 글씨
    Button ima15;//사용중 버튼
    Button ima16;//사용가능 버튼
    ImageButton ima17;//다려진 옷

    public static final long START_TIME_IN_MILLIS = 2700000;
    public static final long START_TIME_INTEVER = 1000;

    public TextView mTextViewCountDown1;

    public boolean mTimerRunning;

    public CountDownTimer mCountDownTimer1;

    public long mTimeLeftInMillis1 = START_TIME_IN_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button12_1_1);

        final List<String> list = new ArrayList<String>();

        mTextViewCountDown1 = findViewById(R.id.textView3_7_1_1);//첫번째 세탁기 타이머 글씨

        init();
    }

    private void init() {

        ima1 = (ImageButton) findViewById(R.id.button18_1);//열린 세탁기

        ima12 = (ImageButton) findViewById(R.id.button19_7_1_1);//닫힌 세탁기

        ima13 = (TextView) findViewById(R.id.textView3_7_1_1);//타이머 글씨

        ima14 = (ImageView) findViewById(R.id.imageView27);//열기

        ima15 = (Button) findViewById(R.id.button_7_1_1);//사용중 버튼

        ima16 = (Button) findViewById(R.id.button);//사용가능 버튼

        ima17 = (ImageButton) findViewById(R.id.button41);//다려진 옷

        image1 = new ImageButton[]{ima1};//열린 세탁기
        image2 = new ImageButton[]{ima12};//닫힌 세탁기
        image3 = new ImageView[]{ima14};//열기
        image4 = new TextView[]{ima13};//타이머 글씨
        image5 = new Button[]{ima15};//사용중 버튼
        image6 = new Button[]{ima16};//사용가능 버튼
        image7 = new ImageButton[]{ima17};//다려진 옷

        ima1.setVisibility(View.VISIBLE);//열린 세탁기

        ima16.setVisibility(View.VISIBLE);//사용가능 버튼

        ima12.setVisibility(View.INVISIBLE);//닫힌 세탁기

        ima13.setVisibility(View.INVISIBLE);//타이머 글씨

        ima14.setVisibility(View.INVISIBLE);//열기

        ima15.setVisibility(View.INVISIBLE);//사용중 버튼

        ima17.setVisibility(View.INVISIBLE);//다려진 옷

    }

    public void startTimer1() {


        a1 = (ImageView) findViewById(R.id.imageView27);
        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation1);
        a1.setAnimation(animation);


        mCountDownTimer1 = new CountDownTimer(START_TIME_IN_MILLIS, START_TIME_INTEVER) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis1 = millisUntilFinished;
                updateCountDownText1();

            }


            @Override
            public void onFinish() {
                a1.clearAnimation();
                animation.cancel();

                mTimerRunning = false;
                ima17.setVisibility(View.VISIBLE);//다려진 옷
                ima15.setVisibility(View.VISIBLE);//사용중 버튼
                ima12.setVisibility(View.VISIBLE);//닫힌 세탁기

                ima14.setVisibility(View.INVISIBLE);//열기
                ima13.setVisibility(View.INVISIBLE);//타이머 글씨

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    NotificationChannel notificationChannel = new NotificationChannel("alarm", "알람 테스트", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationChannel.setDescription("알람테스트");
                    notificationManager.createNotificationChannel(notificationChannel);


                    Intent intent1 = new Intent(button12_1_1Activity.this.getApplicationContext(), button12_1_1Activity.class);

                    Notification.Builder builder = new Notification.Builder(button12_1_1Activity.this.getApplicationContext(), "alarm");
                    intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    PendingIntent pendingnotification = PendingIntent.getActivity(button12_1_1Activity.this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

                    builder.setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.intro)).setSmallIcon(R.drawable.white).setNumber(1).setContentText("건조가 완료되었습니다.")
                            .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingnotification).setAutoCancel(true).setOngoing(true);
                    notificationManager.notify(1, builder.build());

                }
                findViewById(R.id.button41).setOnClickListener(new View.OnClickListener() { // 깨끗한 옷 클릭

                    @Override
                    public void onClick(View v) {
                        ima17.setVisibility(View.INVISIBLE);
                        ima15.setVisibility(View.INVISIBLE);
                        ima12.setVisibility(View.INVISIBLE);

                        ima1.setVisibility(View.VISIBLE);
                        ima16.setVisibility(View.VISIBLE);
                    }
                });


            }


        }.start();

        mTimerRunning = true;

    }

    public void updateCountDownText1() {
        int minutes = (int) (mTimeLeftInMillis1 / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis1 / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown1.setText(timeLeftFormatted);

    }

    public void myclick(View v) {
        for (int i = 0; i < image1.length; i++) {
            switch (v.getId()) {
                case R.id.button18_1:
                    showDialog1 = (ImageButton) findViewById(R.id.button18_1);

                    showDialog1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            final String[] items = new String[]{"의류 건조 500원", "이불 건조 1000원"};
                            final boolean checked[] = {false, false};
                            final int[] price = new int[]{500, 1000};

                            AlertDialog.Builder ad1 = new AlertDialog.Builder(button12_1_1Activity.this);
                            ad1.setTitle("원하시는 기능을 선택하세요.");
                            ad1.setMultiChoiceItems(items, checked, new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                    checked[which] = isChecked;

                                }
                            });
                            ad1.setPositiveButton("시작", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String str = "";
                                    int total = 1000;
                                    for (int i = 0; i < checked.length; i++) {
                                        if (checked[i] == true)
                                            total += price[i];

                                        image2[0].setVisibility(View.VISIBLE);
                                        image3[0].setVisibility(View.VISIBLE);
                                        image4[0].setVisibility(View.VISIBLE);
                                        image5[0].setVisibility(View.VISIBLE);

                                        image1[0].setVisibility(View.INVISIBLE);
                                        image6[0].setVisibility(View.INVISIBLE);
                                        image7[0].setVisibility(View.INVISIBLE);
                                    }

                                    dialog.dismiss();

                                    startTimer1();

                                }
                            });
                            ad1.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(button12_1_1Activity.this, "취소 버튼을 눌렀습니다.", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            });
                            ad1.setNegativeButton("결제금액 보기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String str = "";
                                    int total = 1000;
                                    for (int i = 0; i < checked.length; i++) {
                                        if (checked[i] == true)
                                            total += price[i];
                                    }
                                    str += "결제 금액은" + total + "입니다.";
                                    Toast.makeText(button12_1_1Activity.this, str, Toast.LENGTH_SHORT).show();

                                }
                            });
                            final AlertDialog dialog = ad1.create();

                            dialog.show();
                            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    Boolean wanttoclose = false;
                                    if (wanttoclose)
                                        dialog.dismiss();
                                    String str = "";
                                    int total = 1000;
                                    for (int i = 0; i < checked.length; i++) {
                                        if (checked[i] == true)
                                            total += price[i];
                                    }
                                    str += "결제 금액은" + total + "입니다.";
                                    Toast.makeText(button12_1_1Activity.this, str, Toast.LENGTH_SHORT).show();

                                }

                            });

                        }
                    });
                    break;

            }
        }
    }


}
