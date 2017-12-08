package com.personage.kevin.mubypass.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.personage.kevin.mubypass.Fragment.classify.ArmorFragment;
import com.personage.kevin.mubypass.Fragment.classify.EmboitementFragment;
import com.personage.kevin.mubypass.Fragment.classify.SkillFragment;
import com.personage.kevin.mubypass.Fragment.classify.WeaponFragment;
import com.personage.kevin.mubypass.Fragment.classify.WingFragment;
import com.personage.kevin.mubypass.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * 信息分类查询主控页
 */
public class ClassifyMainActivity extends BaseActivity {

    private PagerSlidingTabStrip pagerTab;
    private ViewPager mainVp;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_main);
        initView();
    }


    public void initView() {
        super.initView();
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(Color.BLACK);
        toolbar = (Toolbar) findViewById(R.id.classify_tool_bar);
        toolbar.setTitle("分类查询");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pagerTab = (PagerSlidingTabStrip) findViewById(R.id.vp_tab);
        pagerTab.setTextColor(Color.WHITE);
        mainVp = (ViewPager) findViewById(R.id.main_vp);
        mainVp.setAdapter(new ClassifyPagerAdapter(getSupportFragmentManager()));
        pagerTab.setViewPager(mainVp);
        //pagerTab.setOnPageChangeListener(new TabPageChangeListener());
    }

    @Override
    public void initDate() {
        super.initDate();

    }

    class ClassifyPagerAdapter extends FragmentPagerAdapter{

        String[] classifyTitle = {"防具","武器","套装","技能","翅膀"};
        ArmorFragment armorFragment;
        EmboitementFragment emboitementFragment;
        SkillFragment skillFragment;
        WingFragment wingFragment;
        WeaponFragment weaponFragment;

        public ClassifyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    armorFragment = new ArmorFragment();
                    return  armorFragment;
                case 1:
                    weaponFragment = new WeaponFragment();
                    return  weaponFragment;
                case 2:
                    emboitementFragment = new EmboitementFragment();
                    return emboitementFragment;
                case 3:
                    skillFragment = new SkillFragment();
                    return  skillFragment;
                case 4:
                    wingFragment = new WingFragment();
                    return  wingFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return classifyTitle.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return classifyTitle[position];
        }
    }

//    class TabPageChangeListener implements ViewPager.OnPageChangeListener {
//
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//
//        }
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
