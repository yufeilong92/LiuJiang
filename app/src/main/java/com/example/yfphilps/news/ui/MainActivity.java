package com.example.yfphilps.news.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.yfphilps.news.R;
import com.example.yfphilps.news.service.BaseJsonService;
import com.example.yfphilps.news.service.InfoService;
import com.example.yfphilps.news.vo.ChannelVO;
import com.example.yfphilps.news.vo.PostVO;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void initDataContent() {
        initView();

    }

    @Override
    protected void initView() {
        InfoService service = new InfoService(this);
        ArrayList<ChannelVO> vos = ChannelVO.getChannelsWithFather(getApplicationSet().getChannelVO(), null);

        service.getJsonNewsList(null, "新闻中心",
                null, null, null, new BaseJsonService.IResultInfoListener() {
                    @Override
                    public void onComplete(ArrayList<Object> values, String content) {

                        ArrayList<PostVO> list= (ArrayList<PostVO>) values.get(0);
                        for (int i = 0; i < list.size(); i++) {
                            PostVO postVO = list.get(i);

                            String title = postVO.getTitle();
                            Log.d("===", "onComplete: "+title);

                        }

                    }

                    @Override
                    public void onError(String msg, String content) {
                        Log.d("===", "onError: "+msg);
                    }
                });

    }
}
