package AdapterPack;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dell.wangyouwei1506b20170825.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import Bean.Data;

/**
 * 姓名：王有为
 * 时间：2017/8/25.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Data.DataBean> list;
    ImageLoader imageLoader;
    DisplayImageOptions options;
    public MyAdapter(Context context, List<Data.DataBean> list) {
        this.context = context;
        this.list = list;
        //ImageLoader 加载图片
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                        .createDefault(context);
                //将configuration配置到imageloader中
                imageLoader= ImageLoader.getInstance();
                imageLoader.init(configuration);
                options=new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .bitmapConfig(Bitmap.Config.ARGB_8888)
                        .showImageOnLoading(R.mipmap.ic_launcher)
                        .showImageForEmptyUri(R.mipmap.ic_launcher)
                        .showImageOnFail(R.mipmap.ic_launcher)
                        .build();
    }

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(context, R.layout.mybase,null);
            holder.textname=(TextView)convertView.findViewById(R.id.baseText2);
            holder.texttitle=(TextView)convertView.findViewById(R.id.baseText1);
            holder.imageView=(ImageView)convertView.findViewById(R.id.baseImage);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        Data.DataBean bean = list.get(position);
        holder.texttitle.setText(bean.getAbstractX());
        holder.textname.setText(bean.getMedia_name());
        if (bean.getMiddle_image()==null){
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
        }else{
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
        }
        return convertView;
    }
    class ViewHolder{
        TextView texttitle,textname;
        ImageView imageView;
    }
}
