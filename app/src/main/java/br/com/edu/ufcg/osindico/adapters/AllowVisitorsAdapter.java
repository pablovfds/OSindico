package br.com.edu.ufcg.osindico.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.models.VisitorDetails;

/**
 * Created by emanoel on 29/03/17.
 */

public class AllowVisitorsAdapter extends RecyclerView.Adapter<AllowVisitorsAdapter.ViewHolder> {

    private List<VisitorDetails> visitors;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.visitor_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = visitors.get(position).getName();
        String cpf = visitors.get(position).getCpf();
        holder.tv_name.setText(name);
        holder.tv_cpf.setText(cpf);
    }

    @Override
    public int getItemCount() {
        return visitors.size();
    }

    public void addVisitor(VisitorDetails visitor) {
        visitors.add(visitor);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_cpf;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_visitor_name);
            tv_cpf = (TextView) itemView.findViewById(R.id.tv_visitor_cpf);
        }
    }
}
