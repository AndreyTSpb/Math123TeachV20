package potaskun.enot.math123teachv20;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SelectGroupsAdapter extends ArrayAdapter<SelectGroups> {

    private LayoutInflater lif;//для создания из resource объекта View
    private int resource;
    private List<SelectGroups> selectGroups;
    private Context mContext;

    public SelectGroupsAdapter(@NonNull Context context, int resource, List<SelectGroups> selectGroups) {
        super(context, resource, selectGroups);
        this.selectGroups = selectGroups;
        this.lif = LayoutInflater.from(context);
        this.resource = resource;
        this.mContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final ViewHolder viewHolder;
        if(convertView == null){
            //если вид нет то его создать
            convertView = lif.inflate(this.resource, parent, false);
            //Сохраняем предыдущее значение чтоб поновой не строилось
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final SelectGroups sg = selectGroups.get(position);

        viewHolder.nameGroup.setText(sg.getName());
        viewHolder.idGroup.setText(""+sg.getId());
        /*Нажатие по кнопки QR-Code*/
        viewHolder.buttonQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mContext instanceof SelectGroupActivity){
                    String name = sg.getName();
                    int id = sg.getId();
                    ((SelectGroupActivity)mContext).goQrCode(name, id);
                }
            }
        });
        /*Нажатие по названию группы*/
        viewHolder.nameGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mContext instanceof SelectGroupActivity){
                    String name = sg.getName();
                    int id = sg.getId();
                    ((SelectGroupActivity)mContext).goToGroup(name, id);
                }
            }
        });

        return convertView;
    }

    class ViewHolder{
        final TextView nameGroup, idGroup;
        final Button buttonQr;

        public ViewHolder(View view){
            nameGroup = view.findViewById(R.id.groupName);
            idGroup   = view.findViewById(R.id.idGroup);
            buttonQr  = view.findViewById(R.id.buttonQr);
        }
    }

}
