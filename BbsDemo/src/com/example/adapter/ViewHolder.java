package com.example.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {

	private SparseArray<View> store;
	private int mPosition;
	private View mContentView;
	private Context mContext;

	public ViewHolder(Context context, int layoutId, int position,
			ViewGroup parent) {
		store = new SparseArray<View>();
		mContext = context;
		mPosition = position;
		mContentView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		mContentView.setTag(this);
	}

	public static ViewHolder get(Context context, int layoutId,
			View convertView, int position, ViewGroup parent) {
		if (convertView == null)
			return new ViewHolder(context, layoutId, position, parent);
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		viewHolder.mPosition = position;
		return viewHolder;
	}

	/**
	 * Ëé∑Âèñ
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int id) {
		View view = store.get(id);
		if (null == view) {
			view = mContentView.findViewById(id);
			store.put(id, view);
		}
		return (T) view;
	}

	public View getContentView() {
		return mContentView;
	}

	public Context getContext() {
		return mContext;
	}

	public ViewHolder setImage(int id, Bitmap bm) {
		ImageView iv = getView(id);
		iv.setImageBitmap(bm);
		return this;
	}

	public ViewHolder setImage(int id, int resId) {
		ImageView iv = getView(id);
		iv.setImageDrawable(mContext.getResources().getDrawable(resId));
		return this;
	}

//	// Õº∆¨URL
//	public ViewHolder setImage(int id, String url) {
//		ImageView iv = getView(id);
//		BitmapUtils bitmapUtils = new BitmapUtils(mContext);
//		// bitmapUtils.configDefaultLoadingImage(R.drawable.empty);
//		bitmapUtils.display(iv, url);
//		return this;
//	}

	public ViewHolder setText(int id, String text) {
		TextView tv = getView(id);
		tv.setText(text);
		return this;
	}

	// boolean…Ë÷√
	public ViewHolder setBoolean(int id, Boolean bool) {
		CheckBox chek = getView(id);
		chek.setChecked(bool);
		return this;
	}

	public ViewHolder setTextFromHtml(int id, String text) {
		TextView tv = getView(id);
		tv.setText(Html.fromHtml(text));
		return this;
	}

	public ViewHolder setTextFromHtmlWithimg(int id, String text,
			String strImage, ImageGetter ig) {
		TextView tv = getView(id);
		tv.setText(Html.fromHtml(strImage, ig, null));
		tv.append(Html.fromHtml(text));
		return this;
	}

	public int getPosition() {
		return mPosition;
	}

}
