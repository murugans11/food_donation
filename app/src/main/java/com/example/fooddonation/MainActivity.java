package com.example.fooddonation;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.fooddonation.databinding.ActivityMainBinding;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements Listener {

    private LoginViewModel loginViewModel;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        binding.setClickListener((MainActivity) this);


        loginViewModel = new ViewModelProvider(MainActivity.this).get(LoginViewModel.class);

        loginViewModel.getGetAllData().observe(this, data -> {

            try {
                binding.lblEmailAnswer.setText((Objects.requireNonNull(data).get(0).getEmail()));
                binding.lblPasswordAnswer.setText((Objects.requireNonNull(data.get(0).getPassword())));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

    @Override
    public void onClick(View view) {

        String strEmail = binding.txtEmailAddress.getText().toString().trim();
        String strPassword = binding.txtPassword.getText().toString().trim();

        LoginTable data = new LoginTable();

        if (TextUtils.isEmpty(strEmail)) {
            binding.txtEmailAddress.setError("Please Enter Your E-mail Address");
        }
        else if (TextUtils.isEmpty(strPassword)) {
            binding.txtPassword.setError("Please Enter Your Password");
        }
        else {

            data.setEmail(strEmail);
            data.setPassword(strPassword);
            loginViewModel.insert(data);

        }

    }

}