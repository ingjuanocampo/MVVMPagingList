package juanocampo.myapplication.com.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import juanocampo.myapplication.com.R
import juanocampo.myapplication.com.utils.delegate.DelegateAdapter
import juanocampo.myapplication.com.utils.delegate.model.RecyclerViewType

class LoaderDelegateAdapter : DelegateAdapter<LoaderDelegateAdapter.ViewHolder, RecyclerViewType> {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, viewType: RecyclerViewType) {
    }

    class ViewHolder(viewGroup: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.loader_item, viewGroup, false))
}