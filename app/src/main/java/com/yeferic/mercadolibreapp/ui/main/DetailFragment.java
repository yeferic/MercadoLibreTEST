package com.yeferic.mercadolibreapp.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yeferic.mercadolibreapp.R;
import com.yeferic.mercadolibreapp.adapters.AttributeAdapter;
import com.yeferic.mercadolibreapp.adapters.ItemsAdapter;
import com.yeferic.mercadolibreapp.adapters.PictureAdapter;
import com.yeferic.mercadolibreapp.databinding.DetailsFragmentBinding;
import com.yeferic.mercadolibreapp.intarfaces.IDataServices;
import com.yeferic.mercadolibreapp.model.Paging;
import com.yeferic.mercadolibreapp.model.ProductDetail;
import com.yeferic.mercadolibreapp.model.QueryResult;
import com.yeferic.mercadolibreapp.repo.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass..
 */
public class DetailFragment extends Fragment {

    private MainViewModel mViewModel;
    private DetailsFragmentBinding binding;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    public DetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.details_fragment, container, false);
        binding.setLifecycleOwner(this);
        createViewModelHandlers();
        createWidgetHandlers();
        getProductDetail();
        return binding.getRoot();
    }

    private void createViewModelHandlers() {
        final Observer<ProductDetail> productDetailObserver = detail -> {
            binding.tvConditionDF.setText((detail.getCondition().equalsIgnoreCase("new") ? "Nuevo" : "Usado") + " | " + String.valueOf(detail.getSoldQuantity()) + " vendidos");
            binding.tvTitleDF.setText(detail.getTitle());

            PictureAdapter adpPictures = new PictureAdapter(detail.getPictures());
            binding.lsPicturesDF.setAdapter(adpPictures);

            binding.tvPriceDF.setText("$" + String.valueOf(detail.getPrice()));
            binding.tvWarrantyDF.setText(detail.getWarranty());

            AttributeAdapter adpAttributes = new AttributeAdapter(detail.getAttributes());
            binding.lsAttributesDF.setAdapter(adpAttributes);

            binding.pbLoadDetailDF.setVisibility(View.GONE);
            binding.lyDetailDF.setVisibility(View.VISIBLE);
        };

        mViewModel.getProductDetail().observe(getViewLifecycleOwner(), productDetailObserver);
    }

    private void createWidgetHandlers() {

        RecyclerView.LayoutManager lmPictures = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.lsPicturesDF.setLayoutManager(lmPictures);
        binding.lsPicturesDF.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.lsAttributesDF.setLayoutManager(layoutManager);
        binding.lsAttributesDF.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.lsAttributesDF.setHasFixedSize(true);
    }

    private void getProductDetail(){
        binding.lyDetailDF.setVisibility(View.GONE);
        binding.pbLoadDetailDF.setVisibility(View.VISIBLE);

        IDataServices service = RetrofitClient.getRetrofitInstance().create(IDataServices.class);
        final Call<ProductDetail> productDetailCall = service.getProductDetail(mViewModel.getIdItemSelected());
        productDetailCall.enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {
                mViewModel.setProductDetail(response.body());
                binding.pbLoadDetailDF.setVisibility(View.GONE);
                binding.lyDetailDF.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ProductDetail> call, Throwable t) {
                call.cancel();
                binding.pbLoadDetailDF.setVisibility(View.GONE);
                binding.lyDetailDF.setVisibility(View.GONE);
                Toast.makeText(getContext(), "No fue posible obtener información del producto, inténtalo de nuevo.", Toast.LENGTH_LONG).show();
            }
        });
    }
}