package com.islamassem.utils.dialog;

import android.content.Context
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.TextView
import androidx.core.text.toSpannable
import com.islamassem.utils.R
import com.islamassem.utils.base.BaseDialogFragment
import com.islamassem.utils.getColorRes
import com.islamassem.utils.isEmpty
import kotlinx.android.synthetic.main.dialog.*
import java.lang.RuntimeException

class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        for(a in nums.indices)
        {
            val s = target - nums[a]
            val index = checkNum(nums,s,a)
            if(index>-1){
                return intArrayOf(a,index)
                return IntArray(2){
                    when(it){
                        0->a
                        1->index
                        else -> 0
                    }
                }
            }
        }
        throw RuntimeException()
    }
    fun checkNum(nums: IntArray, target: Int,skip : Int) : Int{
        for(i in nums.indices)
            if (nums[i] == target&&skip != i)
                return i
        return -1
    }
}
class MessageDialog() : BaseDialogFragment() {

    var okResource : Int = 0
    var cancelResource : Int = 0
    lateinit var okText : CharSequence
    lateinit var cancelText : CharSequence
    var titleResource : Int = 0
    var messageResource : Int = 0
    lateinit var titleText : CharSequence
    lateinit var messageText : CharSequence
    lateinit var  onTitleClickListener : View.OnClickListener
    lateinit var  onMessageClickListener : View.OnClickListener
    lateinit var  onOkClickListener : View.OnClickListener
    lateinit var  onCancelClickListener : View.OnClickListener
    public var isProgress = false;
    var exitOnDismiss = false;
    var canCancel = true;
    override fun getLayoutId(): Int {
        return R.layout.dialog
    }


    override fun saveInstanceState(savedInstanceState: Bundle) {

    }

    override fun initData(data: Bundle) {

    }

    override fun initVariables(context: Context) {

    }

    override fun initViewModel() {

    }
    override fun initViews() {
        if (isProgress) {
            progressBar.visibility = View.VISIBLE;
            if (isEmpty(message.text)) {
                message_container.visibility = View.GONE;
                dialog_card.setCardBackgroundColor(getColorRes(requireContext(),
                    R.color.transparent));
            } else {
                message_container.visibility = View.VISIBLE;
                dialog_card.setCardBackgroundColor(getColorRes(requireContext(),
                    R.color.white_black));
            }
            return
        }
        progressBar.visibility = View.GONE;
        if (okResource != 0)
            ok.setText(okResource);
        if (cancelResource != 0)
            cancel.setText(cancelResource);
        if (::okText.isInitialized && !isEmpty(okText))
            ok.text = okText
        if (::cancelText.isInitialized && !isEmpty(cancelText))
            cancel.text = cancelText

        if (::onTitleClickListener.isInitialized)
            title.setOnClickListener(onTitleClickListener)
        if (::onMessageClickListener.isInitialized)
            message.setOnClickListener(onMessageClickListener)
        if (::onOkClickListener.isInitialized)
            ok.setOnClickListener(onOkClickListener)
        else ok.setOnClickListener { dismiss() }
        if (::onCancelClickListener.isInitialized)
            cancel.setOnClickListener(onCancelClickListener)
        else cancel.setOnClickListener { dismiss() }
        //todo add divider divider.visibility with title
        if (titleResource != 0)
            title.setText(titleResource)
        if (::titleText.isInitialized && !isEmpty(titleText))
            title.text = titleText
        title.visibility = if (!isEmpty(title))  View.VISIBLE else View.GONE
        if (::messageText.isInitialized && messageText is String) {
            if((messageText as String ).toLowerCase().contains("unable to resolve host") ||
                (messageText as String ).toLowerCase().contains("failed to connect"))
                messageText= ("فشل الاتصال بالسيرفر,تحقق من اتصالك بالانترنت");
            if((messageText as String ).toLowerCase().contains("timeout"))
                messageText= ("فشل الاتصال بالسيرفر,تحقق من اتصالك بالانترنت");
            if((messageText as String ).toLowerCase().contains("Software caused connection".toLowerCase()))
                messageText= ("فشل الاتصال بالسيرفر,تحقق من اتصالك بالانترنت");
        }
        if (messageResource != 0)
            message.setText(messageResource)
        if (::messageText.isInitialized)
            message.text = messageText
//        {
//            if (messageText is Spannable)
//                message.text = messageText
//            if (messageText is SpannableStringBuilder)
//                message.setText(messageText, TextView.BufferType.SPANNABLE)
//        }
        message.visibility = if (!isEmpty(message))  View.VISIBLE else View.GONE
        message_container.visibility = message.visibility
        cancel.visibility = if (!isEmpty(cancel) && ::onCancelClickListener.isInitialized)  View.VISIBLE else View.GONE
        isCancelable = canCancel;
    }

//    override fun dismiss() {
//        try {
//            if (isProgress)
//                Handler(Looper.getMainLooper()).postDelayed({
//                    try {
//                        super.dismiss();
//                    } catch (e : Exception) {
//                        com.islamassem.utils.LogE("messageDialog", "${e.message} and isProgress = $isProgress",e);
//                    }
//                }, 200);
//            else super.dismiss();
//        } catch (e : Exception) {
//            com.islamassem.utils.LogE("messageDialog", "${e.message} and isProgress = $isProgress",e);
//            super.dismiss();
//        }
//    }
}