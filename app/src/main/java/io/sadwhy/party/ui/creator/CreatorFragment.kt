package io.sadwhy.party.ui.creator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil3.load
import com.google.android.material.appbar.AppBarLayout
import io.sadwhy.party.R
import io.sadwhy.party.data.model.Post
import io.sadwhy.party.databinding.CreatorFragmentBinding
import io.sadwhy.party.utils.AutoClearedValue.Companion.autoCleared
import io.sadwhy.party.utils.Serializer.getSerialized
import io.sadwhy.party.utils.Serializer.putSerialized
import io.sadwhy.party.ui.utils.AnimationUtils.showTitleOnCollapse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CreatorFragment : Fragment(R.layout.creator_fragment) {

    companion object {
        fun getBundle(post: Post) = Bundle().apply {
            putSerialized("post", post)
        }
    }

    private val args by lazy { requireArguments() }
    private val post by lazy { args.getSerialized<Post>("post")!! }

    private var binding: CreatorFragmentBinding by autoCleared()
    private val viewModel: CreatorViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CreatorFragmentBinding.bind(view)

        viewModel.fetchCreator(post.service, post.user)

        with(binding) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.creator.collectLatest { creator ->
                        val name = creator?.name ?: post.user
                        username.text = name
                        collapsingToolbar.title = name
                    }
                }
            }

            profileImage.load("https://img.kemono.su/icons/${post.service}/${post.user}")
            bannerImage.load("https://img.kemono.su/banners/${post.service}/${post.user}")

            listOf("Posts", "Media", "About").forEach {
                tabLayout.addTab(tabLayout.newTab().setText(it))
            }

            showTitleOnCollapse(appbarlayout, collapsingToolbar)
        }
    }
}