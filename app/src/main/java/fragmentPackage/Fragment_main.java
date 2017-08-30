package fragmentPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.dell.wangyouwei1506b20170825.App;
import com.dell.wangyouwei1506b20170825.Login;
import com.dell.wangyouwei1506b20170825.R;
import com.google.gson.Gson;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import AdapterPack.MyAdapter;
import Bean.Data;
import me.maxwin.view.XListView;

/**
 * 姓名：王有为
 * 时间：2017/8/25.
 */

public class Fragment_main extends Fragment implements XListView.IXListViewListener{
    private TextView textView;
    private List<Data.DataBean> list;
    private XListView listView;
    private DbManager dbManager;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment,container,false);
        textView=(TextView)view.findViewById(R.id.fragmenttext);
        listView=(XListView)view.findViewById(R.id.xlistview);
        listView.setPullRefreshEnable(true);
        listView.setPullLoadEnable(true);
        listView.setXListViewListener(this);
        listviewlistenr();
        list=new ArrayList<>();
        //接收传过来的值
        Bundle bundle = getArguments();
        String name = bundle.getString("name");
        textView.setText(name);
        //Xutils加载数据

        loadData();
        //添加适配器
        MyAdapter adapter = new MyAdapter(getActivity(),list);
        listView.setAdapter(adapter);
        return view;
    }

    private void listviewlistenr() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        RequestParams params = new RequestParams("http://ic.snssdk.com/2/article/v25/stream/?category=news_finance&count=20&min_behot_time=1455522899&bd_city=北京市&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455523440&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson =new Gson();
                Data data = gson.fromJson(result, Data.class);
                list.addAll(data.getData());
                App app = (App) getActivity().getApplication();
                dbManager = app.getDbManager();
                try {
                    dbManager.save(data.getData());
                } catch (DbException e) {
                    e.printStackTrace();
                }
                //停止刷新，加载
                listView.stopRefresh();
                listView.stopLoadMore();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
//刷新
    @Override
    public void onRefresh() {
        list.clear();
        loadData();
    }
//加载更多
    @Override
    public void onLoadMore() {
        loadData();
    }
}
