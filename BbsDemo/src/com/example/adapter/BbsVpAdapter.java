package com.example.adapter;

import java.util.List;

import com.example.bbsdemo.MainActivity;
import com.example.bbsdemo.R;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class BbsVpAdapter extends PagerAdapter {

	// 传递过来的页面view的集合
	private List<View> viewItems;
	// 每个item的页面view
	private View convertView;
	ViewHolder holder = null;
	private MainActivity mContext;
	private List<List<String>> dataItems;

	// 构造方法
	public BbsVpAdapter(MainActivity mContext, List<View> viewItems,
			List<List<String>> dataItems) {
		this.mContext = mContext;
		this.viewItems = viewItems;
		this.dataItems = dataItems;
	}

	// 页面切换时,取得每一个子项进行赋值
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		holder = new ViewHolder();// 自定义类
		// 取到每一个子项，进行赋值
		convertView = viewItems.get(position);
		holder.lvBbs = (ListView) convertView.findViewById(R.id.lvbbscontent);
		holder.lvBbs.setAdapter(new AbListAdapter<String>(mContext, dataItems
				.get(position), R.layout.lvbbsitems) {

			@Override
			protected void converView(
					com.example.adapter.ViewHolder viewHolder, String bean) {

			}

		});
		container.addView(viewItems.get(position));
		return viewItems.get(position);
	}

	// 获取当前选项卡的页数
	@Override
	public int getCount() {
		if (viewItems == null) {
			return 0;
		}
		return viewItems.size();
	}

	// 官方默认这么写
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	// 删除页卡
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(viewItems.get(position));
	}

	/**
	 * @author 自定义类
	 */
	class ViewHolder {
		ListView lvBbs;
	}

}
