package br.com.edu.ufcg.osindico.data.domain;

import android.os.AsyncTask;

public class ZipCodeRequest {//extends AsyncTask<Void, Void, Void> {
//    private WeakReference<ZipCodeSearchActivity> activity;
//
//    public ZipCodeRequest(ZipCodeSearchActivity activity ){
//        this.activity = new WeakReference<>( activity );
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        if( activity.get() != null ){
//            activity.get().lockFields( true );
//        }
//    }
//
//    @Override
//    protected Void doInBackground(Void... voids) {
//        try {
//            String jsonString = JsonRequest.request( activity.get().getUriZipCode() );
//
//            Gson gson = new Gson();
//            JSONArray jsonArray = new JSONArray(jsonString);
//            activity.get().getAddresses().clear();
//
//            for( int i = 0; i < jsonArray.length(); i++ ){
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                Address a = gson.fromJson( jsonObject.toString(), Address.class );
//                activity.get().getAddresses().add( a );
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void svoid) {
//        super.onPostExecute(svoid);
//
//        if( activity.get() != null ){
//            activity.get().lockFields( false );
//            activity.get().updateListView();
//        }
//    }
}
