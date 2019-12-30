package android.example.esm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {


    private TabLayout menuBar;
    private ViewPager viewPager;

    private int[] menuBarIconsUnselected = {
            R.drawable.ic_list_light,
            R.drawable.ic_account_light,
            R.drawable.ic_settings_light
    };

    private int[] menuBarIconsSelected = {
            R.drawable.ic_list_dark,
            R.drawable.ic_account_dark,
            R.drawable.ic_settings_dark
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        menuBar = (TabLayout) findViewById(R.id.menu_bar);
        menuBar.setupWithViewPager(viewPager);

        // Initialize Menu Bar Icons

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
            }
        });

        //showLoadIndicator(false);
    }

    private void setupMenuBarIcons() {
        menuBar.getTabAt(0).setIcon(menuBarIconsSelected[0]);
        menuBar.getTabAt(1).setIcon(menuBarIconsUnselected[1]);
        menuBar.getTabAt(2).setIcon(menuBarIconsUnselected[2]);
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
