package com.god.gl.vaccination.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;

import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author gl
 * @date 2018/12/4
 * @desc
 */
public abstract class  BaseFragment extends Fragment {

    private String tag = "";
    // 视图化的对象
    protected View rootView;
    protected Context mContext;
    protected LayoutInflater inflater;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.inflater = LayoutInflater.from(context);
        try {
            mContext = (BaseActivity) context;
        } catch (Exception e) {
            throw new IllegalArgumentException("这个fragment的父activity必须继承BaseActivity");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (rootView = inflater.inflate(getFragmentViewId(savedInstanceState), container, false));
    }


    public abstract void initView(View rootView);

    public abstract int getFragmentViewId(Bundle savedInstanceState);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initView(view);
    }

    public String getTAG() {
        try {
            if ("".equals(tag)) {
                tag = getClass().getSimpleName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tag;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(getTAG());


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }




}
