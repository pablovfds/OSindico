package br.com.edu.ufcg.osindico.adapters;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.Utils.ItemClickListener;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;

public class ServiceRequestAdapter extends RecyclerView.Adapter<ServiceRequestAdapter.ViewHolder>{

    private List<ServiceRequestResponse> serviceRequestResponses;
    private ItemClickListener clickListener;

    public ServiceRequestAdapter(List<ServiceRequestResponse> serviceRequestResponses) {
        this.serviceRequestResponses = serviceRequestResponses;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_request_row,
                parent, false);
        return new ServiceRequestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = "Problema: " + serviceRequestResponses.get(position).getTitle();
        //String sender = "De: " + serviceRequestResponses.get(position).getSender();
        String sender = "De: Morador 1";
        String location = "Local: Apt 18";
        String description = "Descrição: " + serviceRequestResponses.get(position).getDescription();
        String status = "teste";
        
        if(serviceRequestResponses.get(position).isStatus()){
            status = "Resolvido";
            holder.tv_status.setBackgroundColor(Color.parseColor("#82bd82"));
        }else{
            status = "Pendente";
            holder.tv_status.setBackgroundColor(Color.parseColor("#fa6868"));
        }

        holder.tv_status.setText(status);
        holder.tv_title_service.setText(title);
        holder.tv_sender_service.setText(sender);
        holder.tv_sender_location.setText(location);
        holder.tv_description_service.setText(description);
    }

    @Override
    public int getItemCount() {
        return serviceRequestResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_title_service;
        private TextView tv_sender_service;
        private TextView tv_sender_location;
        private TextView tv_description_service;
        private ImageButton btn_update_service_status;
        private TextView tv_status;

        public ViewHolder(View view) {
            super(view);

            tv_title_service = (TextView) view.findViewById(R.id.tv_title_service);
            tv_sender_service = (TextView) view.findViewById(R.id.tv_sender_service);
            tv_sender_location = (TextView) view.findViewById(R.id.tv_service_location);
            tv_description_service = (TextView) view.findViewById(R.id.tv_description_service);
            btn_update_service_status = (ImageButton) view.findViewById(R.id.btn_update_service_status);
            tv_status = (TextView) view.findViewById(R.id.status);
            btn_update_service_status.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(serviceRequestResponses.get(getAdapterPosition()));
        }
    }
}
