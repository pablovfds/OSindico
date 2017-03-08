package br.com.edu.ufcg.osindico.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.RuleResponse;

/**
 * Created by emanoel on 02/03/17.
 */

public class CondominiumRulesAdapter extends RecyclerView.Adapter<CondominiumRulesAdapter.ViewHolder> {

    private List<RuleResponse> rules;

    public CondominiumRulesAdapter(List<RuleResponse> rules) {
        this.rules = rules;
    }

    @Override
    public CondominiumRulesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rule_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CondominiumRulesAdapter.ViewHolder holder, int position) {
        String rule = rules.get(position).getRule();
        holder.tv_rule.setText(rule);
    }

    @Override
    public int getItemCount() {
        return rules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_rule;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_rule = (TextView) itemView.findViewById(R.id.tv_rule);
        }
    }
}
