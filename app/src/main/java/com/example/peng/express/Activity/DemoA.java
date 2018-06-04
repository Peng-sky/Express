//
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.Window;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//public class MainActivity extends Activity {
//
//    public static final String REQUEST_URL = "http://192.168.1.120:8080/test_server/servlet/UserManager?";
//
//    private String username="";
//    private String userpwd="";
//
//    private EditText editTextUsername,editTextUserpwd;
//    private Button buttonLogin;
//    /** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.main);
//
//        //初始化控件
//        initViews();
//    }
//
//    private void initViews() {
//        editTextUsername=(EditText) findViewById(R.id.id_username);
//        editTextUserpwd=(EditText) findViewById(R.id.id_userpwd);
//        buttonLogin=(Button) findViewById(R.id.id_login);
//        buttonLogin.setOnClickListener(clickListener);
//    }
//
//    private OnClickListener clickListener=new OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            // TODO Auto-generated method stub
//            username=editTextUsername.getText().toString();
//            userpwd=editTextUserpwd.getText().toString();
//            if(username.equals("")||userpwd.equals("")){
//                Toast.makeText(MainActivity.this, "用户名或密码为空！", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            //请求的参数
//            final String params="request_flag=login&username="+username+"&userpwd="+userpwd;
//            new Thread(new Runnable() {
//
//                @Override
//                public void run() {
//                    // TODO Auto-generated method stub
//                    //得到json字符串
//                    String resultJson = HttpUtil.sendGet(REQUEST_URL,params);
//                    //得到返回值
//                    int res=JsonUtil.getResult("result", resultJson);
//                    if(res==1){
//                        handler.sendEmptyMessage(1);
//                    }
//                }
//            }).start();
//        }
//    };
//
//    Handler handler=new Handler(){
//        public void handleMessage(android.os.Message msg) {
//            Intent intent=new Intent();
//            intent.setClass(MainActivity.this, SuccessActivity.class);
//            intent.putExtra("username", username);
//            startActivity(intent);
//        };
//    };
//}