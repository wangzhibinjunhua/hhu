package com.wzb.hhu.activity;

import java.util.ArrayList;
import java.util.List;

import com.wzb.hhu.R;
import com.wzb.hhu.bean.UserBean;
import com.wzb.hhu.util.LogUtil;
import com.wzb.hhu.util.ResTools;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class UserManagerActivity extends BaseActivity implements OnScrollListener{
	
	private ImageView backView;
	private TextView titleView;
	
	//private Button 
	
	private UserAdapter userAdapter;

	private ListView userListView = null;
	private int curPosition = -1;
	private int titlePosition = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_usermanager);
		initTitleView();
		initView();
	}
	
	private void initView(){
		userListView = (ListView) findViewById(R.id.lv_user);
		initAdapter();
		
		userListView.setAdapter(userAdapter);
		userListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		userListView.setOnScrollListener(this);
		userListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				curPosition=arg2;
				LogUtil.logMessage("wzb", "curpos="+curPosition);
				userAdapter.notifyDataSetChanged();
			}
			
		});
	}
	
	private void initAdapter(){
		List<UserBean> users = new ArrayList<UserBean>();
		String x[] = ResTools.getResStringArray(UserManagerActivity.this, R.array.user_title);
		UserBean userTitle=new UserBean();
		userTitle.setAccount(x[0]);
		userTitle.setName(x[1]);
		userTitle.setLevel(x[2]);
		users.add(userTitle);
		

		userAdapter=new UserAdapter(users);
	}
	
	private void initTitleView() {
		backView = (ImageView) findViewById(R.id.title_back);
		titleView = (TextView) findViewById(R.id.title_text);
		titleView.setText(ResTools.getResString(UserManagerActivity.this, R.string.user_manager));
		backView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	class UserAdapter extends BaseAdapter{
		
		List<UserBean> users;
		public UserAdapter(List<UserBean> users) {
			// TODO Auto-generated constructor stub
			this.users = users;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return users.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return users.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(R.layout.user_list_item, null);
			}

			TextView account = (TextView) convertView.findViewById(R.id.user_account);
			TextView name = (TextView) convertView.findViewById(R.id.user_name);
			TextView level = (TextView) convertView.findViewById(R.id.user_level);
			account.setText(users.get(position).getAccount());
			name.setText(users.get(position).getName());
			level.setText(users.get(position).getLevel());

			int[] colors = { Color.WHITE, Color.rgb(219, 238, 244) };// RGB颜色

			convertView.setBackgroundColor(colors[position % 2]);// 每隔item之间颜色不同
			
			if(curPosition==position){
				convertView.setBackgroundColor(Color.YELLOW);
			}
			
			if(titlePosition==position){
				account.setTextSize(18);
				name.setTextSize(18);
				level.setTextSize(18);
			}else{
				account.setTextSize(15);
				name.setTextSize(15);
				level.setTextSize(15);
			}

			return convertView;
		}

		public UserBean getUser(int id) {

			return users.get(id);
		}

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

}