package com.darrenthiores.movbybwa.checkOut

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.darrenthiores.core.model.data.CheckOut
import com.darrenthiores.core.model.data.User
import com.darrenthiores.core.ui.CheckOutAdapter
import com.darrenthiores.movbybwa.Navigation.MainNavActivity
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.FragmentCheckOutBinding
import org.koin.android.ext.android.inject

class CheckOutFragment : Fragment() {

    private var _binding:FragmentCheckOutBinding? = null
    private val binding get() = _binding
    private val viewModel:CheckOutViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCheckOutBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkOutData = arguments?.getParcelableArrayList<CheckOut>(PickSeatFragment.SEAT_DATA)
        val checkOutAdapter = CheckOutAdapter()
        checkOutAdapter.setData(checkOutData!!)

        var total = 0

        for(data in checkOutData){
            total += data.price
        }

        binding?.apply {

            tvCheckOutCinema.text = "Kelapa Gading XXI"
            tvCheckOutDate.text = "26/10/2021"

            viewModel.getBalance().addOnCompleteListener {
                if(it.isSuccessful){
                    val data = it.result?.toObject(User::class.java)
                    updateUI(data?.balance, total)
                }
            }

            rvCheckOut.layoutManager = LinearLayoutManager(activity)
            rvCheckOut.adapter = checkOutAdapter

        }

        buttonFunctionality()

    }

    private fun updateUI(balance:Double?, total:Int){
        binding?.apply {

            tvCheckOutTotal.text = getString(R.string.text_balance, total.toString())
            tvCheckOutBalance.text = getString(R.string.text_balance, balance.toString())

            if(balance!!<total){
                tvCheckOutBalance.setTextColor(Color.RED)
                btPay.visibility = View.GONE
                tvCheckOutBalanceLess.visibility = View.VISIBLE
            } else {
                tvCheckOutBalance.setTextColor(Color.GREEN)
                btPay.visibility = View.VISIBLE
                tvCheckOutBalanceLess.visibility = View.GONE
            }

            btPay.setOnClickListener {
                val balanceAfterPay = balance - total
                viewModel.updateBalance(balanceAfterPay)
                showNotification()
                findNavController().navigate(R.id.action_checkOutFragment_to_bookSuccessFragment)
            }

        }
    }

    private fun buttonFunctionality(){
        binding?.apply {
            btUp.setOnClickListener { requireActivity().onBackPressed() }
            btCancel.setOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun showNotification() {
        val pendingIntent = NavDeepLinkBuilder(requireContext())
            .setComponentName(MainNavActivity::class.java)
            .setGraph(R.navigation.mobile_navigation)
            .setDestination(R.id.navigation_ticket)
            .createPendingIntent()
        val notificationManager = activity?.applicationContext?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification: NotificationCompat.Builder =
            NotificationCompat.Builder(activity?.applicationContext!!, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.logo_mov)
                .setLargeIcon(BitmapFactory.decodeResource(
                    resources,
                    R.mipmap.mov_icon
                ))
                .setContentIntent(pendingIntent)
                .setContentTitle("Ticket Successfully Bought")
                .setContentText("Check Your Ticket Here!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            notification.setChannelId(NOTIFICATION_CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(NOTIFICATION_ID, notification.build())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        private const val NOTIFICATION_CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "buy_success_channel"
        private const val NOTIFICATION_ID = 19
    }

}