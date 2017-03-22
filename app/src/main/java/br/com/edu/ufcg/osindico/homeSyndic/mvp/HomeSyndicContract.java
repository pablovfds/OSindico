package br.com.edu.ufcg.osindico.homeSyndic.mvp;

public interface HomeSyndicContract {

    interface Model {

        interface HomeSyndicListener{

        }

    }

    interface Presenter {
        void onItemClicked(int id);
        void setView(HomeSyndicContract.View view);
        void onDestroy();
    }

    interface View {
        void navigateToDwellerList();
        void navigateToDwellerRequests();
        void navigateToCondoRules();
        void navigateToServiceRequestList();
    }
}
