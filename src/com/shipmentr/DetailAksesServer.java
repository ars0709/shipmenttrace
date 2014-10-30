package com.shipmentr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailAksesServer  extends Activity {
	
	private static final String AR_awb = "awb";
	JSONArray shipment = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        Intent in = getIntent();
        String kode = in.getStringExtra(AR_awb);
    	String link_url = "http://www.indyralogistics.com/detail-shipment.php?kode="+kode;
        
        JSONParser jParser = new JSONParser();

		JSONObject json = jParser.AmbilJson(link_url);

		try {
			shipment = json.getJSONArray("tbawb");
			
			for(int i = 0; i < shipment.length(); i++){
				JSONObject ar = shipment.getJSONObject(i);
				

		        TextView judul = (TextView) findViewById(R.id.judul);
		        TextView detail = (TextView) findViewById(R.id.detail);
		        TextView isi = (TextView) findViewById(R.id.isi);

				String judul_d = ar.getString("awb");
				String detail_d = ar.getString("customer")+" - "+ar.getString("origin")+" - "+ar.getString("destination");
				String isi_d = ar.getString("customer");

		        judul.setText(judul_d);
		        detail.setText(detail_d);
		        isi.setText(isi_d);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
    }
}
