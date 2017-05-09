package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseActivityFragment {

    private InterstitialAd interstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        AdView adView = (AdView) root.findViewById(R.id.adView);

        adView.loadAd(createAdRequest());

        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                retrieveJoke();
            }
        });

        requestNewInterstitial();

        jokeButton.setOnClickListener(view -> {
            if (interstitialAd.isLoaded()) {
                interstitialAd.show();
            } else {
                retrieveJoke();
            }
        });

        return root;
    }

    private AdRequest createAdRequest() {
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        return new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                                      .build();
    }

    private void requestNewInterstitial() {
        interstitialAd.loadAd(createAdRequest());
    }
}
