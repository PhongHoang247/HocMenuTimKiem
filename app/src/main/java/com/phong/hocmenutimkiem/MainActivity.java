package com.phong.hocmenutimkiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lvTinhThanh;
    ArrayAdapter<String> adapterTinhThanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        lvTinhThanh = findViewById(R.id.lvTinhThanh);
        //Nạp danh sách tỉnh thành trong Strings.xml vào adapter
        adapterTinhThanh = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1);
        //Nạp dữ liệu vào Adapter
        adapterTinhThanh.addAll(getResources().getStringArray(R.array.arrTinhThanh));
        lvTinhThanh.setAdapter(adapterTinhThanh);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Nạp main_menu.xml:
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem menuSearch = menu.findItem(R.id.menuSearch);
        //Lấy SearchView trong main_menu.xml
        //Cách 1:
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuSearch);
        //Cách 2:
        //SearchView searchView = (SearchView) menuSearch.getActionView();
        //Xử lý sự kiện:
        MenuItemCompat.setOnActionExpandListener(menuSearch, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(MainActivity.this, "Đang Expand",Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(MainActivity.this, "Đang Collapse",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //Xử lý sự kiện tìm kiếm cho người dùng:
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapterTinhThanh.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
