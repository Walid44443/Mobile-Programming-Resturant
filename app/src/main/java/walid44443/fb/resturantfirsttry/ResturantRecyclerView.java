package walid44443.fb.resturantfirsttry;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by walid4444 on 11/23/17.
 */

public class ResturantRecyclerView extends RecyclerView.Adapter<ResturantRecyclerView.ResturantViewHolder> {

    List<ResturantModel> resturantModelList;
    Context mContext;

    @Override
    public ResturantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.resturant_item_view,parent,false);
        ResturantViewHolder holder = new ResturantViewHolder(row);
        mContext=parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(final ResturantViewHolder holder, final int position) {
        //holder.icon.setImaresturantModelList.get(position).getIco();
        holder.Res_Name.setText(resturantModelList.get(position).getName());
        holder.Element.setTag(resturantModelList.get(position).getId());
        holder.Element.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),ResturantLocationActivity.class);
                i.putExtra("id", resturantModelList.get(position).getId()+"");
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.resturantModelList.size();
    }

    public ResturantRecyclerView(List<ResturantModel> resturantModelList) {
        this.resturantModelList = resturantModelList;
    }



    class ResturantViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView Res_Name;
        LinearLayout Element;
        public ResturantViewHolder(View v) {
            super(v);
            icon = v.findViewById(R.id.res_icon);
            Res_Name = v.findViewById(R.id.res_name);
            Element = v.findViewById(R.id.element);
        }
    }
}
