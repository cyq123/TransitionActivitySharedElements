package cyq.com.transitionactivitysharedelements;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cyq.com.transitionactivitysharedelements.VO.Item;

/**
 * activity之间共享元素的转换
 * 第一步：启动新的activity：ActivityCompat.startActivity(this,intent,activityOptionsCompat.toBundle());
 * 将要转换的view配置在activityOptionCompat当中。
 * 第二步：设置要用于在转换中标识视图的视图的名称。
 * 在新的activity中通过下面方法设置：
 *  ViewCompat.setTransitionName(imageView,TRANSITION_NAME_IMG);
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private List<Item> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = generateListData();//生成十条测试数据

        listView = findViewById(R.id.listview);
        ListviewAdapter adapter = new ListviewAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,DetailsActivity.class);
        intent.putExtra("item",list.get(position).getText());

        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair<View, String>(view.findViewById(R.id.img),DetailsActivity.TRANSITION_NAME_IMG),
                                 new Pair<View,String>(view.findViewById(R.id.txt),DetailsActivity.TRANSITION_NAME_TXT));

        ActivityCompat.startActivity(this,intent,activityOptionsCompat.toBundle());
    }

    private class ListviewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null){
                view = getLayoutInflater().inflate(R.layout.listview_item,parent,false);
            }
            TextView textView = view.findViewById(R.id.txt);

            Item item = (Item) getItem(position);
            textView.setText(item.getText());
            return view;
        }
    }


    private List<Item> generateListData(){
        List<Item> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            Item item = new Item();
            item.setText("这是第"+i+"个Item");
            list.add(item);
        }
        return list;
    }
}
