package me.crossle.demo.surfacetexture;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.LinearLayout;

public class SurfaceViewActivity extends Activity {

	private SurfaceView mSurfaceView;
	private MediaPlayer mMediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout linearLayout = new LinearLayout(this);
		mSurfaceView = new SurfaceView(this);
		linearLayout.addView(mSurfaceView, 600, 400);
		setContentView(linearLayout);
		mSurfaceView.getHolder().addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				mMediaPlayer.release();
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				playback();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void playback() {
		mMediaPlayer = new MediaPlayer();
		try {
			mMediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getPath() + "/Movies/002-user-authentication.mp4");
			mMediaPlayer.setDisplay(mSurfaceView.getHolder());
			mMediaPlayer.prepare();
			mMediaPlayer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
