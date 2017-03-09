package br.com.edu.ufcg.osindico.navigationSyndic.mvp;

import android.content.SharedPreferences;

public interface NavigationSyndicContract {
    interface Model {
        interface OnNavigationSyndicListener {
            void onSuccessLogout();
        }
        void logout(SharedPreferences preferences, OnNavigationSyndicListener listener);
    }

    interface Presenter {
        void onItemClicked(int id);

        void setView(View view);
        void onDestroy();
    }

    interface View {
        void navigateToCondoDetails();
        void navigateToSyndicMessages();
        void navigateToSyndicCalendar();
        void navigateToSettings();
        void navigateToAbout();
        void navigateToLogin();
    }
}
