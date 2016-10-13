package com.server18.relaxationapp.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.server18.relaxationapp.R;

public class HomeActivity extends AppCompatActivity {
    private ImageView[] dots;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       showAnimationImage();
    }

    public void showAnimationImage(){

        ImageView imageView = (ImageView)findViewById(R.id.iv_homeimge);
        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.image5),2000);
        animation.addFrame(getResources().getDrawable(R.drawable.image6), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.image7), 2000);

        animation.setOneShot(false);
        imageView.setBackgroundDrawable(animation);

        // start the animation!
        animation.start();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setUiPageViewController();
            }
        },1000);

        final Animation a = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        a.reset();
    }




    private void setUiPageViewController() {
        Integer[] mImageIds ;
        LinearLayout   pager_indicator = (LinearLayout)findViewById(R.id.viewPagerCountDots);
       // dotsCount = mAdapter.getCount();
        dots = new ImageView[3];

        for (int i = 0; i < 3; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i],params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
