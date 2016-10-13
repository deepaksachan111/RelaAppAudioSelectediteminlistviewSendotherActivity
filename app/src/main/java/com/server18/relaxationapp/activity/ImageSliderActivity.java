package com.server18.relaxationapp.activity;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.server18.relaxationapp.R;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ImageSliderActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    private SliderLayout mDemoSlider;
    private RelativeLayout notificationCount1;

    private LinearLayout li_cenet_draw_image;


    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        showslide();

        ImageView imageView =(ImageView)findViewById(R.id.back_home_bottom_icon);



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"home",Toast.LENGTH_LONG).show();
            }
        });


    }


    private void showslide(){

        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String,Integer> file_maps = new LinkedHashMap<>();
        file_maps.put("one",R.drawable.image5);
        file_maps.put("two",R.drawable.image6);
        file_maps.put("three", R.drawable.image7);
        file_maps.put("four", R.drawable.nature1);
        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);



      //  Menu menuNav = getMenu();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_slider, menu);

        /*MenuItem myMenuItem = menu.findItem(R.id.notifiction);
        String before = myMenuItem.getTitle().toString();

        String counter = Integer.toString(5);
        String s = before + " "+counter+" ";
        SpannableString sColored = new SpannableString( s );

        sColored.setSpan(new BackgroundColorSpan(Color.RED), s.length() - 3, s.length(), 30);
        sColored.setSpan(new ForegroundColorSpan(Color.WHITE), s.length() - 3, s.length(), 20);


        myMenuItem.setTitle(sColored);*/


        MenuItem item1 = menu.findItem(R.id.notifiction);
        MenuItemCompat.setActionView(item1, R.layout.notification_update_count_layout);
        notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        Button notification =(Button)notificationCount1.findViewById(R.id.button1);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Notification", Toast.LENGTH_LONG).show();
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.notifiction) {
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {

    }



}
