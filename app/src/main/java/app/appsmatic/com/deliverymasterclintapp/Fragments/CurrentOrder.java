package app.appsmatic.com.deliverymasterclintapp.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import app.appsmatic.com.deliverymasterclintapp.API.Models.ResCurrentOrders;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.ClintAppApi;
import app.appsmatic.com.deliverymasterclintapp.API.RetrofitUtilities.Genrator;
import app.appsmatic.com.deliverymasterclintapp.Adabters.CurrentOrdersAdb;
import app.appsmatic.com.deliverymasterclintapp.R;
import app.appsmatic.com.deliverymasterclintapp.SharedPrefs.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentOrder extends Fragment {

    private RecyclerView currentlist;
    private TextView emptyFlag;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_order, container, false);




    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emptyFlag=(TextView)view.findViewById(R.id.current_orders_empty_flag);
        emptyFlag.setVisibility(View.INVISIBLE);

        //Loading Dialog
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext(),R.style.AppCompatAlertDialogStyle);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setIcon(R.drawable.loadicon);
        mProgressDialog.setTitle(R.string.loadingdialog);
        mProgressDialog.setMessage(Html.fromHtml("<font color=#FFFFFF><big>Loading ...</big></font>"));
        mProgressDialog.show();


        HashMap data=new HashMap();
        data.put("owner",SaveSharedPreference.getOwnerId(getContext()));

        Genrator.createService(ClintAppApi.class).getCurrentOrders(data).enqueue(new Callback<ResCurrentOrders>() {
            @Override
            public void onResponse(Call<ResCurrentOrders> call, Response<ResCurrentOrders> response) {

                if (response.isSuccessful()) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    if (response.body().getCode() != 0) {

                        if (response.body().getMessage().isEmpty()) {
                            emptyFlag.setVisibility(View.VISIBLE);
                        } else {
                            emptyFlag.setVisibility(View.INVISIBLE);
                            currentlist = (RecyclerView) getView().findViewById(R.id.fragment_cerunt_orders_list);
                            currentlist.setAdapter(new CurrentOrdersAdb(getContext(), response.body()));
                            currentlist.setLayoutManager(new LinearLayoutManager(getContext()));
                        }


                    } else {
                        Toast.makeText(getContext(), " user orders 0 erorr ", Toast.LENGTH_LONG).show();
                    }

                } else {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    Toast.makeText(getContext(), "Response Not Success from user orders !", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResCurrentOrders> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Toast.makeText(getContext(), " user orders : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


















        currentlist=(RecyclerView)view.findViewById(R.id.fragment_cerunt_orders_list);




    }
}
