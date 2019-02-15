package com.mopub.nativeads;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aerserv.sdk.AerServConfig;
import com.aerserv.sdk.AerServEvent;
import com.aerserv.sdk.AerServEventListener;
import com.aerserv.sdk.AerServInterstitial;
import com.aerserv.sdk.AerServSdk;
import com.aerserv.sdk.AerServTransactionInformation;
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;

import java.math.BigDecimal;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MoPubView moPubView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moPubView = (MoPubView) findViewById(R.id.adview);
        SdkConfiguration sdkConfiguration= new  SdkConfiguration.Builder("d94797325ff34dd383cf0dad5e627071").build();

        MoPub.initializeSdk(this, sdkConfiguration, initSdkListener());
        AerServSdk.init(this, "1003556");
    }

    private SdkInitializationListener initSdkListener() {
        return new SdkInitializationListener() {

            @Override
            public void onInitializationFinished() {
                Log.d("mopub", "MopubInitializationFinished:");

            }
        };
    }

    public  void loadInmobiBanner(View v){

        moPubView.setAdUnitId("d94797325ff34dd383cf0dad5e627071"); // Enter your Ad Unit ID from www.mopub.com
        moPubView.loadAd();

    }

    public  void loadAerservBanner(View v){

        moPubView.setAdUnitId("9ef899e742eb408896a915d6ba641340"); // Enter your Ad Unit ID from www.mopub.com
        moPubView.loadAd();

    }

    public  void loadAerservInterstitial(View v){


        AerServEventListener listener = new AerServEventListener() {
            @Override
            public void onAerServEvent(AerServEvent event, List params) {
                switch (event) {

                    case AD_LOADED:
                        Log.d("onAerServEvent", "Interstitial AD_LOADED");



                        // Execute some code when AD_LOADED event occurs.
                        break;
                    case AD_DISMISSED:
                        // Execute some code when AD_DISMISSED event occurs.
                        Log.d("onAerServEvent", "Interstitial AD_DISMISSED");

                        break;
                    case AD_FAILED:
                        Log.d("onAerServEvent", "Interstitial AD_FAILED");
                        // Execute some code when AD_FAILED event occurs.
                        break;

                    case AD_IMPRESSION:
                        Log.d("onAerServEvent", "Interstitial AD_IMPRESSION");
                        break;

                    case SHOW_TRANSACTION:
                        Log.d("onAerServEvent", "Interstitial SHOW_TRANSACTION");
                        AerServTransactionInformation vc = (AerServTransactionInformation) params.get(0);
                        String buyerName = vc.getBuyerName();
                        BigDecimal buyerPrice = vc.getBuyerPrice();
                        String buyerprice = buyerPrice.toString();
                        Log.d("buyerName", buyerName);
                        Log.d("buyerPrice", buyerprice);
                        break;

                }
            }
        };


        AerServConfig config = new AerServConfig(this, "1060882");
        config.setEventListener(listener);//.setPreload(false);
        AerServInterstitial interstitial = new AerServInterstitial(config);
        interstitial.show();





    }

    @Override
    protected void onPause() {
        super.onPause();
        MoPub.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MoPub.onStop(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MoPub.onResume(this);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    public void onBannerLoaded(MoPubView banner){


    }

    // Sent when the banner has failed to retrieve an ad. You can use the MoPubErrorCode value to diagnose the cause of failure.
    public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode){


    }

    // Sent when the user has tapped on the banner.
    public void onBannerClicked(MoPubView banner){


    }

    // Sent when the banner has just taken over the screen.
    public void onBannerExpanded(MoPubView banner){


    }

    // Sent when an expanded banner has collapsed back to its original size.
    public void onBannerCollapsed(MoPubView banner){


    }

}
