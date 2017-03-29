package br.com.edu.ufcg.osindico.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.RuleResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.VisitorResponse;
import br.com.edu.ufcg.osindico.data.models.VisitorDetails;


public class AllowedVisitorsAdapter extends BaseExpandableListAdapter {

    private List<VisitorResponse> visitorsList;
    private Context context;

    public AllowedVisitorsAdapter(Context context, List<VisitorResponse> visitorsList) {
        this.visitorsList = visitorsList;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return visitorsList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return visitorsList.get(groupPosition).getVisitorDetailsList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return visitorsList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return visitorsList.get(groupPosition).getVisitorDetailsList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        VisitorResponse visitorResponse = (VisitorResponse) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.visitors_header_row, null);
        }

        TextView heading = (TextView) convertView.findViewById(R.id.heading);
        heading.setText(visitorResponse.getNameDweller().trim());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

        VisitorDetails visitorDetails = (VisitorDetails) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.visitors_header_row, null);
        }

        TextView tv_visitor_name = (TextView) convertView.findViewById(R.id.tv_visitor_name);
        TextView tv_visitor_cpf = (TextView) convertView.findViewById(R.id.tv_visitor_cpf);
        tv_visitor_name.setText(visitorDetails.getName().trim());
        tv_visitor_cpf.setText(visitorDetails.getCpf().trim());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
