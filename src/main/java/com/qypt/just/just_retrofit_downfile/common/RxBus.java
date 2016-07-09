package com.qypt.just.just_retrofit_downfile.common;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

/**
 * author Justson
 * Created by Administrator on 2016/7/8.
 */
public class RxBus {

    private  static RxBus rxBus=null;
    SerializedSubject<Object,Object>observable;

    private RxBus(){
        observable=new SerializedSubject<>(PublishSubject.create());
    }

    public  static RxBus getInstance(){
        if(rxBus==null){
          rxBus=  RxBusHolder.rxbus;
        }
        return rxBus;
    }


   private static final class RxBusHolder {
       private static final RxBus rxbus=new RxBus();
   }

    public SerializedSubject getObservable(){
        return observable;
    }

    public boolean hasObserver(){
        return observable.hasObservers();
    }
    public <T> Observable<T> toBservable(Class<T> cla){
        return observable.ofType(cla);
    }
    public void onPost(Object object){
        observable.onNext(object);
    }
}
