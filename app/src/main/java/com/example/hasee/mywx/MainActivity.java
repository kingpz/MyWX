package com.example.hasee.mywx;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import Adapter.Message_list_Adapter;
import Bean.Messages;
import Bean.UserBean;

public class MainActivity  extends AppCompatActivity implements OnClickListener,OnPageChangeListener {

    private ViewPager viewPager;
    private ImageButton one;
    private ImageButton two;
    private ImageButton three;
    private ImageButton four;
    // 底部菜单4个Linearlayout
    private LinearLayout ll_home;
    private LinearLayout ll_address;
    private LinearLayout ll_friend;
    private LinearLayout ll_setting;

    // 底部菜单4个ImageView
    private ImageView iv_home;
    private ImageView iv_address;
    private ImageView iv_friend;
    private ImageView iv_setting;

    // 底部菜单4个菜单标题
    private TextView tv_home;
    private TextView tv_address;
    private TextView tv_friend;
    private TextView tv_setting;

    private Toolbar toolbar;
    // 中间内容区域
   // private ViewPager viewPager;

    // ViewPager适配器ContentAdapter
    private ContentAdapter adapter;

    private List<View> views;

    Context mContext;
    final ArrayList<Messages> list=new ArrayList<>();;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("微信");
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        setSupportActionBar(toolbar);
        //toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        initView();
        initViewPager();
    }
    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            String msg = "";
            switch (item.getItemId()) {
                case R.id.action_add:
                    UserBean user = (UserBean) getIntent().getSerializableExtra("user");
                    System.out.println(user);
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,AddFriendsActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                    break;
                case R.id.action_search:
                    msg += "Click share";
                    break;
                case R.id.action_help:
                    msg += "Click setting";
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                UserBean user = (UserBean) getIntent().getSerializableExtra("user");
                System.out.println(user);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AddFriendsActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            case R.id.action_help:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;
            case R.id.action_search:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }




    private void initViewPager() {
        //获取ViewPager
        //创建一个FragmentPagerAdapter对象，该对象负责为ViewPager提供多个Fragment
        viewPager = (ViewPager) findViewById(R.id.vp_content);
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(
                getSupportFragmentManager()) {

            //获取第position位置的Fragment
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new FirstFragment();
                        break;
                    case 1:
                        fragment = new SecondFragmnt();
                        break;
                    case 2:
                        fragment = new ThirdFragmrnt();
                        break;
                    case 3:
                        fragment = new ForthFragment();
                        break;
                }

                return fragment;
            }

            //该方法的返回值i表明该Adapter总共包括多少个Fragment
            @Override
            public int getCount() {
                return 4;
            }

        };
        //为ViewPager组件设置FragmentPagerAdapter
        viewPager.setAdapter(pagerAdapter);

        //为viewpager组件绑定时间监听器
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            //当ViewPager显示的Fragment发生改变时激发该方法
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    //如果是点击的第一个button，那么就让第一个button的字体变为蓝色
                    //其他的button的字体的颜色变为黑色
                    case 0:
                        one.setImageResource(R.drawable.icon_chat_selected);
                        two.setImageResource(R.drawable.icon_main_home_normal);
                        three.setImageResource(R.drawable.icon_find_normal);
                        four.setImageResource(R.drawable.icon_mine_normal);
                        break;
                    case 1:
                        two.setImageResource(R.drawable.icon_main_home_selected);
                        one.setImageResource(R.drawable.icon_chat_narmal);

                        three.setImageResource(R.drawable.icon_find_normal);
                        four.setImageResource(R.drawable.icon_mine_normal);
                        break;
                    case 2:
                        three.setImageResource(R.drawable.icon_find_selected);
                        one.setImageResource(R.drawable.icon_chat_narmal);
                        two.setImageResource(R.drawable.icon_main_home_normal);

                        four.setImageResource(R.drawable.icon_mine_normal);
                        break;
                    case 3:
                        four.setImageResource(R.drawable.icon_mine_selected);
                        one.setImageResource(R.drawable.icon_chat_narmal);
                        two.setImageResource(R.drawable.icon_main_home_normal);
                        three.setImageResource(R.drawable.icon_find_normal);

                        break;
                }
                super.onPageSelected(position);
            }
        });
    }

    private void initView() {

        one = (ImageButton) findViewById(R.id.bt_one);
        two = (ImageButton) findViewById(R.id.bt_two);
        three = (ImageButton) findViewById(R.id.bt_three);
        four = (ImageButton) findViewById(R.id.bt_four);

        //设置点击监听
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);

        //将button中字体的颜色先按照点击第一个button的效果初始化
        one.setImageResource(R.drawable.icon_chat_selected);
        two.setImageResource(R.drawable.icon_main_home_normal);
        three.setImageResource(R.drawable.icon_find_normal);
        four.setImageResource(R.drawable.icon_mine_normal);
        UserBean user=(UserBean) getIntent().getSerializableExtra("user");
        System.out.println(user.toString());
    }

    //点击主界面上面的button后，将viewpager中的fragment跳转到对应的item
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_one:
                viewPager.setCurrentItem(0);
                break;
            case R.id.bt_two:
                viewPager.setCurrentItem(1);
                break;
            case R.id.bt_three:
                viewPager.setCurrentItem(2);
                break;
            case R.id.bt_four:
                viewPager.setCurrentItem(3);
                break;
        }
    }
}
//        // 初始化控件activity_main
//        initView();
//        // 初始化底部按钮事件
//        initEvent();
//
//    }
//
//    private void initEvent() {
//        // 设置按钮监听
//        ll_home.setOnClickListener(this);
//        ll_address.setOnClickListener(this);
//        ll_friend.setOnClickListener(this);
//        ll_setting.setOnClickListener(this);
//
//        //设置ViewPager滑动监听
//        viewPager.setOnPageChangeListener(this);
//    }
//
//    private void initView() {
//
//        // 底部菜单4个Linearlayout
//        this.ll_home = (LinearLayout) findViewById(R.id.ll_home);
//        this.ll_address = (LinearLayout) findViewById(R.id.ll_address);
//        this.ll_friend = (LinearLayout) findViewById(R.id.ll_friend);
//        this.ll_setting = (LinearLayout) findViewById(R.id.ll_setting);
//
//        // 底部菜单4个ImageView
//        this.iv_home = (ImageView) findViewById(R.id.iv_home);
//        this.iv_address = (ImageView) findViewById(R.id.iv_address);
//        this.iv_friend = (ImageView) findViewById(R.id.iv_friend);
//        this.iv_setting = (ImageView) findViewById(R.id.iv_setting);
//
//        // 底部菜单4个菜单标题
//        this.tv_home = (TextView) findViewById(R.id.tv_home);
//        this.tv_address = (TextView) findViewById(R.id.tv_address);
//        this.tv_friend = (TextView) findViewById(R.id.tv_friend);
//        this.tv_setting = (TextView) findViewById(R.id.tv_setting);
//
//        // 中间内容区域ViewPager
//        this.viewPager = (ViewPager) findViewById(R.id.vp_content);
//
//        // 适配器
//        View page_01 = View.inflate(MainActivity.this, R.layout.message, null);
//        View page_02 = View.inflate(MainActivity.this, R.layout.friend, null);
//        View page_03 = View.inflate(MainActivity.this, R.layout.friendcircle, null);
//        View page_04 = View.inflate(MainActivity.this, R.layout.me, null);
//
//        views = new ArrayList<View>();
//        views.add(page_01);
//        views.add(page_02);
//        views.add(page_03);
//        views.add(page_04);
//
//        this.adapter = new ContentAdapter(views);
//        viewPager.setAdapter(adapter);
//
//        ListView listView=(ListView)findViewById(R.id.message_list);
//        Messages messages=new Messages();
//        messages.setM_PostMessages("你已添加我为好友了");
//        UserBean user=(UserBean) getIntent().getSerializableExtra("user");
//        int id=0;
//        if(user.getU_ID()==1)
//        {
//            id=4;
//        }
//        else if(user.getU_ID()==4)
//        {
//            id=1;
//        }
//        messages.setM_FromUserID(id);
//        ArrayList<Messages> list=new ArrayList<>();
//        list.add(messages);
//        Message_list_Adapter adapter=new Message_list_Adapter(list,mContext);
//        listView.setAdapter(adapter);
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
//        restartBotton();
//        // ImageView和TetxView置为绿色，页面随之跳转
//        switch (v.getId()) {
//            case R.id.ll_home:
//                iv_home.setImageResource(R.drawable.messages);
//                tv_home.setTextColor(0xff1B940A);
//                viewPager.setCurrentItem(0);
//                break;
//            case R.id.ll_address:
//                iv_address.setImageResource(R.drawable.friend);
//                tv_address.setTextColor(0xff1B940A);
//                viewPager.setCurrentItem(1);
//                break;
//            case R.id.ll_friend:
//                iv_friend.setImageResource(R.drawable.friendcirle);
//                tv_friend.setTextColor(0xff1B940A);
//                viewPager.setCurrentItem(2);
//                break;
//            case R.id.ll_setting:
//                iv_setting.setImageResource(R.drawable.me);
//                tv_setting.setTextColor(0xff1B940A);
//                viewPager.setCurrentItem(3);
//                break;
//
//            default:
//                break;
//        }
//
//    }
//
//    private void restartBotton() {
//        // ImageView置为灰色
//        iv_home.setImageResource(R.drawable.messages);
//        iv_address.setImageResource(R.drawable.friend);
//        iv_friend.setImageResource(R.drawable.friendcirle);
//        iv_setting.setImageResource(R.drawable.me);
//        // TextView置为白色
//        tv_home.setTextColor(0xffffffff);
//        tv_address.setTextColor(0xffffffff);
//        tv_friend.setTextColor(0xffffffff);
//        tv_setting.setTextColor(0xffffffff);
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int arg0) {
//
//    }
//
//    @Override
//    public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//    }
//
//    @Override
//    public void onPageSelected(int arg0) {
//        restartBotton();
//        //当前view被选择的时候,改变底部菜单图片，文字颜色
//        switch (arg0) {
//            case 0:
//                iv_home.setImageResource(R.drawable.messages);
//                tv_home.setTextColor(0xff1B940A);
//                break;
//            case 1:
//                iv_address.setImageResource(R.drawable.friend);
//                tv_address.setTextColor(0xff1B940A);
//                break;
//            case 2:
//                iv_friend.setImageResource(R.drawable.friendcirle);
//                tv_friend.setTextColor(0xff1B940A);
//                break;
//            case 3:
//                iv_setting.setImageResource(R.drawable.me);
//                tv_setting.setTextColor(0xff1B940A);
//                break;
//
//            default:
//                break;
//        }
//
//
//}
//}
