package android.example.esm.surveymodule;

import android.content.Intent;
import android.example.esm.API.APIClient;
import android.example.esm.API.APIInterface;
import android.example.esm.surveymodule.models.SurveyForm;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.esm.R;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SurveyFragment extends Fragment {

    RecyclerView recyclerView;
    SurveyAdapter surveyAdapter;
    private List<SurveyForm> surveyFormList = new ArrayList<>();
    private String title;
    private String desc;
    TextView surveyTitle;
    TextView surveyDesc;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_survey, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_survey);

        Log.d("here", "here");

        APIInterface apiInterface = APIClient.getAPIClient().create(APIInterface.class);

        Call<List<SurveyForm>> call = apiInterface.getSurveys();

        call.enqueue(new Callback<List<SurveyForm>>() {
            @Override
            public void onResponse(Call<List<SurveyForm>> call, Response<List<SurveyForm>> response) {
                surveyFormList = response.body();
                Log.d("list", response.body().toString());
                Log.d("here", "success");
                if (response.isSuccessful() && response.body() != null) {
                  /*  if (!surveyFormList.isEmpty()) {
                        surveyFormList.clear();
                    }*/

                    Log.d("list", surveyFormList.toString());

                    surveyAdapter = new SurveyAdapter(surveyFormList, getActivity());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    surveyAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(surveyAdapter);
                    surveyListener();
                }
            }

            @Override
            public void onFailure(Call<List<SurveyForm>> call, Throwable t) {
                Log.d("here", "failure "+ t.getMessage());
            }
        });

        return rootView;
    }

    public void surveyListener(){
        Log.d("test", "listening");
        surveyAdapter.setOnItemClickListener(new SurveyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                SurveyForm surveyFormItem = surveyFormList.get(position);
                title = surveyFormItem.getSurveyTitle();
                desc = surveyFormItem.getSurveyDesc();
                surveyTitle = view.findViewById(R.id.survey_title);
                surveyDesc = view.findViewById(R.id.survey_desc);

                Intent intent = new Intent(getActivity(), SurveyDetailsActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("desc", desc);
                startActivity(intent);
            }
        });
    }
}
