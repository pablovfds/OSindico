package br.com.edu.ufcg.osindico.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.Utils.ItemClickListener;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;

public class RequestDwellerAdapter extends RecyclerView.Adapter<RequestDwellerAdapter.ViewHolder> {
    private List<DwellerResponse> android;
    private ItemClickListener clickListener;

    public RequestDwellerAdapter(List<DwellerResponse> android) {
        this.android = android;
    }

    @Override
    public RequestDwellerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.resident_card_row,
                viewGroup, false);
        return new ViewHolder(view);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(RequestDwellerAdapter.ViewHolder viewHolder, int i) {
        String name = "Nome: " + android.get(i).getName();
        String email = "Email: " + android.get(i).getEmail();
        viewHolder.tv_name.setText(name);
        viewHolder.tv_email.setText(email);
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_name, tv_email;

        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_email = (TextView) view.findViewById(R.id.tv_email);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(android.get(getAdapterPosition()));
        }
    }
}