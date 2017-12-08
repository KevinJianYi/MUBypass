package com.personage.kevin.mubypass.Fragment.classify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.personage.kevin.mubypass.Fragment.BaseFragment;
import com.personage.kevin.mubypass.R;

/**
 * Created by pc1 on 2016/10/19.
 * 技能类 管理碎片
 */

public class SkillFragment extends BaseFragment {

    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.armor_content_layout,container,false);

        return mRootView;
    }
}
