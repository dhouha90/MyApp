package com.example.dchikhaoui.myapp.DETAILBOOK;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.dchikhaoui.myapp.BookApplication;
import com.example.dchikhaoui.myapp.Model.AllOffers;
import com.example.dchikhaoui.myapp.Model.offers;
import com.example.dchikhaoui.myapp.R;
import com.example.dchikhaoui.myapp.service.HttpResponse;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import rx.Subscriber;
import rx.observables.MathObservable;

public class PaiBookPresenter implements DetailBookContract.UserActionPai {
    Context mContext;
    int mSommeFinal = 0;
    HashMap<String, Integer> mIsbn;
    public String sommToPai = "";
    AllOffers mAllOffers;

    public PaiBookPresenter() {
    }

    public PaiBookPresenter(Context mContext, int mSommeFinal, HashMap<String, Integer> mIsbn) {
        this.mContext = mContext;
        this.mSommeFinal = mSommeFinal;
        this.mIsbn = mIsbn;
    }

    @Override
    public void payBooks() {
        String metadataId = PayPalConfiguration.getClientMetadataId(BookApplication.getApp().getApplicationContext());
        PayPalConfiguration config = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
                .clientId(metadataId);
        PayPalPayment payment = new PayPalPayment(new BigDecimal(mSommeFinal), "EUR", "sample item",
                PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(BookApplication.getApp(), PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        BookApplication.getApp().getApplicationContext().startActivity(intent);
    }


    void calculOffre(AllOffers allOffers) {
        final List<offers> listOffres = allOffers.getListOffers();
        final List<Double> resOffers = new ArrayList<>();
        io.reactivex.Observable
                .just(listOffres)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<List<offers>, ObservableSource<offers>>() {
                    @Override
                    public ObservableSource<offers> apply(List<offers> offers) {
                        return io.reactivex.Observable
                                .just(offers)
                                .flatMap(new Function<List<com.example.dchikhaoui.myapp.Model.offers>, ObservableSource<offers>>() {
                                    @Override
                                    public ObservableSource<offers> apply(List<offers> offers) {
                                        return io.reactivex.Observable.fromIterable(offers);
                                    }
                                });
                    }
                }).subscribe(new Observer<offers>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(offers offers) {
                if (offers.getType().equals(OfferType.percentage.toString())) {
                    double mPercent = ((double) offers.getValue() / 100) * mSommeFinal;
                    resOffers.add(mPercent);
                }

                if (offers.getType().equals(OfferType.minus.toString())) {
                    resOffers.add((double) offers.getValue());
                }
                if (offers.getType().equals(OfferType.slice.toString())) {
                    if (mSommeFinal >= offers.getSliceValue()) {
                        resOffers.add((double) offers.getSliceValue());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                getBestOffers(resOffers);
            }
        });
    }


    public void getBestOffers(List<Double> allOffers) {
        rx.Observable<Double> mAllListOffers = rx.Observable.from(allOffers);
        MathObservable.max(mAllListOffers).subscribe(new Subscriber<Double>() {
            @Override
            public void onCompleted() {
                listBookSelectedActivity.binding.allSomme.setText(mContext.getResources().getString(R.string.somme_a_payer, sommToPai));
            }

            @Override
            public void onError(Throwable e) {
                Log.i("eroor", "onError: " + e.toString());
            }

            @Override
            public void onNext(Double res) {
                sommToPai = String.valueOf(Double.valueOf(mSommeFinal) - res);
            }
        });
    }


    public void PaiAll() {
        HttpResponse httpResponse = new HttpResponse();
        Observable<AllOffers> mOffers = httpResponse.getListOffers(mIsbn);
        mOffers.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<AllOffers>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AllOffers allOffers) {
                mAllOffers = allOffers;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                calculOffre(mAllOffers);
            }
        });
    }

    public enum OfferType {
        percentage,
        minus,
        slice
    }


}
