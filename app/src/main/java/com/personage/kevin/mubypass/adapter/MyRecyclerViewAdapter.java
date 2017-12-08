package com.personage.kevin.mubypass.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.personage.kevin.mubypass.R;
import com.personage.kevin.mubypass.entity.GameOperator;

import java.util.List;

/**
 * Created by pc1 on 2016/10/18.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHoder> {
    List<GameOperator> mDataSet;

    /**
     * Item回调的接口
     */
    public interface OnItemClickListener{
        void onItemClickListener(View view, int position);
    }

    /**
     * 设置回调的监听
     */
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    private OnItemClickListener listener;//点击回调的对象

    public static class ViewHoder extends RecyclerView.ViewHolder{
        TextView mGameNameView;
        TextView mGameIntroView;
        TextView mGameAddressLinkView;

        public ViewHoder(View view){
            super(view);
        }
    }

    public MyRecyclerViewAdapter( List<GameOperator> mDataSet){
        this.mDataSet=mDataSet;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_list_item,viewGroup,false);
        ViewHoder hoder = new ViewHoder(view);
        hoder.mGameNameView = (TextView) view.findViewById(R.id.game_name);
        hoder.mGameIntroView = (TextView) view.findViewById(R.id.game_intro);
        hoder.mGameAddressLinkView = (TextView) view.findViewById(R.id.game_address_link);
        return hoder;
    }

    @Override
    public void onBindViewHolder(ViewHoder viewHoder, final int i) {
        GameOperator gameOperatorInfo = mDataSet.get(i);
        viewHoder.mGameNameView.setText(gameOperatorInfo.getOperatorName());
        viewHoder.mGameIntroView.setText(gameOperatorInfo.getOperatorIntro());
        viewHoder.mGameAddressLinkView.setText(gameOperatorInfo.getOperatorAddressLink());
        if (listener!=null){
            viewHoder.mGameIntroView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(v,i);
                }
            });
        }
    }
}
