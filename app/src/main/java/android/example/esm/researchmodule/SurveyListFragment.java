package android.example.esm.researchmodule;

import android.content.Intent;
import android.example.esm.API.APIClient;
import android.example.esm.API.APIInterface;
import android.example.esm.researchmodule.models.SurveyForm;
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


public class SurveyListFragment extends Fragment {

    RecyclerView recyclerView;
    SurveyAdapter surveyAdapter;
    public List<SurveyForm> surveyFormList = new ArrayList<>();
    private SurveyForm surveyForm;
    private String title;
    private String desc;
    TextView surveyTitle;
    TextView surveyDesc;
    public boolean isHorizontal;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_survey_list, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_survey);


        APIInterface apiInterface = APIClient.getAPIClient().create(APIInterface.class);

      /*  Call<List<SurveyForm>> call = apiInterface.getSurveys();

        call.enqueue(new Callback<List<SurveyForm>>() {
            @Override
            public void onResponse(Call<List<SurveyForm>> call, Response<List<SurveyForm>> response) {
                surveyFormList = response.body();
                Log.d("list", response.body().toString());
                Log.d("here2", "success");
                if (response.isSuccessful() && response.body() != null) {
                    if (!surveyFormList.isEmpty()) {
                        surveyFormList.clear();
                    }

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
                Log.d("here3", "failure "+ t.getMessage());
            }
        });*/


        Call<SurveyForm> call = apiInterface.getSurveys();

        call.enqueue(new Callback<SurveyForm>() {
            @Override
            public void onResponse(Call<SurveyForm> call, Response<SurveyForm> response) {
                surveyForm = response.body();
                Log.d("list", response.body().toString());
                if (response.isSuccessful() && response.body() != null) {
                    if (!surveyFormList.isEmpty()) {
                        surveyFormList.clear();
                    }

                    Log.d("list", surveyFormList.toString());
                    surveyFormList.add(surveyForm);
                    surveyAdapter = new SurveyAdapter(surveyFormList, getActivity());

                    if (isHorizontal == true){
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        surveyAdapter.isHorizontal = true;
                    } else {
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    }

                    surveyAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(surveyAdapter);
                    surveyListener();
                }
            }

            @Override
            public void onFailure(Call<SurveyForm> call, Throwable t) {
                Log.d("here3", "failure "+ t.getMessage());
            }
        });

        return rootView;
    }


    public void surveyListener(){
        Log.d("test", "listening");
        surveyAdapter.setOnItemClickListener(new SurveyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //SurveyForm surveyFormItem = surveyFormList.get(position);
                desc = surveyForm.getSurveyDesc();
                surveyTitle = view.findViewById(R.id.survey_title);
                surveyDesc = view.findViewById(R.id.survey_desc);
                Log.d("input", surveyTitle + "and" + surveyForm.getSurveySection().size());

                Intent intent = new Intent(getActivity(), SurveyDetailsActivity.class);
                intent.putExtra("survey", surveyForm);
                startActivity(intent);
            }
        });
    }
}
