package com.gaia.member.gaiatt.healthmanage.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gaia.member.gaiatt.R;
import com.gaia.member.gaiatt.base.AppBaseActivity;
import com.gaia.member.gaiatt.utils.CommonConstants;
import com.gaia.member.gaiatt.utils.GetResourcesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * @author 移动开发组-王浩韩
 * @version V1.0
 * @Title: QuestionnaireActivity
 * @Package com.gaia.member.gaiatt.healthmanage.activity
 * @Description: 问卷调查活动界面
 * Copyright: Copyright (c) 2016
 * Company: 成都壹柒互动科技有限公司
 * @date 2016/6/5 0005 下午 11:30
 */
public class QuestionnaireActivity extends AppBaseActivity {


    @Bind(R.id.titlebar_back_img)
    ImageView titlebarBackImg;

    @Bind(R.id.titlebar_tv)
    TextView titlebarTv;
    @Bind(R.id.titlebar_search_img)
    ImageView titlebarSearchImg;
    @Bind(R.id.lv_chat_list)
    ListView lvChatList;
    @Bind(R.id.tv_choice_a)
    TextView tvChoiceA;
    @Bind(R.id.tv_choice_b)
    TextView tvChoiceB;
    @Bind(R.id.tv_choice_c)
    TextView tvChoiceC;
    @Bind(R.id.tv_choice_d)
    TextView tvChoiceD;
    @Bind(R.id.tv_choice_save)
    TextView tvChoiceSave;
    @Bind(R.id.tv_edt_save)
    TextView tvEdtSave;
    @Bind(R.id.chat_bottom_linear_one)
    LinearLayout chatBottomLinearOne;
    @Bind(R.id.edt_chat_bottom)
    EditText edtChatBottom;
    @Bind(R.id.chat_bottom_linear_two)
    LinearLayout chatBottomLinearTwo;

    //==============================================
    //数据
    String[] from={"image","text"};
    int[] to={R.id.chatlist_image_me,R.id.chatlist_text_me,R.id.chatlist_image_other,R.id.chatlist_text_other};
    int[] layout={R.layout.right_send_layout,R.layout.left_send_layout};//子布局视图
    ArrayList<HashMap<String,Object>> chatList=null;//适配器数据源
    protected ChatAdapter chatAdapter=null;
    public final static int OTHER=1;
    public final static int ME=0;
    private  TextView[] tvChoices;//
    String[] singleAnswer=new String[1];//单选类型答案
    String[] multAnswer=new String[4];//多选类型答案
    private int currentType;//当前答题类型
    //计时器
    private Timer mTimer;
    private TimerTask mTimerTask;
    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_questionnaire);
        ButterKnife.bind(this);
        titlebarTv.setText(GetResourcesUtils.getString(this,R.string.questionaire_title));
        titlebarSearchImg.setBackgroundResource(R.drawable.selector_icon__back_cd);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tvChoices=new TextView[]{tvChoiceA,tvChoiceB,tvChoiceC,tvChoiceD};
        chatList=new ArrayList<HashMap<String,Object>>();
        chatAdapter=new ChatAdapter(this,chatList,layout,from,to);
        lvChatList.setAdapter(chatAdapter);
    }

    @Override
    protected void loadData() {
        startTimer();
    }

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setTypeAnswer(msg.arg1);
        }
    };
    /**
     * 开始计时器
     * */
    private void startTimer() {
        if (mTimer == null) {
            mTimer=new Timer();
        }
        if (mTimerTask == null) {
            mTimerTask=new TimerTask() {
                @Override
                public void run() {
                    Message msg=Message.obtain();
                    msg.arg1= (int) (Math.random()*4);
                    mHandler.sendMessage(msg);
                }
            };
            mTimer.schedule(mTimerTask, 1000,1000);
        }
    }

    /**
     * 关闭计时器
     * */
    private void closeTimer(){
        mHandler.removeMessages(0);
        if(mTimerTask != null){
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    /**
     * 获取当前问题类型
     * @param type
     * */
    private void setTypeAnswer(int type){
        setQuestion(type);
        currentType=type;
        switch (type){
            case CommonConstants.NOTANSWER://不需要回答
                break;
            case CommonConstants.SINGLESELECTION://单选
                closeTimer();
                chatBottomLinearOne.setVisibility(View.VISIBLE);
                break;
            case CommonConstants.MULTISELETION://多选
                closeTimer();
                chatBottomLinearOne.setVisibility(View.VISIBLE);
                break;
            case CommonConstants.EDITTEXT://填空
                closeTimer();
                chatBottomLinearTwo.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    //设置问卷
    private void setQuestion(int type) {
        addTextToList("testType" + type, OTHER);
        chatAdapter.notifyDataSetChanged();
        lvChatList.setSelection(chatList.size() - 1);
    }

    //问卷结果设置
    private void setResult(String resultString) {
        // TODO Auto-generated method stub
        /**
         * 这是一个发送消息的监听器，注意如果文本框中没有内容，那么getText()的返回值可能为
         * null，这时调用toString()会有异常！所以这里必须在后面加上一个""隐式转换成String实例
         * ，并且不能发送空消息。
         */
        if(resultString==null&&resultString.equals(""))
            return;
        addTextToList(resultString, ME);
        /**
         * 更新数据列表，并且通过setSelection方法使ListView始终滚动在最底端
         */
        chatAdapter.notifyDataSetChanged();
        lvChatList.setSelection(chatList.size() - 1);
    }

    protected void addTextToList(String text, int who){
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("person",who );
        map.put("image", who == ME ? R.drawable.ph_avatar_female : R.drawable.ph_avatar_female);
        map.put("text", text);
        chatList.add(map);
    }
    //填空题答案处理
    private void setEditAnswer() {
        String textEdt=edtChatBottom.getText()+"";
        if (textEdt != null && !textEdt.equals("")) {
            setResult(textEdt);
            startTimer();
            chatBottomLinearTwo.setVisibility(View.GONE);
            edtChatBottom.setText("");
        }
    }

    //选择题答案处理
    private void setChoiceAnswer() {
        if (currentType == CommonConstants.SINGLESELECTION) {//单选
            if (singleAnswer[0] != null&&!singleAnswer[0].equals("")) {
                setResult("你选择了答案 "+singleAnswer[0]);
                singleAnswer[0]="";
                initChoiceData();
            }
        }else if(currentType == CommonConstants.MULTISELETION&&multAnswer.length!=0){//多选
            StringBuffer textString = new StringBuffer();
            for (int i = 0; i <multAnswer.length ; i++) {
                if (multAnswer[i]!= null) {
                    textString.append(multAnswer[i]+"");
                    multAnswer[i]="";
                }
            }
            if (textString!=null&&!textString.toString().equals("")){
                setResult("你选择了答案 "+textString);
                initChoiceData();
            }
        }
    }

    /**
     * 选项按钮初始化
     * */
    private void initChoiceData() {
        startTimer();
        chatBottomLinearOne.setVisibility(View.GONE);
        for (int i = 0; i <tvChoices.length ; i++) {
            tvChoices[i].setBackgroundResource(R.drawable.btn_chat_blue);
        }
    }

    /**
     * 判断是否单选
     * @param index
     * */
    private void isSingle(int index) {
        if (currentType == CommonConstants.SINGLESELECTION) {//单选
            for (int i = 0; i <tvChoices.length ; i++) {
                if (i == index) {
                    tvChoices[i].setBackgroundResource(R.drawable.btn_chat_lightblue);
                    singleAnswer[0]=tvChoices[i].getText()+"";
                }else {
                    tvChoices[i].setBackgroundResource(R.drawable.btn_chat_blue);
                }
            }
        }else if(currentType == CommonConstants.MULTISELETION) {//多选
            tvChoices[index].setBackgroundResource(R.drawable.btn_chat_lightblue);
            multAnswer[index]=tvChoices[index].getText() + "";
        }
    }

    //选项A
    @OnClick(R.id.tv_choice_a)
    void tvChoiceA(){
        isSingle(0);
    }
    //选项B
    @OnClick(R.id.tv_choice_b)
    void tvChoiceB(){
        isSingle(1);
    }
    //选项C
    @OnClick(R.id.tv_choice_c)
    void tvChoiceC(){
        isSingle(2);
    }
    //选项D
    @OnClick(R.id.tv_choice_d)
    void tvChoiceD(){
        isSingle(3);
    }
    //选择保存
    @OnClick(R.id.tv_choice_save)
    void tvChoiceSave(){
        setChoiceAnswer();
    }
    //填空保存
    @OnClick(R.id.tv_edt_save)
    void tvEdtSave(){
        setEditAnswer();
    }
    //返回
    @OnClick(R.id.titlebar_back_ll)
    void titlebarBackLl() {
        onBackPressed();
    }

    @OnClick(R.id.titlebar_search_ll)

        //进入问卷列表页面
    void titlebarSearchLl() {
        Intent intent = new Intent(QuestionnaireActivity.this, QuestionaireListActivity.class);
        startActivity(intent);
    }








    private class ChatAdapter extends BaseAdapter {

        Context context=null;
        ArrayList<HashMap<String,Object>> chatList=null;
        int[] layout;
        String[] from;
        int[] to;

        public ChatAdapter(Context context,
                           ArrayList<HashMap<String, Object>> chatList, int[] layout,
                           String[] from, int[] to) {
            super();
            this.context = context;
            this.chatList = chatList;
            this.layout = layout;
            this.from = from;
            this.to = to;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return chatList.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        class ViewHolder{
            public ImageView imageView=null;
            public TextView textView=null;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder=null;
            int who=(Integer)chatList.get(position).get("person");

            convertView= LayoutInflater.from(context).inflate(
                    layout[who==ME?0:1], null);
            holder=new ViewHolder();
            holder.imageView=(ImageView)convertView.findViewById(to[who*2+0]);
            holder.textView=(TextView)convertView.findViewById(to[who*2+1]);
            holder.imageView.setBackgroundResource((Integer)chatList.get(position).get(from[0]));
            holder.textView.setText(chatList.get(position).get(from[1]).toString());
            return convertView;
        }

    }
}
