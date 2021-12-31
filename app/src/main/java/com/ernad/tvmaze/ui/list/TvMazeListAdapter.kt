package com.ernad.tvmaze.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ernad.tvmaze.databinding.SingleRowBinding
import com.ernad.tvmaze.ui.list.movieListObject.MazeObject

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class TvMazeListAdapter(private val viewModel: ListViewModel) :
    ListAdapter<MazeObject, TvMazeListAdapter.TvMazeViewHolder>(DiffCallback) {

    /**
     * The MarsPhotosViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [MarsPhoto] information.
     */
    class TvMazeViewHolder(
        private var binding: SingleRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: ListViewModel, mazeObject: MazeObject) {

            binding.viewModel = viewModel
            //load image in single row item
            binding.photo = mazeObject
            //load text data in name and date
            binding.rowName.text = mazeObject.show!!.name
            binding.rowDate.text = mazeObject.show.premiered

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [MarsPhoto] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<MazeObject>() {
        override fun areItemsTheSame(oldItem: MazeObject, newItem: MazeObject): Boolean {
            return oldItem.show!!.id == newItem.show!!.id
        }

        override fun areContentsTheSame(oldItem: MazeObject, newItem: MazeObject): Boolean {
            return oldItem.show!!.image == newItem.show!!.image && oldItem.show.premiered == newItem.show.premiered && oldItem.show.name == newItem.show.name
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvMazeViewHolder {
        return TvMazeViewHolder(
            SingleRowBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: TvMazeViewHolder, position: Int) {
        val singleItem = getItem(position)
        holder.bind(viewModel, singleItem)


    }
}