package mx.hercarr.chatsapp.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.hercarr.chatsapp.R;
import mx.hercarr.chatsapp.adapters.MainViewPagerAdapter;
import mx.hercarr.chatsapp.fragments.CallListFragment;
import mx.hercarr.chatsapp.fragments.ChatListFragment;
import mx.hercarr.chatsapp.fragments.ContactListFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setupToolbar();
        setupViewPager();
        setupTabLayout();
    }

    private void setupToolbar() {
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }

    public void setupViewPager() {
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(CallListFragment.newInstance(), getString(R.string.tab_title_calls));
        adapter.addFragment(ChatListFragment.newInstance(), getString(R.string.tab_title_chats));
        adapter.addFragment(ContactListFragment.newInstance(), getString(R.string.tab_title_contacts));
        viewPager.setAdapter(adapter);
    }

    public void setupTabLayout() {
        tabLayout.setupWithViewPager(viewPager);
    }

}
