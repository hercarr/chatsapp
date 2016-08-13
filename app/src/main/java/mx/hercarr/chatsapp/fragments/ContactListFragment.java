package mx.hercarr.chatsapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.hercarr.chatsapp.R;
import mx.hercarr.chatsapp.adapters.UserAdapter;
import mx.hercarr.chatsapp.model.User;
import mx.hercarr.chatsapp.rest.RandomUserClient;
import mx.hercarr.chatsapp.rest.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactListFragment extends Fragment {

    @BindView(R.id.srlContacts)
    SwipeRefreshLayout srlContacts;
    @BindView(R.id.rvContacts)
    RecyclerView rvContacts;

    private List<User> users;
    private UserAdapter adapter;

    public ContactListFragment() {

    }

    public static ContactListFragment newInstance() {
        ContactListFragment fragment = new ContactListFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_contact_list, menu);
        inflater.inflate(R.menu.menu_main, menu);
    }

    private void init() {
        findUsers();
        setAdapter();
        setupSwipeToRefresh();
        setupRecyclerView();
    }

    private void findUsers() {
        Call<UserResponse> call = RandomUserClient.getUserService().findUsers(100);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                users = response.body().getResults();
                adapter.reload(users);
                if (srlContacts.isRefreshing()) {
                    srlContacts.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("USER-SERVICE", t.getLocalizedMessage());
            }
        });

    }

    private void setAdapter() {
        adapter = new UserAdapter(getActivity(), users);
    }

    private void setupSwipeToRefresh() {
        srlContacts.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                findUsers();
            }
        });
        srlContacts.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.primary),
                ContextCompat.getColor(getActivity(), R.color.accent),
                ContextCompat.getColor(getActivity(), R.color.primary_dark)
        );
    }

    private void setupRecyclerView() {
        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvContacts.setItemAnimator(new DefaultItemAnimator());
        rvContacts.setAdapter(adapter);
        rvContacts.setHasFixedSize(true);
    }

}