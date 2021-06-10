package com.example.demowatchs;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends Activity {


    EditText email, password;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email = findViewById(R.id.editTextTextPersonName6);//Поле ввода логина
        password = findViewById(R.id.editTextTextPersonName8);//Поле ввода пароля
        btnLogin = findViewById(R.id.buttonffff);//Кнопка "вход"


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    ShowDialogWindow("Требуется логин или пароль");
                } else {
                    login();
                }
            }
        });

    }
    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email.getText().toString());
        loginRequest.setPassword(password.getText().toString());
        Call<com.example.demowatchs.LoginResponse> loginResponseCall = APIclient.getUserService().userLogin(loginRequest);//Сформировать запрос
        loginResponseCall.enqueue(new Callback<com.example.demowatchs.LoginResponse>() {//Вызвать запрос
            @Override
            //проверка данных

            public void onResponse(Call<com.example.demowatchs.LoginResponse> call, Response<com.example.demowatchs.LoginResponse> response) {
                if(response.isSuccessful()){        //Данные верны
                    Toast.makeText(com.example.demowatchs.MainActivity.this, "Добро пожаловать", Toast.LENGTH_LONG).show();//Сообщение о входе в приложение
                    Intent intent;
                    intent = new Intent(com.example.demowatchs.MainActivity.this, MainActivity2.class);//Переход на следущий экран
                    startActivity(intent);
                }else {     //данные не верны
                    ShowDialogWindow("Не правильный логин или пароль");
                }
            }

            @Override
            public void onFailure(Call<com.example.demowatchs.LoginResponse> call, Throwable t) { //Ошибка доступа к серверу
                Toast.makeText(com.example.demowatchs.MainActivity.this, "Throwable"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();////Сообщение об ошибке со стороны сервера или приложения

            }
        });
    }

    public void ShowDialogWindow(String text){
        final AlertDialog aboutDialog = new AlertDialog.Builder(MainActivity.this).setMessage(text)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();
        aboutDialog.show();
    }
}


