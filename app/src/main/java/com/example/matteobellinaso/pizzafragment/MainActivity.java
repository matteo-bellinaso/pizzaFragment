package com.example.matteobellinaso.pizzafragment;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements HeadListener {


    PizzaMenuFragment first_fragment = new PizzaMenuFragment();
    PizzaArticleFragment second_fragment = new PizzaArticleFragment();
    public int position = 0;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            if(savedInstanceState == null) {

                fragmentTransaction.add(R.id.fragment_container, first_fragment);
                fragmentTransaction.commit();

            }
        }else{
            articleSelected(position);
        }


    }

    @Override
    public void articleSelected(int position) {

       String dettaglioPizza = Pizza.pizzaDetails[position];

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Bundle bundle = new Bundle();
            bundle.putString("posizione", dettaglioPizza);
            second_fragment.setArguments(bundle);

            fragmentTransaction.replace(R.id.fragment_container, second_fragment);

            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                //LAND
                PizzaArticleFragment pizzaArticleFragment = (PizzaArticleFragment) fragmentManager.findFragmentById(R.id.article_fragment);

                pizzaArticleFragment.updateView(dettaglioPizza);

            }

        }
    }
}
