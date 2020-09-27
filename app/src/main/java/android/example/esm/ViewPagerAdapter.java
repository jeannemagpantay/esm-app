package android.example.esm;
import android.example.esm.homemodule.HomeFragment;
import android.example.esm.researchmodule.ResearchFragment;
import android.example.esm.settingsmodule.SettingsFragment;
import android.example.esm.therapymodule.TherapyFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;



public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] childFragments;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        childFragments = new Fragment[]{
                new HomeFragment(), //0
                new ResearchFragment(),
                new TherapyFragment(),
                new SettingsFragment()
        };
    }

    @Override
    public Fragment getItem(int i) {

        return childFragments[i];
    }

    @Override
    public int getCount() {
        return childFragments.length;
    }



/*    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Home";
        } else if (position == 1) {
            return "Research";
        } else if (position == 2) {
            return "Therapy";
        } else if (position == 3){
            return "Profile";
        } else {
            return "Settings";
        }
    }*/
}