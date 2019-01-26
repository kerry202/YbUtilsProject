package com.youyicheng.KaoLiao.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;

import java.util.List;

public class OrderDetailsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Activity activity;

    public OrderDetailsAdapter(Activity activity, @Nullable List<String> data) {
        super(R.layout.order_details_adapter_layout, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        TextView order_details_tv = helper.getView(R.id.order_details_tv);
        TextView private_msg = helper.getView(R.id.private_msg);


        order_details_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        private_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void showDialog() {

        View view = LayoutInflater.from(activity).inflate(R.layout.show_logistics_dialog, null);

        final Dialog dialog = new AlertDialog.Builder(activity, R.style.add_photo_dialog)
                .setView(view)
                .setCancelable(true)
                .create();
        dialog.show();

        Window win = dialog.getWindow();
        win.getDecorView().setPadding(50, 0, 50, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
    }
}
