package br.com.edu.ufcg.osindico.Utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.condominium_rules.ui.CondominiumRulesFragment;
import br.com.edu.ufcg.osindico.syndicMessages.ui.SyndicMessageFragment;

/**
 * Created by Cathy on 19/03/2017.
 */

public class BottombarManager {

    Activity context;
    BottomNavigationView bottomNavigationView;

    public BottombarManager(Activity context, BottomNavigationView bottomNavigationView){
        this.context = context;
        this.bottomNavigationView = bottomNavigationView;
    }

    public void onBottomItemSelect(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab_messages:
                        setFragment(new SyndicMessageFragment());
                        Log.e("novo morador", "novo morador");
                        break;
                    case R.id.tab_request_services:
                        //setFragment();
                        Toast.makeText(context, "rlista de moradores", Toast.LENGTH_SHORT).show();
                        Log.e("lista", "lista moradores");
                        break;
                    case R.id.tab_claims:
                        setFragment(new CondominiumRulesFragment());
                        Toast.makeText(context, "regras", Toast.LENGTH_SHORT).show();
                        Log.e("regras", "lista regras");
                        break;
                }
                return true;
            }
        });
    }

    private void setFragment(Fragment newFragment) {
        FragmentTransaction transaction = context.getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame_syndic, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
