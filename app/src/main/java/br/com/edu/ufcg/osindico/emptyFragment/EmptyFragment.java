package br.com.edu.ufcg.osindico.emptyFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.edu.ufcg.osindico.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cathy on 25/03/2017.
 */

public class EmptyFragment extends Fragment {

    @BindView(R.id.message)
    TextView tv_message;
    @BindView(R.id.title)
    TextView tv_title;
    String title, message;

    public EmptyFragment(){
        this.title = "Nada por aqui...";
        this.message = "Não temos nenhuma informação a ser exibida!";
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.e("strings", title + message + "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.empty_fragment, null);
        ButterKnife.bind(this, view);

        tv_title.setText(title);
        tv_message.setText(message);

        return view;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message){
        this.message = message;




    }
}
