package com.google.android.apps.gddquiz;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GddquizActivity extends Activity {
	
	private IQuizService quizServiceif;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent intent = new Intent(IQuizService.class.getName());
        bindService(intent,quizServiceConn,BIND_AUTO_CREATE);        
        
        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new btnlistener());
        
    }

	@Override
	protected void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
		unbindService(quizServiceConn);
	}
	
	private class btnlistener implements OnClickListener {
		String ss;
		@Override
		public void onClick(View v) {
			// TODO 自動生成されたメソッド・スタブ
			try {
				ss = quizServiceif.getCode();
			} catch (RemoteException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		
	}
	
	private ServiceConnection quizServiceConn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO 自動生成されたメソッド・スタブ
			quizServiceif = null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO 自動生成されたメソッド・スタブ
			quizServiceif = IQuizService.Stub.asInterface(service);
		}
	};

}