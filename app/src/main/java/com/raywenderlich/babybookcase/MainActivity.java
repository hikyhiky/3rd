/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.babybookcase;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

  private static final String favoritedBookNamesKey = "favoritedBookNamesKey";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    GridView gridView = (GridView)findViewById(R.id.gridview);
    final BooksAdapter booksAdapter = new BooksAdapter(this, books);
    gridView.setAdapter(booksAdapter);

    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Book book = books[position];
        book.toggleFavorite();
        booksAdapter.notifyDataSetChanged();
      }
    });
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    final List<Integer> favoritedBookNames = new ArrayList<>();
    for (Book book : books) {
      if (book.getIsFavorite()) {
        favoritedBookNames.add(book.getName());
      }
    }

    outState.putIntegerArrayList(favoritedBookNamesKey, (ArrayList)favoritedBookNames);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);

    final List<Integer> favoritedBookNames =
      savedInstanceState.getIntegerArrayList(favoritedBookNamesKey);

    // warning: typically you should avoid n^2 loops like this, use a Map instead.
    // I'm keeping this because it is more straightforward
    for (int bookName : favoritedBookNames) {
      for (Book book : books) {
        if (book.getName() == bookName) {
          book.setIsFavorite(true);
          break;
        }
      }
    }
  }

  private Book[] books = {
    new Book(R.string.abc_an_amazing_alphabet_book, R.string.dr_seuss, R.drawable.abc,
      "http://ecx.images-amazon.com/images/I/51rFg8xKvxL._AA240_FMwebp_QL65_.jpg"),
    new Book(R.string.are_you_my_mother, R.string.dr_seuss, R.drawable.areyoumymother,
      "http://ecx.images-amazon.com/images/I/51Zu5zbzWDL._AA240_QL65_.jpg"),
    new Book(R.string.where_is_babys_belly_button, R.string.karen_katz, R.drawable.whereisbabysbellybutton,
      "http://ecx.images-amazon.com/images/I/51scITk38DL._AA240_QL65_.jpg"),
    new Book(R.string.on_the_night_you_were_born, R.string.nancy_tillman, R.drawable.onthenightyouwereborn,
      "http://ecx.images-amazon.com/images/I/51pny-HGhnL._AA240_QL65_.jpg"),
    new Book(R.string.hand_hand_fingers_thumb, R.string.dr_seuss, R.drawable.handhandfingersthumb,
      "http://ecx.images-amazon.com/images/I/51W+iGZ-dkL._AA240_QL65_.jpg"),
    new Book(R.string.the_very_hungry_caterpillar, R.string.eric_carle, R.drawable.theveryhungrycaterpillar,
      "http://ecx.images-amazon.com/images/I/41zqrOnjpTL._AA240_QL65_.jpg"),
    new Book(R.string.the_going_to_bed_book, R.string.sandra_boynton, R.drawable.thegoingtobedbook,
      "http://ecx.images-amazon.com/images/I/61TU+6P-3yL._AA240_QL65_.jpg"),
    new Book(R.string.oh_baby_go_baby, R.string.dr_seuss, R.drawable.ohbabygobaby,
      "http://ecx.images-amazon.com/images/I/518wNY-AF7L._AA240_QL65_.jpg"),
    new Book(R.string.the_tooth_book, R.string.dr_seuss, R.drawable.thetoothbook,
      "http://ecx.images-amazon.com/images/I/51RFYYCYRTL._AA240_QL65_.jpg"),
    new Book(R.string.one_fish_two_fish_red_fish_blue_fish, R.string.dr_seuss, R.drawable.onefish,
      "http://ecx.images-amazon.com/images/I/51dYLc1uKsL._AA240_QL65_.jpg")
  };

}
