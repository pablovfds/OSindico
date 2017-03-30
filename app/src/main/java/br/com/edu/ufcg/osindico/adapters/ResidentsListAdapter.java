package br.com.edu.ufcg.osindico.adapters;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.UserResponse;


public class ResidentsListAdapter extends RecyclerView.Adapter<ResidentsListAdapter.ViewHolder>{

    private List<DwellerResponse> responseList;

    public ResidentsListAdapter(List<DwellerResponse> residentsList) {
        this.responseList = residentsList;
        Log.e("bindview", responseList.get(0).getName());
    }

    @Override
    public ResidentsListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.resident_card_row,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResidentsListAdapter.ViewHolder holder, int pos) {
        Log.e("bindview test", responseList.get(pos).getName() + "pos" + pos);
      //  DwellerResponse response = responseList.get(pos);
        holder.tv_resident_name.setText(responseList.get(pos).getName());
        holder.tv_resident_email.setText(responseList.get(pos).getEmail());
        holder.tv_resident_apt.setText("");

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(Color.WHITE)
                .useFont(Typeface.DEFAULT)
                .fontSize(30) /* size in px */
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRound(responseList.get(pos).getName().substring(0,1), color);

        holder.iv_resident_photo.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() { return responseList.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        private TextView tv_resident_name;
        private TextView tv_resident_email;
        private TextView tv_resident_apt;
        private ImageView iv_resident_photo;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_resident_name = (TextView) itemView.findViewById(R.id.tv_resident_name);
            tv_resident_email = (TextView) itemView.findViewById(R.id.tv_resident_email);
            tv_resident_apt = (TextView) itemView.findViewById(R.id.tv_resident_apartment);
            iv_resident_photo = (ImageView) itemView.findViewById(R.id.resident_photo);
        }
    }

}
