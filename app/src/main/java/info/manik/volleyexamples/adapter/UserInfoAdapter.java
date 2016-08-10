package info.manik.volleyexamples.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import info.manik.volleyexamples.R;
import info.manik.volleyexamples.holder.UserInfoHolder;
import info.manik.volleyexamples.model.ItemObjects;

/**
 * Created by M anik on 04-08-2016.
 */
public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoHolder> {

private List<ItemObjects> itemList;
private Context context;

public UserInfoAdapter(Context context, List<ItemObjects> itemList) {
        this.itemList = itemList;
        this.context = context;
        }

@Override
public UserInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.userinfo_item, null);
    UserInfoHolder rcv = new UserInfoHolder(layoutView);
        return rcv;
        }

@Override
public void onBindViewHolder(UserInfoHolder holder, int position) {
        holder.countryName.setText(itemList.get(position).getName());
     //   holder.countryPhoto.setImageResource(itemList.get(position).getPhoto());
        holder.countryPhoto.setImageBitmap(itemList.get(position).getPhoto());
        }

@Override
public int getItemCount() {
        return this.itemList.size();
        }
        }