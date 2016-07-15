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
	private ImageView imgBack;// ���ذ�ť
	private ImageView imgWriteBbs;// ������
	private ViewPager vBbsPager;// viewpager
	private BbsVpAdapter viewpagerAdpter;// ������
	// �������view
	private List<View> viewItems = new ArrayList<View>();
	private List<List<String>> questionList;// ÿһ��list
	private int offset = 0;// ����ͼƬƫ����
	private int currIndex = 0;// ��ǰҳ�����
	private int bmpW;// ����ͼƬ���
	private ImageView imageView;// ����ͼƬ
	private RadioButton rabAll; // ȫ������
	private RadioButton rabEssence;// ������
	private RadioButton rabMybbs; // �ҵ�����
	private RadioButton rabCollect;// ���ղص�����
	private RadioButton rabJoin; // �Ҳ��������
	private List<RadioButton> lsRab;// radiobutton�ļ���
	private ImageView imgSearch; // ������ť
	private LinearLayout linSearch;// ���ص�������

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
		lsRab = new ArrayList<RadioButton>();// ��ʼ��
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
		// ��ʼ������Ч��
		imageView = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(),
				R.drawable.bottomicon).getWidth();// ��ȡ�����·�Сͼ����
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// ��ȡ�ֱ��ʿ��
		offset = (screenW / 5 - bmpW) / 2;// ����ƫ������/2��Ϊ����ʾ�ڿؼ�������
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		imageView.setImageMatrix(matrix);// ���ö�����ʼλ��
		// ��������
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

	// �Զ���ͷ���������ӿ�ʵ��
	private class MyOnClickListener implements OnClickListener {
		private int index = 0;

		// �������Ĺ��췽��
		public MyOnClickListener(int index) {
			this.index = index;
		}

		// ��Ҫʵ�ֵĽӿڷ���
		public void onClick(View v) {
			if (linSearch.getVisibility() == View.VISIBLE) {// ��������ҳ������ɼ�
				linSearch.setVisibility(View.GONE);
			}
			vBbsPager.setCurrentItem(index);
			/*
			 * �л���Ӧ��ҳ�棬��������ᴥ��MyOnPageChangeListener��
			 * ��Ϊviewpager���Ѿ����ɺõ�View��ͼ���������ȥ��������
			 * MyOnPageChangeListener����Ӷ����Ϳ�����
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
			// ��ת����������ҳ��
			// jumpActivity(InvitationRaiseAct.class);
			Toast.makeText(MainActivity.this, "�����˷������ӣ�", Toast.LENGTH_LONG)
					.show();
			break;
		case R.id.img_search:
			vBbsPager.setCurrentItem(0);// �л���ȫ����������
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
	 *            ��������ֵ�л�ҳ��
	 */
	public void setCurrentView(int index) {
		vBbsPager.setCurrentItem(index);
	}

	// ʵ��OnPageChangeListener�ӿ�
	public class MyOnPageChangeListener implements OnPageChangeListener {
		int one = offset * 2 + bmpW;// ҳ��1 -> ҳ��2 ƫ����

		// int two = one * 2; ҳ��1 -> ҳ��3 ƫ����,��ת����ҳ
		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageSelected(int position) {
			// ����������
			if (linSearch.getVisibility() == View.VISIBLE) {// ��������ҳ������ɼ�
				linSearch.setVisibility(View.GONE);
			}
			// currIndex��ǰͣ����ҳ���±꣬position��Ҫ��������ҳ���±�
			Animation animation = new TranslateAnimation(one * currIndex, one
					* position, 0, 0);
			currIndex = position;
			animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
			animation.setDuration(300);// ���ö���ʱ��
			imageView.startAnimation(animation);
			// ѡ�е�ǰ����Ŀ
			lsRab.get(vBbsPager.getCurrentItem()).setChecked(true);
			Toast.makeText(MainActivity.this,
					"��ѡ����" + vBbsPager.getCurrentItem() + "ҳ��",
					Toast.LENGTH_SHORT).show();
		}
	}

}
