package com.jinkan.www.fastandroid.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.model.repository.NetWorkState;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.functions.Function0;

import static com.jinkan.www.fastandroid.model.repository.Status.FAILED;
import static com.jinkan.www.fastandroid.model.repository.Status.RUNNING;

/**
 * Created by Sampson on 2019/3/18.
 * FastAndroid
 */
class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {
    private ProgressBar progressBar;
    private Button retry;
    private TextView errorMsg;

    private NetworkStateItemViewHolder(@NonNull View itemView, @NonNull Function0 reTryCallback) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar);
        retry = itemView.findViewById(R.id.retry_button);
        errorMsg = itemView.findViewById(R.id.error_msg);
        retry.setOnClickListener(v -> reTryCallback.invoke());


    }

    void bindTo(@NonNull NetWorkState netWorkState) {
        progressBar.setVisibility(toVisibility(netWorkState.getStatus() == RUNNING));
        retry.setVisibility(toVisibility(netWorkState.getStatus() == FAILED));
        errorMsg.setVisibility(toVisibility(netWorkState.getMsg() != null));
        String msg = netWorkState.getMsg();
        if (msg != null)
            errorMsg.setText(msg);
    }

    private int toVisibility(boolean b) {
        return b ? View.VISIBLE : View.GONE;
    }

    public static NetworkStateItemViewHolder create(ViewGroup parent, Function0 reTryCallback) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.network_state_item, parent, false);
        return new NetworkStateItemViewHolder(view, reTryCallback);
    }

}
