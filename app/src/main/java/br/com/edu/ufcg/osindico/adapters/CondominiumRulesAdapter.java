package br.com.edu.ufcg.osindico.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by emanoel on 02/03/17.
 */

public class CondominiumRulesAdapter extends RecyclerView.Adapter<CondominiumRulesAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
