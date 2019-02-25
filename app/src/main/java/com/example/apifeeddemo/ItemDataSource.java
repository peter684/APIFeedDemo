package com.example.apifeeddemo;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    public static final int PAGE_SIZE = 50;
    private static final int FIRST_PAGE = 1;
    private static final String SITE_NAME = "stackoverflow";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {
        RetrofitClient.getInstance().getAPI().getAnswers(FIRST_PAGE,PAGE_SIZE,SITE_NAME).enqueue(new Callback<StackAPIResponse>() {
            @Override
            public void onResponse(Call<StackAPIResponse> call, Response<StackAPIResponse> response) {
                if (response!= null){
                    callback.onResult(response.body().items, null,FIRST_PAGE+1);
                }
            }

            @Override
            public void onFailure(Call<StackAPIResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance().getAPI().getAnswers(params.key,PAGE_SIZE,SITE_NAME).enqueue(new Callback<StackAPIResponse>() {
            @Override
            public void onResponse(Call<StackAPIResponse> call, Response<StackAPIResponse> response) {
                Integer key =params.key>1?params.key-1:null;
                if (response.body()!= null){
                    callback.onResult(response.body().items, key);
                }
            }

            @Override
            public void onFailure(Call<StackAPIResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getAPI()
                .getAnswers(params.key,PAGE_SIZE,SITE_NAME)
                .enqueue(new Callback<StackAPIResponse>() {
            @Override
            public void onResponse(Call<StackAPIResponse> call, Response<StackAPIResponse> response) {
                Integer key =response.body().hasmore? params.key+1:null;
                if (response.body()!= null){
                    callback.onResult(response.body().items, key);
                }
            }

            @Override
            public void onFailure(Call<StackAPIResponse> call, Throwable t) {

            }
        });
    }
}
