package potaskun.enot.math123teachv20;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class SelectStudentsAdapter extends ArrayAdapter<SelectStudents>{
    private LayoutInflater lif;
    private List<SelectStudents> selectStudents;
    private int resource;
    private Context mContext;

    public SelectStudentsAdapter(@NonNull Context context, int resource, List<SelectStudents> selectStudents) {
        super(context, resource, selectStudents);
        this.selectStudents = selectStudents;
        this.lif = LayoutInflater.from(context);
        this.resource = resource;
        this.mContext = context;

    }

    public View getView(int position, View convertView, ViewGroup parent){
        final ViewHolder viewHolder;
        if (convertView == null) {
            //если вид нет то его создать
            convertView = lif.inflate(this.resource, parent, false);
            //Сохраняем предыдущее значение чтоб поновой не строилось
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final SelectStudents ss = selectStudents.get(position);
        viewHolder.nameStud.setText(ss.getName());
        /*Нажатие по названию группы*/
        viewHolder.nameStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mContext instanceof StudentsInGroupActivity){
                    String name = ss.getName();
                    int id = ss.getId();
                    ((StudentsInGroupActivity)mContext).goToBall(name, id);
                }
            }
        });
        return convertView;
    }

    private class ViewHolder{
        final TextView nameStud;
        final CheckBox checkBoxPas;

        public ViewHolder(View view){
            nameStud    = view.findViewById(R.id.nameStud);
            checkBoxPas = view.findViewById(R.id.CheckBoxPas);
        }
    }
}
