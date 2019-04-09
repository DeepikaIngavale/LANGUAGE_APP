package abhinav.com.languageapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class CustomAdapterAnswer extends RecyclerView.Adapter<CustomAdapterAnswer.MyViewHolder>
{
    ArrayList<SentenceBean> arrayListWords;
    Context context;
    OnRecyclerItemClicked listner;
    int recycler_id;

    public CustomAdapterAnswer(Context context, ArrayList<SentenceBean> arrayListWords,
                         int recycler_id,OnRecyclerItemClicked listner)
    {
        this.context = context;
        this.arrayListWords = arrayListWords;
        this.recycler_id = recycler_id;
        this.listner=listner;
    }

    @Override
    public CustomAdapterAnswer.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayoutans, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CustomAdapterAnswer.MyViewHolder vh = new CustomAdapterAnswer.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterAnswer.MyViewHolder holder, final int position)
    {

        holder.btn_word.setText(""+arrayListWords.get(position).getOder());
        // implement setOnClickListener event on item view.
        holder.btn_word.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listner.onRecyclerItemClicked(recycler_id,position,arrayListWords.get(position));
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
