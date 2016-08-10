package info.manik.volleyexamples;

import info.manik.volleyexamples.adapter.UserInfoAdapter;
import info.manik.volleyexamples.app.AppController;
import info.manik.volleyexamples.model.ItemObjects;
import info.manik.volleyexamples.model.UserInfo;
import info.manik.volleyexamples.utils.Const;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonRequestActivity extends Activity  {

	private String TAG = JsonRequestActivity.class.getSimpleName();
	//private Button btnJsonObj, btnJsonArray;
//	private TextView msgResponse;
	private ProgressDialog pDialog;
	List<ItemObjects> listViewItems = new ArrayList<ItemObjects>();

	// These tags will be used to cancel the requests
	private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
	private StaggeredGridLayoutManager gaggeredGridLayoutManager;
	private RecyclerView recyclerView;
	private List<ItemObjects> gaggeredList;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_main);
		 recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
		mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);

		recyclerView.setHasFixedSize(true);

		gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
		recyclerView.setLayoutManager(gaggeredGridLayoutManager);



		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Loading...");
		pDialog.setCancelable(false);

		/*btnJsonObj.setOnClickListener(this);
		btnJsonArray.setOnClickListener(this);*/
		makeJsonArryReq();
		mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Refresh items
				if(listViewItems.size()>0){
					listViewItems.clear();
				}
				makeJsonArryReq();
				mSwipeRefreshLayout.setRefreshing(false);



			}
		});


	}

	private void showProgressDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hideProgressDialog() {
		if (pDialog.isShowing())
			pDialog.hide();
	}

	/**
	 * Making json object request
	 * */
	private void makeJsonObjReq() {
		showProgressDialog();
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
				Const.URL_JSON_OBJECT, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
					//	msgResponse.setText(response.toString());
						hideProgressDialog();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						System.out.println("What is the error"+error.getMessage());
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hideProgressDialog();
					}
				}) {

			/**
			 * Passing some request headers
			 * */
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json");
				return headers;
			}

			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("name", "Androidhive");
				params.put("email", "abc@androidhive.info");
				params.put("pass", "password123");

				return params;
			}

		};

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq,
				tag_json_obj);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);		
	}

	/**
	 * Making json array request
	 * */
	private void makeJsonArryReq() {
		showProgressDialog();
		JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						Gson gson = new Gson();
						String jsonOutput = response.toString();
						Type listType = new TypeToken<List<UserInfo.Post>>(){}.getType();
						List<UserInfo.Post> posts = (List<UserInfo.Post>) gson.fromJson(jsonOutput, listType);

					//	Log.d("postLog", posts.get(0).getUserDetail().getUserName());
						for (int i = 0; i < posts.size(); i++) {
							Log.d("postLog","Printed--->"+ posts.get(i).getUserDetail().getUserProfileImage().getLargePic());
							makeImageRequest(posts.get(i).getUserDetail().getUserName().toString(),posts.get(i).getUserDetail().getUserProfileImage().getLargePic().toString());
						//	msgResponse.setText(posts.get(i).getID());

						}
						Log.d(TAG, response.toString());
						hideProgressDialog();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hideProgressDialog();
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req,
				tag_json_arry);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
	}

	private List<ItemObjects> getListItemData(String userName,Bitmap bitmap){


		listViewItems.add(new ItemObjects(userName,bitmap));
		/*listViewItems.add(new ItemObjects("Ethane", R.drawable.two));
		listViewItems.add(new ItemObjects("Alkyne", R.drawable.three));
		listViewItems.add(new ItemObjects("Benzene", R.drawable.four));
		listViewItems.add(new ItemObjects("Amide", R.drawable.one));
		listViewItems.add(new ItemObjects("Amino Acid", R.drawable.two));
		listViewItems.add(new ItemObjects("Phenol", R.drawable.three));
		listViewItems.add(new ItemObjects("Carbonxylic", R.drawable.four));
		listViewItems.add(new ItemObjects("Nitril", R.drawable.one));
		listViewItems.add(new ItemObjects("Ether", R.drawable.two));
		listViewItems.add(new ItemObjects("Ester", R.drawable.three));
		listViewItems.add(new ItemObjects("Alcohol", R.drawable.four));*/

		return listViewItems;
	}
	private void makeImageRequest(final String userName, String imageUrl) {
		ImageLoader imageLoader = AppController.getInstance().getImageLoader();

		// If you are using NetworkImageView
		//imgNetWorkView.setImageUrl(Const.URL_IMAGE, imageLoader);


		// If you are using normal ImageView
		imageLoader.get(imageUrl, new ImageLoader.ImageListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, "Image Load Error: " + error.getMessage());
			}

			@Override
			public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
				if (response.getBitmap() != null) {

					 gaggeredList = getListItemData(userName,response.getBitmap());

						UserInfoAdapter rcAdapter = new UserInfoAdapter(JsonRequestActivity.this, gaggeredList);
					recyclerView.setAdapter(rcAdapter);


				}
			}
		});
	//

		// Loading image with placeholder and error image
	/*	imageLoader.get(Const.URL_IMAGE, ImageLoader.getImageListener(
				imageView, R.drawable.ico_loading, R.drawable.ico_error));*/

		Cache cache = AppController.getInstance().getRequestQueue().getCache();
		Cache.Entry entry = cache.get(Const.URL_IMAGE);
		if(entry != null){
			try {
				String data = new String(entry.data, "UTF-8");
				// handle data, like converting it to xml, json, bitmap etc.,
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else{
			// cached response doesn't exists. Make a network call here
		}

	}

}
