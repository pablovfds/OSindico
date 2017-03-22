package br.com.edu.ufcg.osindico.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;

public class ServiceRequestAdapter extends RecyclerView.Adapter<ServiceRequestAdapter.ViewHolder>{
    private List<ServiceRequestResponse> serviceRequestResponses;

    public ServiceRequestAdapter(List<ServiceRequestResponse> serviceRequestResponses) {
        this.serviceRequestResponses = serviceRequestResponses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_request_row,
                parent, false);
        return new ServiceRequestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = "Titulo: " + serviceRequestResponses.get(position).getTitle();
        holder.tv_title_service.setText(title);
    }

    @Override
    public int getItemCount() {
        return serviceRequestResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title_service;

        public ViewHolder(View view) {
            super(view);

            tv_title_service = (TextView) view.findViewById(R.id.tv_title_service);
        }

    }
}
