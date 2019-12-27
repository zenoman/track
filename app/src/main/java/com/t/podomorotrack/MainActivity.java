package com.t.podomorotrack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.t.podomorotrack.Config.Spf;
import com.t.podomorotrack.Config.env;
import com.t.podomorotrack.Menu.Beranda;
import com.t.podomorotrack.Model.Respon_login;
import com.t.podomorotrack.Model.iModel;
import com.t.podomorotrack.podo.Json_User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText user,pass;
    private  Button btnlogin;
    private env db=new env();
    private List<Json_User> userList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText) findViewById(R.id.username);
        pass=(EditText) findViewById(R.id.password);
        btnlogin=(Button) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logincheck(user.getText().toString(),pass.getText().toString());
            }
        });
        if(Spf.getmInstance(MainActivity.this).isLogIn()){
            startActivity(new Intent(MainActivity.this,Beranda.class));
        }
    }

    private void logincheck(String usm, String upass) {
        final ProgressDialog dialog=new ProgressDialog(MainActivity.this);
        dialog.setCancelable(true);
        dialog.setMessage("Proses login..");
        String cunam=user.getText().toString();
        String cpass=pass.getText().toString();
        if(TextUtils.isEmpty(cunam)){
            user.setError("Harap Diisi");
            user.requestFocus();
            return;
        }else if (TextUtils.isEmpty(cpass)){
            pass.setError("Harap Diisi");
            pass.requestFocus();
            return;
        }
        dialog.show();
        iModel model=db.getRespo().create(iModel.class);
        Call<Respon_login>call=model.login(usm,upass);
        call.enqueue(new Callback<Respon_login>() {
            @Override
            public void onResponse(Call<Respon_login> call, Response<Respon_login> response) {
                String sts=response.body().getSts();
                String msg=response.body().getMsg();
                if(response.isSuccessful()){
                    dialog.dismiss();
                    if(sts.equals("1")){
                        userList=response.body().getData();
                        for(int i=0;i<userList.size();i++){
                            Json_User userinfo=new Json_User(
                                    userList.get(i).getId(),
                                    userList.get(i).getNip(),
                                    userList.get(i).getNama(),
                                    userList.get(i).getUsername(),
                                    userList.get(i).getTelp(),
                                    userList.get(i).getAlamat(),
                                    userList.get(i).getApi_token(),
                                    userList.get(i).getFoto()
                            );
                            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                            Spf.getmInstance(MainActivity.this).userLogin(userinfo);
                            startActivity(new Intent(MainActivity.this, Beranda.class));
                            finish();
                        }
                    }

                }else{
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Respon_login> call, Throwable t) {
                Log.e("error",String.valueOf(t));
                dialog.dismiss();
                Toast.makeText(MainActivity.this, String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
