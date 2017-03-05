package br.com.edu.ufcg.osindico.navigationDweller.mvp;

import android.content.SharedPreferences;

public interface NavigationDwellerContract {

    interface Model {

        interface OnLogoutFinishedListener {
            void onSucess();
        }

        void logout(SharedPreferences preferences, OnLogoutFinishedListener listener);
    }

    interface Presenter {
        void onItemClicked(int id);

        void setView(View view);

        void onDestroy();
    }

    interface View {
        void navigateToHomeDweller();
        void navigateToMessageDweller();
        void navigateToSettingsDweller();
        void navigateToAboutDweller();
        void navigateToCalendarDweller();
        void navigateToCondoDetails();
        void navigateToLogin();
    }
}
