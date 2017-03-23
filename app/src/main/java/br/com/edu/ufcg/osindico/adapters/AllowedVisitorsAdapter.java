package br.com.edu.ufcg.osindico.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.RuleResponse;



public class AllowedVisitorsAdapter extends RecyclerView.Adapter<AllowedVisitorsAdapter.ViewHolder>{

    public AllowedVisitorsAdapter() {
    }

    @Override
    public AllowedVisitorsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.visitor_row, parent, false);
        return new AllowedVisitorsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() { return 0; }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_visitor_name;
        private TextView tv_visitor_cpf;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_visitor_name = (TextView) itemView.findViewById(R.id.tv_visitor_name);
            tv_visitor_cpf = (TextView) itemView.findViewById(R.id.tv_visitor_cpf);

        }
    }

}
