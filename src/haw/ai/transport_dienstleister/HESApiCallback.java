package haw.ai.transport_dienstleister;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HESApiCallback<T> implements Callback<T> {

	public void failure(RetrofitError arg0) {}

	public void success(T arg0, Response arg1) {}

}
