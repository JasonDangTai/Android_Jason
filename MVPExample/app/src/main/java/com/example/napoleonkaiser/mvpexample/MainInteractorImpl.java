package com.example.napoleonkaiser.mvpexample;

import android.app.Activity;
import android.os.Handler;

import com.example.napoleonkaiser.mvpexample.Data.Book;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by napoleonkaiser on 19/06/2017.
 */

public class MainInteractorImpl implements MainInteractor {

    @Override
    public void findItems(final OnFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(createArray());
            }
        }, 2000);
    }

    public Realm createArray(){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Book book = new Book();
                book.setAuthor("Reto Meier");
                book.setTitle("Android 4 Application Development");
                book.setImageUrl("http://api.androidhive.info/images/realm/1.png");
                realm.insertOrUpdate(book);
            }
        });
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Book book = new Book();

                book.setId(2);
                book.setAuthor("Itzik Ben-Gan");
                book.setTitle("Microsoft SQL Server 2012 T-SQL Fundamentals");
                book.setImageUrl("http://api.androidhive.info/images/realm/2.png");
                realm.insertOrUpdate(book);

                book.setId(3);
                book.setAuthor("Magnus Lie Hetland");
                book.setTitle("Beginning Python: From Novice To Professional Paperback");
                book.setImageUrl("http://api.androidhive.info/images/realm/3.png");
                realm.insertOrUpdate(book);

                book.setId(4);
                book.setAuthor("Chad Fowler");
                book.setTitle("The Passionate Programmer: Creating a Remarkable Career in Software Development");
                book.setImageUrl("http://api.androidhive.info/images/realm/4.png");
                realm.insertOrUpdate(book);

                book.setId(5);
                book.setAuthor("Yashavant Kanetkar");
                book.setTitle("Written Test Questions In C Programming");
                book.setImageUrl("http://api.androidhive.info/images/realm/5.png");
                realm.insertOrUpdate(book);
            }
        });

        return realm;
    }
}
