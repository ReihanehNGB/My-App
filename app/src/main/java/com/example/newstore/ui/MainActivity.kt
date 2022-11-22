package com.example.newstore.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.SimpleAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.newstore.R
import com.example.newstore.SwipeToDeleteCallback
import com.example.newstore.databinding.ActivityMainBinding
import com.example.newstore.interfaces.RecyclerAdapterListener
import com.example.newstore.model.ProductM
import com.example.newstore.ui.cart.CartFragment
import com.example.newstore.ui.category.CategoryFragment
import com.example.newstore.ui.favorite.FavoriteFragment
import com.example.newstore.ui.home.HomeFragment
import com.example.newstore.ui.home.HomeVM
import com.example.newstore.ui.profile.ProfileFragment
import com.example.newstore.ui.search.SearchActivity
import com.example.newstore.utils.ViewPagerAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var homrfragment: HomeFragment
    lateinit var cartfragment: CartFragment
    lateinit var categoryfragment: CategoryFragment
    lateinit var profilefragment: ProfileFragment
    lateinit var favoritfragment: FavoriteFragment
    var mAdapter: RecyclerAdapterListener? = null
    lateinit var searchActivity: SearchActivity
    private val homeVM: HomeVM by viewModels()
    var stateIcon: String = ""
    var value: String = ""
    private var animate = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)

        initBinding(R.layout.activity_main)



        value = intent.extras?.getString("SingleU").toString()


        homrfragment = HomeFragment.newInstance()
        cartfragment = CartFragment.newInstance()
        categoryfragment = CategoryFragment.newInstance()
        favoritfragment = FavoriteFragment.newInstance()

        profilefragment = ProfileFragment.newInstance()

        setupViewPager()

        listener()








    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }


    private fun initBinding(layout: Int) {
        binding = DataBindingUtil.setContentView(this, layout)
        binding.presenter = this

    }

    @SuppressLint("ResourceType", "UseCompatLoadingForDrawables")
    private fun listener() {

        binding.icHome.setOnClickListener {
//            loadGif(R.drawable.home_animate,binding.icHome)
            binding.viewPager.currentItem = 0
            stateIcon = "SelectedHome"
            binding.state = stateIcon
        }

        binding.icFavorite.setOnClickListener {
//            loadGif(R.drawable.favorite_animate,binding.icFavorite)
            binding.viewPager.currentItem = 1
            stateIcon = "SelectedFavorite"
            binding.state = stateIcon
        }

        binding.icCategory.setOnClickListener {
//            loadGif(R.drawable.category_animate,binding.icCategory)
            binding.viewPager.currentItem = 2
            stateIcon = "SelectedCategory"
            binding.state = stateIcon
        }



        binding.icBasket.setOnClickListener {
//            loadGif(R.drawable.basket_animate,binding.icBasket)
            binding.viewPager.currentItem = 3
            stateIcon = "SelectedBasket"
            binding.state = stateIcon
        }

        binding.icProfile.setOnClickListener {
//            loadGif(R.drawable.profile_animate,binding.icProfile)
            binding.viewPager.currentItem = 4
            stateIcon = "SelectedProfile"
            binding.state = stateIcon
            profilefragment.userID = value
        }

    }

    private fun setupViewPager() {
        val mAdapter = ViewPagerAdapter(supportFragmentManager)
        mAdapter.apply {
            addFragment(homrfragment)
            addFragment(favoritfragment)
            addFragment(categoryfragment)
            addFragment(cartfragment)
            addFragment(profilefragment)


        }
        binding.viewPager.apply {
            adapter = mAdapter
            offscreenPageLimit = mAdapter.count
            currentItem = 0
        }
    }

    fun selectCart(productModelC: ProductM) {
        cartfragment.updateList(productModelC)
    }

    fun selectFav(productModelF: ProductM) {
        favoritfragment.update(productModelF)

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(productM: ProductM) {
        selectCart(productM)
        // Do something
    }












}