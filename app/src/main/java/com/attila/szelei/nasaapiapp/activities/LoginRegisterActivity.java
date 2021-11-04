package com.attila.szelei.nasaapiapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.attila.szelei.nasaapiapp.R;
import com.attila.szelei.nasaapiapp.model.LoginRequest;
import com.attila.szelei.nasaapiapp.model.LoginResponse;
import com.attila.szelei.nasaapiapp.service.RetrofitClient;
import com.attila.szelei.nasaapiapp.service.NodeRestService;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import com.rengwuxian.materialedittext.MaterialEditText;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginRegisterActivity extends AppCompatActivity {


    NodeRestService nodeRestService;
    TextView textViewCreateAcc;
    MaterialEditText loginEmail_edt, loginPassword_edt;
    Button btn_login;


    CompositeDisposable compositeDisposable =new CompositeDisposable();

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofitClient = RetrofitClient.getRetroFitClient();
        nodeRestService = retrofitClient.create(NodeRestService.class);

        //TextViews
        loginEmail_edt =findViewById(R.id.email_editText);
        loginPassword_edt =findViewById(R.id.password_editText);

        //Buttonlogin
        btn_login =findViewById(R.id.login_btn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser(loginEmail_edt.getText().toString(),loginPassword_edt.getText().toString());

            }
        });

        textViewCreateAcc =findViewById(R.id.create_acc_textView);
        textViewCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View register= LayoutInflater.from(LoginRegisterActivity.this).inflate(R.layout.register, null);


                new MaterialStyledDialog.Builder(LoginRegisterActivity.this)
                        .setIcon(R.drawable.ic_email)
                        .setTitle("REGISTRATION")
                        .setDescription("Please fill all fields")
                        .setCustomView(register)
                        .setNegativeText("CANCEL")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        }).setPositiveText("REGISTER")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            LoginRequest loginRequest = new LoginRequest();
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                MaterialEditText registerEmail_edt= register.findViewById(R.id.email_editText);
                                MaterialEditText registerPassword_edt= register.findViewById(R.id.password_editText);
                                if(TextUtils.isEmpty(registerEmail_edt.getText().toString())){
                                    Toast.makeText(LoginRegisterActivity.this, "Email address cannot be empty", Toast.LENGTH_SHORT).show();
                                    return ;
                                }
                                if(TextUtils.isEmpty(registerPassword_edt.getText().toString())){
                                    Toast.makeText(LoginRegisterActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                registerUser(registerEmail_edt.getText().toString(),registerPassword_edt.getText().toString());
                            }
                        }).show();

            }
        });



    }

    private void registerUser(String email, String password) {
        compositeDisposable.add(nodeRestService.signup(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        Toast.makeText(LoginRegisterActivity.this,""+response, Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void loginUser(String email, String password) {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email address cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
         else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }else {
             Call<LoginResponse> loginResponseCall = nodeRestService.login(loginRequest);
             loginResponseCall.enqueue(new Callback<LoginResponse>() {
                 @Override
                 public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                     if (response.isSuccessful()){
                         Toast.makeText(LoginRegisterActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                         new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                             @Override
                             public void run() {
                                 startActivity(new Intent(LoginRegisterActivity.this, NasaAPIActivity.class));
                             }
                         }, 300);

                     }else{
                         Toast.makeText(LoginRegisterActivity.this,
                                 "username/password was not correct", Toast.LENGTH_LONG).show();
                     }
                 }

                 @Override
                 public void onFailure(Call<LoginResponse> call, Throwable t) {
                     Toast.makeText(LoginRegisterActivity.this, "Error: "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                 }
             });
        }


    }

}