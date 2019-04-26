package juanocampo.myapplication.com.utils.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import juanocampo.myapplication.com.utils.delegate.model.RecyclerViewType

interface DelegateAdapter<VH : RecyclerView.ViewHolder, VT : RecyclerViewType> {

    fun onCreateViewHolder(parent: ViewGroup): VH

    fun onBindViewHolder(viewHolder: VH, viewType: VT)
}