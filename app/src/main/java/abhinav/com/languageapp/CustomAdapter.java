package abhinav.com.languageapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
{
    ArrayList<SentenceBean> arrayListWords;
    Context context;
    int recycler_id;

    public CustomAdapter(Context context, ArrayList<SentenceBean> arrayListWords,
                         int recycler_id)
    {
        this.context = context;
        this.arrayListWords = arrayListWords;
        this.recycler_id = recycler_id;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position)
    {

        holder.btn_word.setText(""+arrayListWords.get(position).getOder());
        // implement setOnClickListener event on item view.
        holder.btn_word.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // display a toast with person name on item click
                Toast.makeText(context, ""+arrayListWords.get(position).getOder(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, ""+personImages.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return arrayListWords.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        Button btn_word;// init the item view's
        public MyViewHolder(View itemView)
        {
            super(itemView);
            // get the reference of item view's
            btn_word=(Button)itemView.findViewById(R.id.btn_word);
        }
    }

}
