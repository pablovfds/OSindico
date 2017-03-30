package br.com.edu.ufcg.osindico.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;

public class DwellerServiceAdapter extends RecyclerView.Adapter<DwellerServiceAdapter.ViewHolder> {
    private List<ServiceRequestResponse> serviceRequestResponses;

    public DwellerServiceAdapter(List<ServiceRequestResponse> serviceRequestResponses) {
        this.serviceRequestResponses = serviceRequestResponses;
    }

    @Override
    public DwellerServiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dweller_service_row,
                parent, false);
        return new DwellerServiceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DwellerServiceAdapter.ViewHolder holder, int position) {
        ServiceRequestResponse response = serviceRequestResponses.get(position);

        if(serviceRequestResponses.get(position).isStatus()){
            holder.tv_status.setText("Resolvido");
            holder.tv_status.setBackgroundColor(Color.parseColor("#82bd82"));
        }else{
            holder.tv_status.setText("Pendente");
            holder.tv_status.setBackgroundColor(Color.parseColor("#fa6868"));
        }

        holder.tv_title_service.setText("Titulo: " + response.getTitle());
        holder.tv_description_service.setText("Descrição: " + response.getDescription());
        holder.tv_date_service.setText("Data da requisição: " + response.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return serviceRequestResponses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title_service;
        private TextView tv_description_service;
        private TextView tv_date_service;
        private TextView tv_status;

        ViewHolder(View view) {
            super(view);

            tv_title_service = (TextView) view.findViewById(R.id.tv_title_service);
            tv_description_service = (TextView) view.findViewById(R.id.tv_description_service);
            tv_date_service = (TextView) view.findViewById(R.id.tv_date_service);
            tv_status = (TextView) view.findViewById(R.id.status);
        }
    }
}
