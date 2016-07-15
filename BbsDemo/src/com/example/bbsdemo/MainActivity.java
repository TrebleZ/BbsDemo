package com.example.bbsdemo;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.BbsVpAdapter;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private ImageView imgBack;// 返回按钮
	private ImageView imgWriteBbs;// 发表博客
	private ViewPager vBbsPager;// viewpager
	private BbsVpAdapter viewpagerAdpter;// 适配器
	// 存放子项view
	private List<View> viewItems = new ArrayList<View>();
	private List<List<String>> questionList;// 每一个list
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private ImageView imageView;// 动画图片
	private RadioButton rabAll; // 全部帖子
	private RadioButton rabEssence;// 精华帖
	private RadioButton rabMybbs; // 我的帖子
	private RadioButton rabCollect;// 我收藏的帖子
	private RadioButton rabJoin; // 我参与的帖子
	private List<RadioButton> lsRab;// radiobutton的集合
	private ImageView imgSearch; // 搜索按钮
	private LinearLayout linSearch;// 隐藏的搜索框

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_bbsmain);
		initView();
		initData();
	}

	protected void initView() {
		linSearch = (LinearLayout) findViewById(R.id.lin_search);
		imgSearch = (ImageView) findViewById(R.id.img_search);
		imgSearch.setOnClickListener(this);
		vBbsPager = (ViewPager) findViewById(R.id.vbbsPager);
		imgBack = (ImageView) findViewById(R.id.img_back);
		imgWriteBbs = (ImageView) findViewById(R.id.img_writebbs);
		imgWriteBbs.setOnClickListener(this);
		imgBack.setOnClickListener(this);
		for (int i = 0; i < 5; i++) {
			viewItems.add(getLayoutInflater().inflate(R.layout.vplistitems,
					null));
		}
		rabAll = (RadioButton) findViewById(R.id.rab_all);
		rabEssence = (RadioButton) findViewById(R.id.rab_essence);
		rabMybbs = (RadioButton) findViewById(R.id.rab_mybbs);
		rabCollect = (RadioButton) findViewById(R.id.rab_collect);
		rabJoin = (RadioButton) findViewById(R.id.rab_join);
		lsRab = new ArrayList<RadioButton>();// 初始化
		lsRab.add(rabAll);
		lsRab.add(rabEssence);
		lsRab.add(rabMybbs);
		lsRab.add(rabCollect);
		lsRab.add(rabJoin);
		rabAll.setOnClickListener(new MyOnClickListener(0));
		rabEssence.setOnClickListener(new MyOnClickListener(1));
		rabMybbs.setOnClickListener(new MyOnClickListener(2));
		rabCollect.setOnClickListener(new MyOnClickListener(3));
		rabJoin.setOnClickListener(new MyOnClickListener(4));
	}

	protected void initData() {
		// 初始化动画效果
		imageView = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(),
				R.drawable.bottomicon).getWidth();// 获取文字下方小图标宽度
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 5 - bmpW) / 2;// 计算偏移量，/2是为了显示在控件的中央
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		imageView.setImageMatrix(matrix);// 设置动画初始位置
		// 测试数据
		questionList = new ArrayList<List<String>>();
		List<String> lv = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			lv.add("1");
			questionList.add(lv);
		}
		viewpagerAdpter = new BbsVpAdapter(this, viewItems, questionList);
		vBbsPager.setAdapter(viewpagerAdpter);
		vBbsPager.setCurrentItem(0);
		vBbsPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	// 自定义头标点击监听接口实现
	private class MyOnClickListener implements OnClickListener {
		private int index = 0;

		// 带参数的构造方法
		public MyOnClickListener(int index) {
			this.index = index;
		}

		// 需要实现的接口方法
		public void onClick(View v) {
			if (linSearch.getVisibility() == View.VISIBLE) {// 若在其他页搜索框可见
				linSearch.setVisibility(View.GONE);
			}
			vBbsPager.setCurrentItem(index);
			/*
			 * 切换对应的页面，这个方法会触发MyOnPageChangeListener，
			 * 因为viewpager是已经生成好的View视图，会滚动过去，所以在
			 * MyOnPageChangeListener中添加动画就可以了
			 */
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:
			finish();
			break;
		case R.id.img_writebbs:
			// 跳转到发表帖子页面
			// jumpActivity(InvitationRaiseAct.class);
			Toast.makeText(MainActivity.this, "你点击了发表帖子！", Toast.LENGTH_LONG)
					.show();
			break;
		case R.id.img_search:
			vBbsPager.setCurrentItem(0);// 切换到全部帖子那里
			if (linSearch.getVisibility() == View.GONE) {
				linSearch.setVisibility(View.VISIBLE);
			} else {
				linSearch.setVisibility(View.GONE);
			}
			break;
		}
	}

	/**
	 * @param index
	 *            根据索引值切换页面
	 */
	public void setCurrentView(int index) {
		vBbsPager.setCurrentItem(index);
	}

	// 实现OnPageChangeListener接口
	public class MyOnPageChangeListener implements OnPageChangeListener {
		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量

		// int two = one * 2; 页卡1 -> 页卡3 偏移量,跳转了两页
		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageSelected(int position) {
			// 隐藏搜索框
			if (linSearch.getVisibility() == View.VISIBLE) {// 若在其他页搜索框可见
				linSearch.setVisibility(View.GONE);
			}
			// currIndex当前停留的页面下标，position将要滑动到的页面下标
			Animation animation = new TranslateAnimation(one * currIndex, one
					* position, 0, 0);
			currIndex = position;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);// 设置动画时间
			imageView.startAnimation(animation);
			// 选中当前的项目
			lsRab.get(vBbsPager.getCurrentItem()).setChecked(true);
			Toast.makeText(MainActivity.this,
					"您选择了" + vBbsPager.getCurrentItem() + "页卡",
					Toast.LENGTH_SHORT).show();
		}
	}

}
