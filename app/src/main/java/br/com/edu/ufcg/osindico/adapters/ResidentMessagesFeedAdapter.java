package br.com.edu.ufcg.osindico.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.Utils.ItemClickListener;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;


public class ResidentMessagesFeedAdapter extends RecyclerView.Adapter<ResidentMessagesFeedAdapter.ViewHolder> {

    private List<MessageResponse> messageResponses;
    private ItemClickListener clickListener;

    public ResidentMessagesFeedAdapter(List<MessageResponse> responses) {
        this.messageResponses = responses;
    }

    @Override
    public ResidentMessagesFeedAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.resident_notification_card_row,
                viewGroup, false);
        return new ViewHolder(view);
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(ResidentMessagesFeedAdapter.ViewHolder viewHolder, int i) {
        String message = messageResponses.get(i).getMessage();
        viewHolder.tv_notification.setText(message);
        viewHolder.tv_date.setText("06/03/2017");
    }

    @Override
    public int getItemCount() {
        return messageResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_notification, tv_date;

        public ViewHolder(View view) {
            super(view);

            tv_notification = (TextView) view.findViewById(R.id.tv_notification);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(messageResponses.get(getAdapterPosition()));
        }
    }
}