package com.wiggins.numberprogressbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.wiggins.numberprogressbar.listener.OnProgressBarListener;
import com.wiggins.numberprogressbar.widget.NumberProgressBar;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description 自定义数字进度条：ProgressBar
 * @Author 一花一世界
 * @Time 2017/1/12 14:57
 */

public class MainActivity extends Activity implements OnProgressBarListener {

    private MainActivity mActivity = null;
    private Timer timer;
    private NumberProgressBar npb; //进度条组件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;

        initView();
        setListener();
        setProgress();
    }

    private void initView() {
        npb = (NumberProgressBar) findViewById(R.id.numberProgressBar);
    }

    private void setListener() {
        npb.setOnProgressBarListener(this);
    }

    /**
     * @Description 创建一个定时任务，模拟进度更新
     */
    private void setProgress() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //递增progress数值
                        npb.incrementProgressBy(1);
                    }
                });
            }
        }, 1000, 100);
    }

    /**
     * @Description 实现对进度实时监听，max默认为100
     */
    @Override
    public void onProgressChange(int current, int max) {
        if (current == max) {
            showToast(getResources().getString(R.string.finish));
            npb.setProgress(0);
        }
    }

    /**
     * @Description 吐司
     */
    private void showToast(String str) {
        Toast.makeText(mActivity, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * @Description 取消定时任务
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
