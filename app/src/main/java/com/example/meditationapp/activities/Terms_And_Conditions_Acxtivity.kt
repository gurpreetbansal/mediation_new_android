package com.example.meditationapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sidegig.Interface.APIService
import com.example.meditationapp.R
import kotlinx.android.synthetic.main.terms_and_conditions_fragment.*


class Terms_And_Conditions_Acxtivity : AppCompatActivity() {
    var mAPIService: APIService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.terms_and_conditions_fragment)


        img_back_terms.setOnClickListener(View.OnClickListener {
            finish()
        })



//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.loadUrl("https://clientstagingdev.com/meditation/api/auth/termsCondtions");


        //webview = WebView(this)

//        webview.getSettings().setJavaScriptEnabled(true) // enable javascript
//
//        val activity = this
//
//        webview.setWebViewClient(object : WebViewClient() {
//            override fun onReceivedError(
//                view: WebView,
//                errorCode: Int,
//                description: String,
//                failingUrl: String
//            ) {
//                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show()
//            }
//
//            @TargetApi(android.os.Build.VERSION_CODES.M)
//            override fun onReceivedError(
//                view: WebView,
//                req: WebResourceRequest,
//                rerr: WebResourceError
//            ) {
//                // Redirect to deprecated method, so you can use it in all SDK versions
//                onReceivedError(
//                    view,
//                    rerr.errorCode,
//                    rerr.description.toString(),
//                    req.url.toString()
//                )
//            }
//        })
//
//        webview.loadUrl("http://docs.google.com/gview?embedded=true&url="+"https://selfpause.com/terms-conditions/")
      //  setContentView(webview)
    }

}
