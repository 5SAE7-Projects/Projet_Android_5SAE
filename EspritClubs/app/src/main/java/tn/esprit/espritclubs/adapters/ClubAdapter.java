package tn.esprit.espritclubs.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.entities.Club;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ClubViewHolder> {

    private List<Club> clubList;
    private OnItemClickListener onItemClickListener;

    // Modify constructor to accept the listener
    public ClubAdapter(List<Club> clubList, OnItemClickListener onItemClickListener) {
        this.clubList = clubList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club, parent, false);
        return new ClubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {
        Club club = clubList.get(position);
        holder.clubName.setText(club.getName());
        Picasso.get().load(club.getImageResId()).into(holder.clubImage);

        holder.reserveButton.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onReserveClicked(club); // Trigger the listener when the button is clicked
            }
        });
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }

    public static class ClubViewHolder extends RecyclerView.ViewHolder {
        public TextView clubName;
        public ImageView clubImage;
        public Button reserveButton;

        public ClubViewHolder(View itemView) {
            super(itemView);
            clubName = itemView.findViewById(R.id.club_name);
            clubImage = itemView.findViewById(R.id.club_image);
            reserveButton = itemView.findViewById(R.id.reserve_button);
        }
    }

    // Interface for item click listener
    public interface OnItemClickListener {
        void onReserveClicked(Club club);  // Method signature for item click
    }
}
