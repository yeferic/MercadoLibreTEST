package com.yeferic.mercadolibreapp.ui.main;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yeferic.mercadolibreapp.R;
import com.yeferic.mercadolibreapp.adapters.ItemsAdapter;
import com.yeferic.mercadolibreapp.databinding.MainFragmentBinding;
import com.yeferic.mercadolibreapp.intarfaces.ICustomClickListener;
import com.yeferic.mercadolibreapp.intarfaces.IDataServices;
import com.yeferic.mercadolibreapp.model.Item;
import com.yeferic.mercadolibreapp.model.Paging;
import com.yeferic.mercadolibreapp.model.QueryResult;
import com.yeferic.mercadolibreapp.repo.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment implements ICustomClickListener {

    private MainViewModel mViewModel;
    private MainFragmentBinding binding;
    private final int MAX_ITEMS_PER_PAGE = 25;
    private Parcelable recyclerViewState;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        binding.setLifecycleOwner(this);
        createWidgetHandlers();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        createViewModelHandlers();
    }

    private void createViewModelHandlers() {
        final Observer<String> queryToSearhObserver = query -> {
            binding.txtSearchMF.setQuery(query, false);
        };

        mViewModel.getQueryToSearch().observe(getViewLifecycleOwner(), queryToSearhObserver);

        final Observer<QueryResult> queryResultObserver = queryResult -> {
            ItemsAdapter adpItems = new ItemsAdapter(MainFragment.this, queryResult.getProductos());
            binding.lsItemsMF.setAdapter(adpItems);
            binding.lsItemsMF.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        };

        mViewModel.getQueryResult().observe(getViewLifecycleOwner(),queryResultObserver);
    }

    private void createWidgetHandlers() {
        binding.txtSearchMF.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mViewModel.setQueryToSearch(query);
                mViewModel.setCurrentOffset(0);
                getItemsByQuery();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.lsItemsMF.setLayoutManager(layoutManager);
        binding.lsItemsMF.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.lsItemsMF.setHasFixedSize(true);

        binding.lsItemsMF.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Paging pg = mViewModel.getQueryResult().getValue().getPaging();

                if (!recyclerView.canScrollVertically(1) && (pg.getOffset() + MAX_ITEMS_PER_PAGE) < pg.getTotal()) {
                    recyclerViewState = binding.lsItemsMF.getLayoutManager().onSaveInstanceState();
                    mViewModel.setCurrentOffset(pg.getOffset() + MAX_ITEMS_PER_PAGE);
                    getItemsByQuery();
                }
            }
        });
    }

    private void getItemsByQuery() {
        binding.pbSearchMF.setVisibility(View.VISIBLE);

        IDataServices service = RetrofitClient.getRetrofitInstance().create(IDataServices.class);
        final Call<QueryResult> searchItems = service.getItems(mViewModel.getQueryToSearch().getValue(), mViewModel.getCurrentOffset(), MAX_ITEMS_PER_PAGE);
        searchItems.enqueue(new Callback<QueryResult>() {
            @Override
            public void onResponse(Call<QueryResult> call, Response<QueryResult> response) {
                if(mViewModel.getCurrentOffset() == 0){
                    mViewModel.setQueryResult(response.body());
                }else {
                    QueryResult qr = new QueryResult();
                    qr.setPaging(response.body().getPaging());
                    qr.setProductos(mViewModel.getQueryResult().getValue().getProductos());
                    qr.getProductos().addAll(response.body().getProductos());
                    mViewModel.setQueryResult(qr);
                }
                binding.pbSearchMF.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<QueryResult> call, Throwable t) {
                call.cancel();
                binding.pbSearchMF.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "No fue posible completar la búsqueda, inténtalo de nuevo.", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemClick(int p) {
        Toast.makeText(getContext(), "No fue posible completar la búsqueda, inténtalo de nuevo.", Toast.LENGTH_LONG).show();
    }
}