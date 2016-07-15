package com.example.adapter;

import java.util.List;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class AbListAdapter<T> extends BaseAdapter {
	private List<T> mData;
	protected Context mContext;
	private int layoutId;

	public AbListAdapter(Context context, List<T> data, int layoutId) {
		this.mData = data;
		this.mContext = context;
		this.layoutId = layoutId;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public T getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = ViewHolder.get(mContext, layoutId, convertView,
				position, parent);
		converView(viewHolder, mData.get(position));
		return viewHolder.getContentView();
	}

	protected abstract void converView(ViewHolder viewHolder, T bean);

	public List<T> getData() {
		return mData;
	}
}
