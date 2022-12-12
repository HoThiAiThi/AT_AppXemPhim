package com.example.bt_cuoiky;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DangKy extends AppCompatActivity {
    private EditText edtUserName,edtEmail,edtPassWord,edtConfirmPassword,edtFullName,edtPhoneNumber;
    private Button btnSignIn,btnSignUp;
    boolean status=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edtUserName= (EditText) findViewById(R.id.edtUserName);
        edtEmail= (EditText) findViewById(R.id.edtEmail);
        edtPassWord= (EditText) findViewById(R.id.edtPassword);
        edtFullName= (EditText) findViewById(R.id.edtFullName);
        edtPhoneNumber= (EditText) findViewById(R.id.edtPhone);
        edtConfirmPassword= (EditText) findViewById(R.id.edtConfirmPassword);
        btnSignIn=(Button) findViewById(R.id.btnSignIn);
        btnSignUp=(Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status=false;
                if(edtUserName.getText().length()!=0&& edtEmail.getText().length()!=0
                        && edtPassWord.getText().length()!=0&&edtConfirmPassword.getText().length()!=0){
                    if(edtPassWord.getText().toString().equals(edtConfirmPassword.getText().toString())){
                        Cursor dataAccount = DangNhap.database.GetData("SELECT * FROM TaiKhoan");
                        while (dataAccount.moveToNext()){
                            String userName =dataAccount.getString(0);
                            String password =dataAccount.getString(1);
                            if(userName.equals(edtUserName.getText().toString())){
                                Toast.makeText(DangKy.this, "Ten tai khoan da co nguoi su dung", Toast.LENGTH_SHORT).show();
                                status=true;
                            }
                        }
                        if(!dataAccount.moveToNext() && !status ){

                            //Toast.makeText(DangKy.this, "Dang ki thanh cong", Toast.LENGTH_SHORT).show();
                            String regex= "^\\w+[a-z0-9]*@{1}\\w+mail.com$";
                            Pattern pattern =Pattern.compile(regex);
                            Matcher matcher =pattern.matcher(edtEmail.getText().toString());
                            String regexPhoneNumber= "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
                            Pattern patternPhoneNumber = Pattern.compile(regexPhoneNumber);
                            Matcher matcherPhoneNumber = patternPhoneNumber.matcher(edtPhoneNumber.getText().toString());
                            if(!matcher.find()){
                                Toast.makeText(DangKy.this, "Sai dinh dang mail", Toast.LENGTH_SHORT).show();

                            }else if(!matcherPhoneNumber.find()) {
                                Toast.makeText(DangKy.this, "Số điện thoại không hợp lệ !!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(DangKy.this, "Dang ki thanh cong ", Toast.LENGTH_SHORT).show();
                                status= true;
                                TaiKhoan tk = new TaiKhoan();
                                tk.setUserName(edtUserName.getText().toString());
                                tk.setPassword(edtPassWord.getText().toString());
                                tk.setFullname(edtFullName.getText().toString());
                                tk.setEmail(edtEmail.getText().toString());
                                tk.setPhone(edtPhoneNumber.getText().toString());
                                DangNhap.database.insertTaiKhoan(tk);
                                Intent intent = new Intent(DangKy.this, DangNhap.class);
                                startActivity(intent);
                            }

                        }

                    }else{
                        Toast.makeText(DangKy.this, "Mat Khau Khong Khop", Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(DangKy.this, "Ban can nhap day du thong tin!!", Toast.LENGTH_SHORT).show();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKy.this, DangNhap.class);
                startActivity(intent);
            }
        });

    }
}