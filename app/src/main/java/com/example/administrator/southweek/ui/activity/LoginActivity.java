package com.example.administrator.southweek.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.bean.UserInfo;
import com.example.administrator.southweek.ui.fragment.SettingFragment;
import com.example.administrator.southweek.ui.utils.Contants;
import com.example.administrator.southweek.ui.utils.HttpHelper;
import com.example.administrator.southweek.ui.utils.JsonParserUtils;
import com.example.administrator.southweek.ui.utils.RegexUtils;
import com.example.administrator.southweek.ui.utils.SharePreferenceUtils;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/18.
 */
public class LoginActivity extends AppCompatActivity {

    @BindViews({R.id.login_qq_login, R.id.login_weichat_login, R.id.login_sina_login})
    public ImageView[] mBtn;
    @BindView(R.id.login_forget_pass)
    public TextView mTv_ForgetPass;
    @BindView(R.id.login_create_user)
    public TextView mTv_New_User;

    @BindView(R.id.login_ed_username)
    public EditText mEt_UserName;
    @BindView(R.id.login_ed_userpassword)
    public EditText mEt_UserPsw;

    @BindView(R.id.login_btnToLogin)
    public Button mLogin;

    private AuthInfo mAuthInfo = null;
    private SsoHandler mSsoHandler = null;


    public static final int LoginActivity_CODE = 300;

    private Dialog mDialog ;
    private ImageView mRotate ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(LoginActivity.this);
        initToolBar();
        init();
    }

    private void init() {
        //实例化微博授权对象
        mAuthInfo = new AuthInfo(getApplicationContext(), Contants.APP_KEY,
                Contants.REDIRECT_URL, Contants.SCOPE);
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
                LoginActivity.this.finish();
            }
        });

    }

    @OnClick({R.id.login_qq_login, R.id.login_weichat_login, R.id.login_sina_login,
            R.id.login_forget_pass, R.id.login_create_user, R.id.login_btnToLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_qq_login:  // QQ第三方登录

                break;

            case R.id.login_weichat_login: // 微信第三方登录

                break;

            case R.id.login_sina_login:  // 新浪第三方登录
                // 客户端是有效
                mSsoHandler = new SsoHandler(LoginActivity.this, mAuthInfo);
                mSsoHandler.authorize(new AuthListener());
                break;

            case R.id.login_forget_pass: // 忘记密码

                break;

            case R.id.login_create_user: // 注册帐号
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.login_btnToLogin: // 登录
                login();
                //调用 dialog
                showDialog();

        }
    }

    /**
     * 登录逻辑
     */
    private void login() {
        // 1 .判断edit框是否为空
        if (TextUtils.isEmpty(mEt_UserName.getText().toString())) {
            toastShow("请输入手机号或者邮箱地址");
            return;
        } else {
            // 1.1手机号或者邮箱是否规范
            if (RegexUtils.checkMobile(mEt_UserName.getText().toString()) ||
                    RegexUtils.checkEmail(mEt_UserName.getText().toString())) {
            } else {
                toastShow("手机号或者邮箱地址格式不正确");
                return;
            }
        }

        // 2. 判断密码是否为空
        if (TextUtils.isEmpty(mEt_UserPsw.getText().toString())) {
            toastShow("密码不能为空");
            return;
        }

        // 3. 登录逻辑

    }

    private void showDialog(){
        mDialog = new Dialog(LoginActivity.this,R.style.UpdateRotate);
        View view = LayoutInflater.from(LoginActivity.this).inflate
                (R.layout.dialog_layout_progress,null);
        mRotate = (ImageView) view.findViewById(R.id.updating_rotate);
        Animation animation = AnimationUtils.loadAnimation
                (LoginActivity.this,R.anim.updating_rotate_anim);
        animation.start();
        mRotate.setAnimation(animation);

        mDialog.setContentView(view);

        mDialog.show();
    }

    private void dismissDialog(){
        if(mDialog !=null){
            if(mRotate != null) {
                mRotate.clearAnimation();
            }
            mDialog.dismiss();
        }
    }
    // 吐司
    private void toastShow(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);//回调


        }
    }

    public class AuthListener implements WeiboAuthListener {
        public Oauth2AccessToken mAccessToken = null;

        @Override
        public void onComplete(Bundle bundle) {
            // 授权成功后在这里 返回 token_access
            mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);

            if (mAccessToken.isSessionValid()) {
                String accessToken = bundle.getString("access_token");
                String expires_in = bundle.getString("expires_in");
                String uid = bundle.getString("uid");
                Log.i("TAG", "onComplete: access_token-->" +accessToken+ ", expires_in -- >"+
                             expires_in +", uid -->" + uid);
                // 保存 token
                SharePreferenceUtils.saveUserAccessToken(LoginActivity.this,accessToken,
                        System.currentTimeMillis()+"");

                // 返回主界面
                HttpHelper helper = HttpHelper.getInstance();
                helper.requestByGet(Contants.USER_PATH + accessToken, new HttpHelper.StringCallBack() {
                    @Override
                    public void onFailure(Exception e) {
                        dismissDialog();
                        toastShow("网络失败");
                    }

                    @Override
                    public void onResult(Object string) {
                        Log.i("TAG", "onResult: "  + string.toString());
                                UserInfo userInfo = JsonParserUtils.getUserInfo((String)string);
                        Intent intent = new Intent(LoginActivity.this, SettingFragment.class);
                        intent.putExtra("userInfo", userInfo);
                        LoginActivity.this.setResult(LoginActivity_CODE,intent);

                        dismissDialog();
                        LoginActivity.this.finish();
                    }
                });


            } else {

                String code = bundle.getString("code", "");
                Log.i("TAG", "onWeiboException: " + code);
                dismissDialog();
                toastShow("授权失败" + code);
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Log.i("TAG", "onWeiboException: " +e.getMessage().toString());
            dismissDialog();
            toastShow("授权失败");
        }

        @Override
        public void onCancel() {
            dismissDialog();
            toastShow("授权取消");
        }
    }
}
