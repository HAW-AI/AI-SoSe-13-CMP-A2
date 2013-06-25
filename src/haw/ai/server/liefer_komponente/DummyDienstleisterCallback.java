package haw.ai.server.liefer_komponente;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DummyDienstleisterCallback<T> implements Callback<T> {

	public void failure(RetrofitError arg0) {}
	
	public void success(T arg0, Response arg1) {}
}
