package android.example.esm.surveymodule;

import android.content.Context;
import android.example.esm.R;
import android.example.esm.surveymodule.models.SurveyForm;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.SurveyViewHolder> {

    Context context;
    List<SurveyForm> surveyFormList;
    private OnItemClickListener onItemClickListener;

    public SurveyAdapter(List<SurveyForm> surveyList, Context c){
        this.surveyFormList = surveyList;
        context = c;
    }

    @NonNull
    public SurveyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View thisView;
        thisView = LayoutInflater.from(parent.getContext()).inflate(R.layout.survey_item, parent, false);

        return new SurveyViewHolder(thisView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(SurveyViewHolder holders, int position){
        final SurveyViewHolder holder = holders;
        SurveyForm surveyForm = surveyFormList.get(position);
        holder.surveyTitle.setText(surveyForm.getSurveyTitle());
        holder.surveyDesc.setText(surveyForm.getSurveyDesc());
    }

    public void setData(List<SurveyForm> list){
        this.surveyFormList = list;
        notifyItemRangeChanged(0, surveyFormList.size());
    }

    @Override
    public int getItemCount(){
        return surveyFormList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public class SurveyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView surveyTitle;
        TextView surveyDesc;
        private OnItemClickListener onItemClickListener;

        public SurveyViewHolder(View itemView, OnItemClickListener onItemClickListener){
            super(itemView);
            itemView.setOnClickListener(this);
            surveyTitle = (TextView) itemView.findViewById(R.id.survey_title);
            surveyDesc = (TextView) itemView.findViewById(R.id.survey_desc);
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
            Log.d("test", "clicked from adapter");
        }
    }


}
