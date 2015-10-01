package com.deepakbaliga.profileui.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.deepakbaliga.profileui.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by deezdroid on 01/10/15.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoVH> {

    private Context context;
    private ArrayList<String> URLs;

    public PhotoAdapter(Context context, ArrayList<String> URLs) {
        this.context = context;
        this.URLs = URLs;
    }

    @Override
    public PhotoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_square_image, parent, false);
        PhotoVH photoVH = new PhotoVH(view);
        return photoVH;
    }

    @Override
    public void onBindViewHolder(PhotoVH holder, int position) {

        Picasso.with(context)
                .load(URLs.get(position))
                .resize(300, 300)
                .centerInside()
                .into(holder.getSquareImage());

    }

    @Override
    public int getItemCount() {
        return URLs.size();
    }

    class PhotoVH extends RecyclerView.ViewHolder{

        private ImageView squareImage;

        public PhotoVH(View itemView) {
            super(itemView);

            squareImage = (ImageView) itemView.findViewById(R.id.square_image);

        }

        public ImageView getSquareImage() {
            return squareImage;
        }

        public void setSquareImage(ImageView squareImage) {
            this.squareImage = squareImage;
        }
    }
}
