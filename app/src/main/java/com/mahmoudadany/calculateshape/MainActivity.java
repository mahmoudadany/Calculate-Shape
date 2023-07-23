package com.mahmoudadany.calculateshape;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText rectangle_height,rectangle_width,circle_radius,triangle_base,triangle_height;
LinearLayout rectangle,triangle;
Spinner spinner;
Button calculate;
TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflat();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        rectangle.setVisibility(View.GONE);
                        triangle.setVisibility(View.GONE);
                        circle_radius.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "please select any shape", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        rectangle.setVisibility(View.VISIBLE);
                        triangle.setVisibility(View.GONE);
                    circle_radius.setVisibility(View.GONE);
                        break;
                    case 2:
                        rectangle.setVisibility(View.GONE);
                        triangle.setVisibility(View.GONE);
                        circle_radius.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        rectangle.setVisibility(View.GONE);
                        triangle.setVisibility(View.VISIBLE);
                        circle_radius.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void inflat() {
        spinner=findViewById(R.id.main_spinner_shapetype);
        calculate=findViewById(R.id.main_bt_calculate);
        calculate.setOnClickListener(this);
        result=findViewById(R.id.main_tv_result);
        rectangle=findViewById(R.id.main_layout_rectangle);
        triangle=findViewById(R.id.main_layout_triangle);
        rectangle_height=findViewById(R.id.main_ed_rectangle_height);
        rectangle_width=findViewById(R.id.main_ed_rectangle_width);
        circle_radius=findViewById(R.id.main_ed_circle_radius);
        triangle_base=findViewById(R.id.main_ed_triangle_base);
        triangle_height=findViewById(R.id.main_ed_triangle_height);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.main_bt_calculate){
            calculator();
        }
    }

    private void calculator() {
        if(rectangle.getVisibility()==View.VISIBLE){
            if (CheckNotNull(rectangle_height.getText().toString())||CheckNotNull(rectangle_width.getText().toString())){
                Toast.makeText(getBaseContext(), "empty value", Toast.LENGTH_SHORT).show();
                return;
            }
            Double height=Double.parseDouble(rectangle_height.getText().toString());
            Double width=Double.parseDouble(rectangle_width.getText().toString());
            Double final_result=getResult(height,width);
            result.setText(final_result.toString());
            return;
        }
        if(triangle.getVisibility()==View.VISIBLE){
            if (CheckNotNull(triangle_height.getText().toString())||CheckNotNull(triangle_base.getText().toString())){
                Toast.makeText(getBaseContext(), "empty value", Toast.LENGTH_SHORT).show();
                return;
            }
            Double height=Double.parseDouble(triangle_height.getText().toString());
            Double base=Double.parseDouble(triangle_base.getText().toString());
            Double final_result=getTriangleResult(base,height);
            result.setText(final_result.toString());
            return;
        }
        if(circle_radius.getVisibility()==View.VISIBLE){
            if (CheckNotNull(circle_radius.getText().toString())){
                Toast.makeText(getBaseContext(), "empty value", Toast.LENGTH_SHORT).show();
                return;
            }
            Double radius=Double.parseDouble(circle_radius.getText().toString());
            Double final_result=getResult(radius);
            result.setText(final_result.toString());
            return;
        }
        Toast.makeText(getBaseContext(), "please select any shape", Toast.LENGTH_SHORT).show();
    }

    private Boolean CheckNotNull(String check) {
        return check.isEmpty();
    }

    private double getResult(double height,double width) {
        return Double.valueOf(height*width);
    }
    private double getResult(double radius) {
        return  (3.14*(radius*radius));
    }
    private double getTriangleResult(double base,double height) {
        return  (0.5*base)*height;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("result",result.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
       String result1=(String) savedInstanceState.get("result");
       result.setText(result1);
    }
}