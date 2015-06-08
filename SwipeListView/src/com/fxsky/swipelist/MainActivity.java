package com.fxsky.swipelist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.fxsky.swipelist.adapter.SwipeAdapter;
import com.fxsky.swipelist.entity.WXMessage;
import com.fxsky.swipelist.widget.SwipeListView;

public class MainActivity extends Activity {
	private List<WXMessage> data = new ArrayList<WXMessage>();
	private SwipeListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initData();
		initView();
	}

	/**
	 * 初始化数据
	 * 
	 * @author zchengjun77@163.com 2014-12-12 上午9:52:23
	 */
	private void initData() {

		for (int i = 0; i < 50; i++) {
			WXMessage msg = null;
			if (i % 3 == 0) {
				msg = new WXMessage("腾讯新闻", "人民日报刊文：习近平对评价毛泽东提6个重要观点", "早上8:44");
				msg.setIcon_id(R.drawable.qq_icon);
			} else if (i % 3 == 1) {
				msg = new WXMessage("订阅号", "CSDN：2013年国内最具技术影响力公司", "早上8:49");
				msg.setIcon_id(R.drawable.wechat_icon);
			} else {
				msg = new WXMessage("微博阅读", "美女演各款妹子跟男朋友打电话", "昨天晚上");
				msg.setIcon_id(R.drawable.qq_icon);
			}

			data.add(msg);
		}
	}

	/**
	 * 初始化界面
	 * 
	 * @author zchengjun77@163.com 2014-12-12 上午9:54:21
	 */
	private void initView() {

		mListView = (SwipeListView) findViewById(R.id.listview);
		final SwipeAdapter mAdapter = new SwipeAdapter(this, data,
				mListView.getRightViewWidth());

		mAdapter.setOnRightItemClickListener(new SwipeAdapter.onRightItemClickListener() {

			@Override
			public void onRightItemClick(View v, int position) {
				//移除
				data.remove(position);
				//mAdapter.notifyDataSetChanged();
				mListView.setAdapter(mAdapter);//如果不加这一句的话,删除之后右边还是会出现,不会自动回弹,所以重新设置一下
				/*Toast.makeText(MainActivity.this,
						"删除第  " + (position + 1) + " 对话记录", Toast.LENGTH_SHORT)
						.show();*/
			}
		});

		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mListView.setItemsCanFocus(true);
				Toast.makeText(MainActivity.this, "item onclick " + position,
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
