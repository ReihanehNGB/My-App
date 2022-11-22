package com.example.newstore.ui.details

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
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.ProductM
import com.example.newstore.ui.cart.CartFragment
import com.example.newstore.utils.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus


@AndroidEntryPoint
class ProductDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductDetailsBinding
    lateinit var adapter: ProductAdapter
    private val productDetailsActivityVM: ProductDetailsActivityVM by viewModels()
    var error: String = ""
    var productM: ProductM? = null
    val cartFragment = CartFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_product_details)

        val value = intent.extras?.getString("singleP")
        productDetailsActivityVM.singleProduct("api/v1/products/$value")



        productDetailsActivityVM.homeLiveDataSP.observe(this) {
            when (it) {
                is NetworkResult.Loading -> {
//                    binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkResult.Success -> {
                    productM = it.data
                    val imageList = ArrayList<SlideModel>()
                    productM?.images?.forEach {
                        imageList.add(SlideModel(it, " ", ScaleTypes.CENTER_CROP))
                    }
                    binding.imageSlider.setImageList(imageList)
                    Toast.makeText(this, "${productM?.title}", Toast.LENGTH_SHORT).show()
                    binding.product = productM





//                    binding.btnSeeMore.setOnClickListener(View.OnClickListener {
//                        if (!expandable) {
//                            expandable = true
//                            val animation = ObjectAnimator.ofInt(binding.tvDescription, "maxLines", 40)
//                            animation.setDuration(100).start()
//                            binding.btnSeeMore.visibility =View.GONE
//                        } else {
//                            expandable = false
//                            val animation = ObjectAnimator.ofInt(binding.tvDescription, "maxLines", 4)
//                            animation.setDuration(100).start()
//                            binding.btnSeeMore.visibility =View.VISIBLE
//                        }
//                    })
//                    adapter = ProductAdapter(this,null,"DETAILS",null,productM,null)
//                    binding.progressBar.visibility = View.GONE


                }
                is NetworkResult.Failure -> {
                    val error = it.errorMessage
//                    binding.progressBar.visibility = View.GONE
//                    binding.error.visibility = View.VISIBLE

                }
                else -> {}

            }//close when

        }//close observe



        binding.icBack.setOnClickListener {
            finish()
        }
    }


    private fun initBinding(layout: Int) {
        binding = DataBindingUtil.setContentView(this, layout)
        binding.presenter = this

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




}//close Class