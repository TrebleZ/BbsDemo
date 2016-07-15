package com.example.adapter;

import java.util.List;

import com.example.bbsdemo.MainActivity;
import com.example.bbsdemo.R;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class BbsVpAdapter extends PagerAdapter {

	// ���ݹ�����ҳ��view�ļ���
	private List<View> viewItems;
	// ÿ��item��ҳ��view
	private View convertView;
	ViewHolder holder = null;
	private MainActivity mContext;
	private List<List<String>> dataItems;

	// ���췽��
	public BbsVpAdapter(MainActivity mContext, List<View> viewItems,
			List<List<String>> dataItems) {
		this.mContext = mContext;
		this.viewItems = viewItems;
		this.dataItems = dataItems;
	}

	// ҳ���л�ʱ,ȡ��ÿһ��������и�ֵ
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		holder = new ViewHolder();// �Զ�����
		// ȡ��ÿһ��������и�ֵ
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

	// ��ȡ��ǰѡ���ҳ��
	@Override
	public int getCount() {
		if (viewItems == null) {
			return 0;
		}
		return viewItems.size();
	}

	// �ٷ�Ĭ����ôд
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	// ɾ��ҳ��
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(viewItems.get(position));
	}

	/**
	 * @author �Զ�����
	 */
	class ViewHolder {
		ListView lvBbs;
	}

}
