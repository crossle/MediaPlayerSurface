package me.crossle.demo.surfacetexture;

import android.app.Activity;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.widget.LinearLayout;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLSurfaceViewActivity extends Activity {
	
	private static final String TAG = "GLSurfaceView";
	
	private GLSurfaceView mSurfaceView;
	private MediaPlayer mMediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout linearLayout = new LinearLayout(this);
		mSurfaceView = new GLSurfaceView(this);
		linearLayout.addView(mSurfaceView, 600, 400);
		setContentView(linearLayout);

		mSurfaceView.setRenderer(new Renderer() {

			@Override
			public void onSurfaceCreated(GL10 gl, EGLConfig config) {
				Log.e(TAG, "onSurfaceCreated");
			}

			@Override
			public void onSurfaceChanged(GL10 gl, int width, int height) {
				Log.e(TAG, "onSurfaceChanged");
			}

			@Override
			public void onDrawFrame(GL10 gl) {
				Log.e(TAG, "onDrawFrame");
			}
		});

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
