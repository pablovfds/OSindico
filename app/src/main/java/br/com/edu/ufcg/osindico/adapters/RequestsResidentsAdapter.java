package br.com.edu.ufcg.osindico.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ResidentResponse;

public class RequestsResidentsAdapter extends RecyclerView.Adapter<RequestsResidentsAdapter.ViewHolder> {
    private List<ResidentResponse> android;

    public RequestsResidentsAdapter(List<ResidentResponse> android) {
        this.android = android;
    }

    @Override
    public RequestsResidentsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.resident_card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RequestsResidentsAdapter.ViewHolder viewHolder, int i) {
        String name = "Nome: " + android.get(i).getName();
        String email = "Email: " + android.get(i).getEmail();
        viewHolder.tv_name.setText(name);
        viewHolder.tv_email.setText(email);
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_email;

        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_email = (TextView) view.findViewById(R.id.tv_email);

        }
    }
}