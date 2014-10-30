/**
 * 
 */
/**
 * @author Aris
 *
 */
package com.shipmentr;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AksesServerActivity extends ListActivity {

	private static String link_url = "http://www.indyralogistics.com/detail-json.php";
	
	private static final String AR_awb = "awb";
	private static final String AR_cust = "customer";
	private static final String AR_origin = "origin";

	JSONArray artikel = null;
	ArrayList<HashMap<String, String>> daftar_shipment = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		

		JSONParser jParser = new JSONParser();

		JSONObject json = jParser.AmbilJson(link_url);

		try {
			artikel = json.getJSONArray("tbawb");
			
			for(int i = 0; i < artikel.length(); i++){
				JSONObject ar = artikel.getJSONObject(i);
				
				String id = ar.getString(AR_awb);
				String cust = ar.getString(AR_cust);
				String ori = ar.getString(AR_origin);
				
				HashMap<String, String> map = new HashMap<String, String>();

				map.put(AR_awb, id);
				map.put(AR_cust, cust);
				map.put(AR_origin, ori);

				daftar_shipment.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.adapter_listview();
	}
	
	public void adapter_listview() {


		ListAdapter adapter = new SimpleAdapter(this, daftar_shipment,
				R.layout.list_item,
				new String[] {  AR_cust, AR_origin,AR_awb }, new int[] {
						R.id.judul, R.id.content, R.id.kode});

		setListAdapter(adapter);
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				String kode = ((TextView) view.findViewById(R.id.kode)).getText().toString();
				
				Intent in = new Intent(AksesServerActivity.this, DetailAksesServer.class);
				in.putExtra(AR_awb, kode);
				startActivity(in);

			}
		});


		
	}
}
