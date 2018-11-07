package com.rain.androidjetpackcomponent.room;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.rain.androidjetpackcomponent.R;
import com.rain.androidjetpackcomponent.room.dao.BookDao;
import com.rain.androidjetpackcomponent.room.dao.UserDao;
import com.rain.androidjetpackcomponent.room.entity.Book;
import com.rain.androidjetpackcomponent.room.entity.User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Author:rain
 * Date:2018/11/6 15:57
 * Description:
 * 主要是练习room数据库的使用
 * 参见：https://blog.csdn.net/guiying712/article/details/81183653
 * 参见：https://blog.csdn.net/qq_37475168/article/details/79790848
 * room 数据库操作默认不能在主线程操作
 */
public class RoomActivity extends AppCompatActivity {

    private AppDatabase db;
    private UserDao userDao;
    private BookDao bookDao;
    private TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        tvResult = findViewById(R.id.tv_result);

        db = AppDatabase.instance();
        userDao = db.userDao();
        bookDao = db.bookDao();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.room_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_insert:
                User user = new User(1, "xiao", "wang");
                User user2 = new User(2, "xiao", "ming");
                User user3 = new User(3, "xiao", "hong");
                userDao.insertAll(user, user2, user3);

                Book book = new Book("java", 1);
                Book book2 = new Book("php", 1);
                Book book3 = new Book("android", 1);
                bookDao.insertAll(book, book2, book3);
                queryAndShow();
                break;

            case R.id.action_delete:
                userDao.delete();
                queryAndShow();
                break;

            case R.id.action_update:
                userDao.updateUserFirstname("da", 2);
                queryAndShow();
                break;

            case R.id.action_query:
                queryAndShow();
                break;

            case R.id.action_query2:
                String s = bookDao.getBookFromUser().toString();
                setTextResult(s);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void setTextResult(String s) {
        tvResult.setText(s);
    }

    private void queryAndShow() {
        setTextResult("");
        String s = userDao.getAll().toString() + "\n---------------\n";
        String s1 = bookDao.getAll().toString();
        setTextResult(s + s1);
    }
}
