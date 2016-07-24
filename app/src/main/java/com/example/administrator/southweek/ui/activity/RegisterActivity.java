package com.example.administrator.southweek.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.utils.RegexUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.register_ed_username)
    public EditText mEt_UserName ;
    @BindView(R.id.register_ed_sms_code)
    public EditText mEt_SMSCode ;
    @BindView(R.id.register_ed_userpassword)
    public EditText mEt_Psw ;

    @BindView(R.id.register_btnToRegister)
    public Button mBt_Create ;
    @BindView(R.id.register_btn_smscode)
    public Button mBt_GetSMSCode ;

    @BindView(R.id.register_deal)
    public TextView mTv_Deal ;

    private ArrayList<HashMap<String,Object>> mSupportCountry ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        findViewById(R.id.toobar_center).setVisibility(View.GONE);
        findViewById(R.id.toobar_right).setVisibility(View.GONE);
        findViewById(R.id.toolbar_line).setVisibility(View.GONE);
        TextView goBack = (TextView) findViewById(R.id.toobar_goback);
        goBack.setText("");
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
    }

    private void initView() {
        mTv_Deal.setText(Html.fromHtml("点击“创建帐号”即同意<front size='10' color='#ff00'>" +
                "<u>用户协议</u></font>"));
        handleSMSCode();//注册短信校验监听
    }
    // 吐司
    private void toastShow(final String msg){
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
           }
       });
    }

    @OnClick({R.id.register_btnToRegister,R.id.register_btn_smscode,R.id.register_deal})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.register_btn_smscode:  // 获取验证码

                if(TextUtils.isEmpty(mEt_UserName.getText().toString())){
                    toastShow("手机不能为空");
                }else if(!RegexUtils.checkMobile(mEt_UserName.getText().toString())){
                    toastShow("手机号码有误");
                }else {
                    //发送验证码
                    getSMSCode();
                }
                break;
            case R.id.register_btnToRegister: // 创建
                if(TextUtils.isEmpty(mEt_UserName.getText().toString())){
                    toastShow("手机不能为空");
                }else if(!RegexUtils.checkMobile(mEt_UserName.getText().toString())){
                    toastShow("手机号码有误");
                }else if(TextUtils.isEmpty(mEt_SMSCode.getText().toString())){
                    toastShow("验证码不能为空");
                }else if(TextUtils.isEmpty(mEt_Psw.getText().toString())){
                    toastShow("密码不能为空");
                }else{
                    sendSMSCode();//校验验证码
                }
                break ;
            case R.id.register_deal:  // 到登录协议


        }
    }

    private void handleSMSCode(){
        //打开注册页面
        EventHandler eh=new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功,HashMap<String,Object>返回校验手机号和验证码
                        toastShow("提交验证码成功");
                        HashMap<String,Object> info = (HashMap<String, Object>) data;

                        Set<String> set = info.keySet() ;

                        for(String s : set){
                            Log.i("Info" ,info.get(s).toString());
                        }
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        toastShow("获取验证码成功，请注意查收");
                        //获取验证码成功
                        Log.i("TAG", "afterEvent: GET_VERIFICATION" + data.toString());
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                       // ArrayList<HashMap<String,Object>>
                        mSupportCountry = (ArrayList<HashMap<String, Object>>) data;
                        for(HashMap<String,Object> h : mSupportCountry){

                            Iterator<Map.Entry<String,Object>> iterator = h.entrySet().iterator();
                            while(iterator.hasNext()){
                                Map.Entry<String,Object>  entry = iterator.next() ;
                                Log.i("CountryList", "afterEvent: key-->" +entry.getKey() +" value-->"+entry.getValue()+"\n");
                            }

                        }
                    }
                }else{

                    if(event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        toastShow("获取验证码失败");
                    }else if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                        toastShow("验证码校验失败");
                    }
                    ((Throwable)data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    /**
     * 获取短信验证码
     */
    public void getSMSCode(){
        // : 获取当前 支持的手机国家
        //SMSSDK.getSupportedCountries(); // 在监听那里返回 HashMap<"zone",>

       SMSSDK.getVerificationCode("86", mEt_UserName.getText().toString());  // 获取短信校验,在监听那里返回


    }

    /**
     * 校验短信验证码
     */
    private void sendSMSCode(){

        SMSSDK.submitVerificationCode("86",mEt_UserName.getText().toString(),
                mEt_SMSCode.getText().toString()); // code校验，在监听那里返回
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler(); // 与activity关联。防止造成内存泄漏
    }
}
