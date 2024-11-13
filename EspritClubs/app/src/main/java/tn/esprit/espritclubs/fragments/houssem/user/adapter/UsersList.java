package tn.esprit.espritclubs.fragments.houssem.user.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.entities.User;

public class UsersList extends ArrayAdapter<User> {
    public UsersList(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
    }

    private Uri imageUri;

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        User champ = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shapeuser, parent, false);
        }
        TextView name = convertView.findViewById(R.id.textView);
        TextView roles = convertView.findViewById(R.id.textViewrole);
        TextView textViewgender = convertView.findViewById(R.id.textViewgender);

        ImageView champimage = convertView.findViewById(R.id.champimage);

        name.setText(champ.getFirstName()+" "+champ.getLastName());
        textViewgender.setText(champ.getGender());
        roles.setText(champ.getRole());

        //roles.setText(Arrays.toString(champ.getPositions()));
        //roles.setText(TextUtils.join(", ", champ.getRole()));
        //champimage.setImageURI(champ.getProfilePicture());

        return convertView;
    }


    private void loadProfileImage() {
//        String profileImageUri = currentUser.getProfilePicture();
//        if (profileImageUri != null && !profileImageUri.isEmpty()) {
//            imageUri = Uri.parse(profileImageUri);
//            mylogo.setImageURI(imageUri);
//        } else {
//            if ("male".equalsIgnoreCase(currentUser.getGender()))
//                mylogo.setImageResource(R.drawable.avatar);
//            if ("female".equalsIgnoreCase(currentUser.getGender()))
//                mylogo.setImageResource(R.drawable.avatara);
//        }
    }
}
