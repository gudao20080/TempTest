package com.example.test2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.example.test2.view.BlankFragment;
import com.example.test2.view.DisableScrollViewPager;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private DisableScrollViewPager pager;
    private static final String[] COUNTRIES = new String[]{
        "Belgium", "France", "Italy", "Germany", "Spain",
        "abc", "abcd",
        "abcde",
        "abcdef",
        "abcdefg",
        "hij",
        "hijk",
        "hijkl",
        "hijklm",
        "hijklmn"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (DisableScrollViewPager) findViewById(R.id.viewPager);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout
//            .simple_list_item_1, COUNTRIES);
        pager.setCanScroll(false);
//        pager.setAdapter(new MyPagerAdapter(Arrays.asList(COUNTRIES)));
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fragments.add(BlankFragment.getInstance("" + i));
        }

        FragmentManager fm = getSupportFragmentManager();
        MyFragmentAdapter adapter = new MyFragmentAdapter(fm, fragments);
        pager.setAdapter(adapter);

        findViewById(R.id.btn_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(0);
            }
        });
        findViewById(R.id.btn_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1);
            }
        });
        findViewById(R.id.btn_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(2);
            }
        });
    }
}
