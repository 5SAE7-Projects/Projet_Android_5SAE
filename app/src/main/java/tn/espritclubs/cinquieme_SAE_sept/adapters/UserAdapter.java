package tn.espritclubs.cinquieme_SAE_sept.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import tn.espritclubs.cinquieme_SAE_sept.R;
import tn.espritclubs.cinquieme_SAE_sept.models.User;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Context context, List<User> objects) {
        super(context, 0,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.usercell, parent, false);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.name);
            holder.role = convertView.findViewById(R.id.role);
            holder.image = convertView.findViewById(R.id.userimage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        User user = getItem(position);
        if (user != null) {
            holder.name.setText(user.getFirstname()+" "+user.getLastname());
            holder.role.setText(user.getRole());
            //holder.image.setImageResource(user.getProfilePicture());
            //holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //holder.image.setClipToOutline(true);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView role;
        ImageView image;
    }
}
