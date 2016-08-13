package mx.hercarr.chatsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.hercarr.chatsapp.R;
import mx.hercarr.chatsapp.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private  Context context;
    private List<User> users;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = (users != null) ? users : new ArrayList<User>();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_item_view, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bindUser(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void reload(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    protected class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgProfile)
        ImageView imgProfile;
        @BindView(R.id.lblName)
        TextView lblName;
        @BindView(R.id.lblAddress)
        TextView lblAddress;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindUser(User user) {
            loadPicture(user.getPicture().getLarge());
            lblName.setText(user.getName().getFullName());
            lblAddress.setText(user.getLocation().getAddress());
        }

        private void loadPicture(String pictureUrl) {
            Glide.with(context)
                    .load(pictureUrl)
                    .centerCrop()
                    .error(android.R.drawable.sym_def_app_icon)
                    .crossFade()
                    .into(imgProfile);
        }

    }

}