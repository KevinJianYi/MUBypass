package com.personage.kevin.mubypass.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.personage.kevin.mubypass.R;
import com.personage.kevin.mubypass.adapter.MyRecyclerViewAdapter;
import com.personage.kevin.mubypass.adapter.RefreshViewRecyclerAdapter;
import com.personage.kevin.mubypass.entity.GameOperator;
import com.personage.kevin.mubypass.view.DividerItemDecoration;
import com.personage.kevin.mubypass.view.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<GameOperator> gameOperators;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter recyclerViewAdapter;
    private RefreshViewRecyclerAdapter refreshViewRecyclerAdapter;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent startIntent = new Intent(this,ClassifyMainActivity.class);
        switch (id){
            case R.id.nav_swordman:
                startActivity(startIntent);
                break;
            case R.id.nav_enchanter:
                startActivity(startIntent);
                break;
            case R.id.nav_sagittary:
                startActivity(startIntent);
                break;
            case R.id.nav_m_swordman:
                startActivity(startIntent);
                break;
            case R.id.nav_mentor:
                startActivity(startIntent);
                break;
            case R.id.nav_kael:
                startActivity(startIntent);
                break;
            case R.id.nav_brawler:
                startActivity(startIntent);
                break;
            case R.id.nav_man_of_La_mancha:
                startActivity(startIntent);
                break;

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void initView() {
        super.initView();
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        recyclerView = (RecyclerView) findViewById(R.id.list_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL_LIST));
        loadPrivateServerInfo();
        recyclerViewAdapter = new MyRecyclerViewAdapter(gameOperators);
        refreshViewRecyclerAdapter = new RefreshViewRecyclerAdapter(recyclerViewAdapter);
        recyclerView.setAdapter(refreshViewRecyclerAdapter);
        createLoadMoreView();
        recyclerViewAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(WebsiteActivity.LINK_ADDRESS,"www.sx.godsmu.com");
                bundle.putString(WebsiteActivity.GAME_NAME,"海神奇迹");
                intent.putExtra(WebsiteActivity.LINK_BUNDLE,bundle);
                startActivity(intent);
            }
        });

        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rx.Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map(new Func1<Long, Object>() {
                    @Override
                    public Object call(Long aLong) {
                        fetchingNewData();
                        refreshLayout.setRefreshing(false);
                        refreshViewRecyclerAdapter.notifyDataSetChanged();
                        return null;
                    }
                }).subscribe();
            }
        });

        recyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                simulateLoadMoreData();
            }
        });
    }

    private void loadPrivateServerInfo(){
        gameOperators =new ArrayList<GameOperator>();
        for(int i=0;i<20;i++){
            GameOperator gameOperator = new GameOperator();
            gameOperator.setOperatorID(""+i);
            gameOperator.setOperatorName("海神奇迹");
            gameOperator.setOperatorIntro("SX版本！，超级变态！GM叫'大技'...");
            gameOperator.setOperatorAddressLink("www.sx.godsmu.com");
            gameOperators.add(gameOperator);
        }

    }

    private void fetchingNewData() {
        GameOperator gameOperator = new GameOperator();
        gameOperator.setOperatorID("1");
        gameOperator.setOperatorName("海神奇迹");
        gameOperator.setOperatorIntro("SX版本！，超级变态！GM叫'大技'...");
        gameOperator.setOperatorAddressLink("www.sx.godsmu.com");
        gameOperators.add(0, gameOperator);
    }

    private void loadMoreData() {
        ArrayList<GameOperator> moreList = new ArrayList<GameOperator>();
        for (int i = 0; i < 3; i++) {
            GameOperator gameOperator = new GameOperator();
            gameOperator.setOperatorID(""+i);
            gameOperator.setOperatorName("海神奇迹");
            gameOperator.setOperatorIntro("SX版本！，超级变态！GM叫'大技'...");
            gameOperator.setOperatorAddressLink("www.sx.godsmu.com");
            gameOperators.add(gameOperator);
            moreList.add(gameOperator);
        }
        gameOperators.addAll(moreList);
    }

    private void createLoadMoreView() {
        View loadMoreView = LayoutInflater
                .from(MainActivity.this)
                .inflate(R.layout.view_load_more, recyclerView, false);
        refreshViewRecyclerAdapter.addFooterView(loadMoreView);
    }

    /**
     * Rx 刷新
     */
    private void simulateLoadMoreData(){
        rx.Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map(new Func1<Long, Object>() {
                    @Override
                    public Object call(Long aLong) {
                        loadMoreData();
                        refreshViewRecyclerAdapter.notifyDataSetChanged();
                        return null;
                    }
                }).subscribe();
    }
}
