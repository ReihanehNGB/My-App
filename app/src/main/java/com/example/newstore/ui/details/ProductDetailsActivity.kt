package com.example.newstore.ui.details

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.newstore.R
import com.example.newstore.adapter.product.ProductAdapter
import com.example.newstore.databinding.ActivityProductDetailsBinding
import com.example.newstore.interfaces.RecyclerAdapterListener
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.ProductM
import com.example.newstore.ui.cart.CartFragment
import com.example.newstore.utils.ViewPagerAdapter
import com.example.newstore.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus


@AndroidEntryPoint
class ProductDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductDetailsBinding
    lateinit var adapter: ProductAdapter
    private val productDetailsActivityVM: ProductDetailsActivityVM by viewModels()
    var error: String = ""
    var productM: ProductM? =null
    val cartFragment = CartFragment.newInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details)
        binding.presenter = this


        val value = intent.extras?.getString("singleP")
        productDetailsActivityVM.singleProduct("api/v1/products/$value")




        productDetailsActivityVM.homeLiveDataSP.observe(this) {
            when (it) {
                is NetworkResult.Loading -> {
                    binding.progressBarDetail.visibility = View.VISIBLE
                }
                is NetworkResult.Success -> {
                    productM = it.data
                    val imageList = ArrayList<SlideModel>()
                    productM!!.images.forEach {
                        imageList.add(SlideModel(it, " ", ScaleTypes.CENTER_CROP))
                        binding.rlDetails.visibility = View.VISIBLE
                        binding.product = productM

                    }
                    binding.imageSlider.setImageList(imageList)
                    this.showToast("${productM?.title}")
                    binding.progressBarDetail.visibility = View.GONE






                }
                is NetworkResult.Failure -> {
                    binding.progressBarDetail.visibility = View.GONE
//                    binding.error.visibility = View.VISIBLE

                }
                else -> {}

            }//close when

        }//close observe

        binding.rlAddCart.setOnClickListener{

        }



        binding.icBack.setOnClickListener {
            finish()
        }
    }

    fun clickColor(view:View){
        binding.expandColor.collapse()
        binding.expandColor.toggle()
        binding.clickColor.setOnClickListener {
            binding.expandColor.toggle()
        }
    }

    fun clickAddCart(view:View){
        EventBus.getDefault().post(productM)
    }

    private fun setupViewPager() {
        val mAdapter = ViewPagerAdapter(supportFragmentManager)
        mAdapter.apply {
        }

    }

//    fun seeMore(){
//        if (!expandable) {
//            expandable = true
//            val animation = ObjectAnimator.ofInt(binding.tvDescription, "maxLines", 40)
//            animation.setDuration(100).start()
//            binding.btnSeeMore.visibility =View.GONE
//        } else {
//            expandable = false
//            val animation = ObjectAnimator.ofInt(binding.tvDescription, "maxLines", 4)
//            animation.setDuration(100).start()
//            binding.btnSeeMore.visibility =View.VISIBLE
//        }
//
//        adapter = ProductAdapter(this,null,"DETAILS",null,null)
//        binding.progressBar.visibility = View.GONE
//    }




}//close Class