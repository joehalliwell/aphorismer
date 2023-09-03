package com.joehalliwell.aphorismer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private AphorismGenerator generator = null;
    private ViewPager viewPager = null;

    static String K_SEED = "seed";
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialise our generator
        long seed = System.currentTimeMillis();
        if (savedInstanceState != null) {
            seed = savedInstanceState.getLong(K_SEED);
        }
        generator = new AphorismGenerator(seed);

        // Construct the display
        setContentView(R.layout.main_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.aphorism_pager);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                String aphorism = generator.getAphorism(i);
                AphorismFragment fragment = AphorismFragment.newInstance(aphorism);
                return fragment;
            }

            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

        };
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);

        // Set up ShareActionProvider's default share intent
        MenuItem shareItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        shareActionProvider.setShareIntent(getShareIntent());
        return true;
    }

    private Intent getShareIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, generator.getAphorism(viewPager.getCurrentItem()) + "\n — @aphorismer");
        return intent;
    }

    private Intent getMarketIntent() {
        String appPackageName= getPackageName();
        Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
        marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET | Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        return marketIntent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                shareActionProvider.setShareIntent(getShareIntent());
                return true;
            case R.id.action_feedback:
                startActivity(getMarketIntent());
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong(K_SEED, generator.getSeed());
    }

}
