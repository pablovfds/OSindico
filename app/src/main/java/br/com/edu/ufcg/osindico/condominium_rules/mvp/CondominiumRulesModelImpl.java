package br.com.edu.ufcg.osindico.condominium_rules.mvp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.RuleResponse;
import br.com.edu.ufcg.osindico.data.services.RulesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by emanoel on 02/03/17.
 */

public class CondominiumRulesModelImpl implements CondominiumRulesContract.Model {

    private RulesService rulesService;

    public CondominiumRulesModelImpl(RulesService rulesService) {
        this.rulesService = rulesService;
    }

    @Override
    public void getCondominiumRules(String token, final OnCondominiumRulesListener listener) {
        Call<List<RuleResponse>> call = rulesService.getmRulesApi().getCondominiumRules(token);

        call.enqueue(new Callback<List<RuleResponse>>() {
            @Override
            public void onResponse(Call<List<RuleResponse>> call, Response<List<RuleResponse>> response) {
                List<RuleResponse> ruleResponseList = response.body();

                if (response.isSuccessful()) {
                    Log.d("Sucesso", "OK");
                    listener.onSuccess(ruleResponseList);
                } else {
                    Log.d("status", Integer.toString(response.code()));
                    listener.onSuccess(new ArrayList<RuleResponse>());
                }
            }

            @Override
            public void onFailure(Call<List<RuleResponse>> call, Throwable t) {
                Log.d("onFailure", t.toString());
                listener.onServerError(t.getMessage());
            }
        });
    }
}
