package br.com.edu.ufcg.osindico.homeSyndic.mvp;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.services.SyndicService;


public class HomeSyndicPresenterImpl implements HomeSyndicContract.Presenter, HomeSyndicContract.Model.HomeSyndicListener {

    private HomeSyndicContract.View homeSyndicView;
    private HomeSyndicContract.Model homeSyndicModel;

    public HomeSyndicPresenterImpl(SyndicService service){
        this.homeSyndicModel = new HomeSyndicModelImpl(service);
    }

    @Override
    public void onItemClicked(int id) {
        switch (id){
            case  R.id.tab_new_dweller:
                this.homeSyndicView.navigateToDwellerList();
                break;
            case R.id.tab_dwellers:
                this.homeSyndicView.navigateToDwellerRequests();
                break;
            case R.id.tab_rules:
                this.homeSyndicView.navigateToCondoRules();
                break;
        }
    }

    @Override
    public void setView(HomeSyndicContract.View view) {
        this.homeSyndicView = view;
    }

    @Override
    public void onDestroy() {}
}
