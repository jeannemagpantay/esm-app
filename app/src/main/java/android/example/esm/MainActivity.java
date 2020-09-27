package android.example.esm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {


    private TabLayout menuBar;
    private ViewPager viewPager;
    int tabOpen = 0;

    private int[] menuBarIconsUnselected = {
            R.drawable.home_icon,
            R.drawable.survey_icon,
            R.drawable.clinical_icon,
            R.drawable.settings_icon
    };

    private int[] menuBarIconsSelected = {
            R.drawable.home_icon_selected,
            R.drawable.survey_icon_selected,
            R.drawable.clinical_icon_selected,
            R.drawable.settings_icon_selected
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        menuBar = (TabLayout) findViewById(R.id.menu_bar);
        menuBar.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        tabOpen = intent.getIntExtra("TabNumber", 0);
        if (tabOpen == 1){
            jumpToSurveys();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupMenuBarIcons();

        menuBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                menuBar.getTabAt(position).setIcon(menuBarIconsSelected[position]);
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.menuBar);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                menuBar.getTabAt(position).setIcon(menuBarIconsUnselected[position]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                menuBar.getTabAt(position).setIcon(menuBarIconsSelected[position]);
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.menuBar);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);


            }
        });

        //showLoadIndicator(false);
    }

    private void setupMenuBarIcons() {

        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.menuBar);

        menuBar.getTabAt(0).setIcon(menuBarIconsUnselected[0]);
        menuBar.getTabAt(1).setIcon(menuBarIconsUnselected[1]);
        menuBar.getTabAt(2).setIcon(menuBarIconsUnselected[2]);
        menuBar.getTabAt(3).setIcon(menuBarIconsUnselected[3]);

        if (viewPager.getCurrentItem() == 0){
            menuBar.getTabAt(0).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        } else if (viewPager.getCurrentItem() == 1){
            menuBar.getTabAt(1).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        } else if (viewPager.getCurrentItem() == 2){
            menuBar.getTabAt(2).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        } else if (viewPager.getCurrentItem() == 3){
            menuBar.getTabAt(3).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        }





    }

    public void jumpToSurveys(){
        viewPager.setCurrentItem(1);
        setupMenuBarIcons();
    }

/*    @Subscribe(threadMode = ThreadMode.MAIN.MAIN)
    public void onPromoEvent(PromoEvent event) {
        boolean newPromo = prefs.getBoolean("new_promo", true);
        if(newPromo == true) {
            menuBar.getTabAt(1).setIcon(R.drawable.tab_updates_new);
        }
        else{
            menuBar.getTabAt(1).setIcon(menuBarIconsUnselected[1]);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }*/
}
