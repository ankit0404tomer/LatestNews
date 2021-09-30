package com.example.latestnews.ui.newslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latestnews.R
import com.example.latestnews.network.rx.ResponseState
import com.example.latestnews.ui.adapter.NewsListAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.news_list_fragment.*
import javax.inject.Inject


class NewsListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var adapter: NewsListAdapter
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(NewsListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNewsList.observe(viewLifecycleOwner, {
            when (it) {

                is ResponseState.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is ResponseState.Success -> {
                    progress_bar.visibility = View.GONE
                    adapter = NewsListAdapter(it.response.newsList)
                    recycler_view.adapter = adapter
                    recycler_view.layoutManager = LinearLayoutManager(
                        activity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    newsItemClick()

                }
                is ResponseState.Error -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun newsItemClick() {
        adapter.getSelectedNewsitem.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_newsListFragment_to_detailNewsFragment, viewModel.createArguments(it))
        })
    }

}
