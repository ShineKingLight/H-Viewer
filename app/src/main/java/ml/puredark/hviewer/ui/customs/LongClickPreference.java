package ml.puredark.hviewer.ui.customs;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by PureDark on 2016/10/9.
 */

public class LongClickPreference extends Preference implements View.OnLongClickListener{
    private View.OnLongClickListener onLongClickListener;

    public LongClickPreference(Context context) {
        super(context);
    }

    public LongClickPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LongClickPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener){
        this.onLongClickListener = onLongClickListener;
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        if (parent instanceof ListView) {
            ListView listView = (ListView) parent;
            listView.setOnItemLongClickListener(new ListView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    ListView listView = (ListView) parent;
                    ListAdapter listAdapter = listView.getAdapter();
                    Object obj = listAdapter.getItem(position);
                    if (obj != null && obj instanceof View.OnLongClickListener) {
                        View.OnLongClickListener longListener = (View.OnLongClickListener) obj;
                        return longListener.onLongClick(view);
                    }
                    return false;
                }
            });
        }
        return super.onCreateView(parent);
    }

    @Override
    public boolean onLongClick(View v) {
        return onLongClickListener!=null && onLongClickListener.onLongClick(v);
    }
}
