package app.sharma.searchview2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<Places> arrayList = new ArrayList<>();

    public SearchAdapter(Context context, ArrayList<Places> arrayList) {
        this.context =  context;
        this.arrayList = arrayList;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{

        TextView profileImage;
        TextView name, description;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = (TextView) itemView.findViewById(R.id.profile_image);
            name = (TextView) itemView.findViewById(R.id.name_text);
            description = (TextView) itemView.findViewById(R.id.description_text);
        }
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.profileImage.setText(arrayList.get(position).getImage());
        switch (arrayList.get(position).getImage().toString()){
            case "HOTEL": holder.profileImage.setBackgroundColor(ContextCompat.getColor(context, R.color.pink));
                holder.profileImage.setTextColor(ContextCompat.getColor(context, R.color.darkpink));
            break;
            case "PLACE": holder.profileImage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
                holder.profileImage.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                break;
            case "VILLA": holder.profileImage.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow));
                holder.profileImage.setTextColor(ContextCompat.getColor(context, R.color.darkyellow));
                break;
        }
        holder.description.setText(arrayList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
