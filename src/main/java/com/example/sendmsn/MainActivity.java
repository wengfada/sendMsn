package com.example.sendmsn;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

	private EditText num;
	private EditText et_content;
	private Button send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		num = (EditText) findViewById(R.id.num);
		et_content = (EditText) findViewById(R.id.et_content);
		send = (Button) findViewById(R.id.send);
		send = (Button) findViewById(R.id.send);
		send = (Button) findViewById(R.id.send);
		send = (Button) findViewById(R.id.send);
		send = (Button) findViewById(R.id.send);
		send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String number = num.getText().toString();
			
				String content = et_content.getText().toString();
				if (!TextUtils.isEmpty(number) && !TextUtils.isEmpty(number)) {
					sendMsn(number, content);
					num.setText("");
					et_content.setText("");
				}else{
					Toast.makeText(getApplicationContext(), "��,���ź�������ݲ���Ϊ��", Toast.LENGTH_SHORT).show();
				}

			}



	
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private void sendMsn(String number, String content) {
		SmsManager smsManager = SmsManager.getDefault();
		ArrayList<String> messages = smsManager.divideMessage(content);
		for (String msg : messages) {
			smsManager.sendTextMessage(number, null, msg, null,null);
		}
		Toast.makeText(getApplicationContext(), "���ͳɹ�", Toast.LENGTH_SHORT).show();
		
		insertMsn(number, content);
	}
	/**
	 * ����ϵͳapi
	 * @param number
	 * @param content
	 */
	private void getSystemApiMsn(String number, String content) {
		Uri uri = Uri.parse("smsto:"+number);          
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);          
		it.putExtra("sms_body", content);          
		MainActivity.this.startActivity(it);
	}
	/**�����͵Ķ��Ų������ݿ�**/  
	private void insertMsn(String number, String content) {
		
		ContentValues values = new ContentValues();  
		//����ʱ��  
		values.put("date", System.currentTimeMillis());  
		//�Ķ�״̬  
		values.put("read", 0);  
		//1Ϊ�� 2Ϊ��  
		values.put("type", 2);  
		//�ʹ����  
		values.put("address", number);  
		//�ʹ�����  
		values.put("body", content);  
		//������ſ�  
		getContentResolver().insert(Uri.parse("content://sms"),values);
	}

}
