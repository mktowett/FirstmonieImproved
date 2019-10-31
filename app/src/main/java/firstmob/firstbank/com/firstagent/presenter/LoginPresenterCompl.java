package firstmob.firstbank.com.firstagent.presenter;

import android.os.Handler;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import firstmob.firstbank.com.firstagent.Activity.ApplicationClass;
import firstmob.firstbank.com.firstagent.contract.MainContract;
import firstmob.firstbank.com.firstagent.security.SecurityLayer;
import firstmob.firstbank.com.firstagent.utils.Utility;

public class LoginPresenterCompl implements MainContract.Presenter, MainContract.GetDataIntractor.OnFinishedListener {
    MainContract.ILoginView iLoginView;
    Handler handler;
    private MainContract.GetDataIntractor getDataIntractor;

    @Inject
    SecurityLayer sl;

    @Inject
    Utility ul;

    public LoginPresenterCompl(MainContract.ILoginView iLoginView, MainContract.GetDataIntractor getDataIntractor) {
        this.iLoginView = iLoginView;
        this.getDataIntractor = getDataIntractor;

        ApplicationClass.getMyComponent().inject(this);
        // initUser();
    }


    @Override
    public void doLogin(String name) {

        //   ul.checkpermissions();

        iLoginView.showProgress();

        String endpoint = "otp/generateotp.action/";
        String params = "1/" + name;
        String urlparams = "";
        try {
            urlparams = sl.firstLogin(params, endpoint);
            //SecurityLayerStanbic.Log("cbcurl",url);
            SecurityLayer.Log("RefURL", urlparams);
            SecurityLayer.Log("refurl", urlparams);
            SecurityLayer.Log("params", params);
        } catch (Exception e) {
            SecurityLayer.Log("encryptionerror", e.toString());
        }

        getDataIntractor.getResults(this, urlparams);

    }


    @Override
    public void onFinished(String responsebody) {

        try {
            // JSON Object
            SecurityLayer.Log("response..:", responsebody);


            JSONObject obj = new JSONObject(responsebody);
                 /*   JSONObject jsdatarsp = obj.optJSONObject("data");
                    SecurityLayer.Log("JSdata resp", jsdatarsp.toString());
                    //obj = Utility.onresp(obj,getActivity()); */
            obj = SecurityLayer.decryptFirstTimeLogin(obj);
            SecurityLayer.Log("decrypted_response", obj.toString());

            String respcode = obj.optString("responseCode");
            String responsemessage = obj.optString("message");
            //session.setString(SecurityLayer.KEY_APP_ID,appid);

            if (ul.isNotNull(respcode) && ul.isNotNull(responsemessage)) {
                SecurityLayer.Log("Response Message", responsemessage);

                if (respcode.equals("00")) {
                    JSONObject datas = obj.optJSONObject("data");
                    iLoginView.showToast("SUCCESS");
                } else {

                    iLoginView.showToast(responsemessage);
                }

            } else {


                iLoginView.showToast("There was an error on your request");


            }

        } catch (JSONException e) {
            SecurityLayer.Log("encryptionJSONException", e.toString());
            // TODO Auto-generated catch block
            iLoginView.showToast("There was an error on your request");
            // SecurityLayer.Log(e.toString());

        } catch (Exception e) {
            SecurityLayer.Log("encryptionJSONException", e.toString());
            iLoginView.showToast("There was an error on your request");
            // SecurityLayer.Log(e.toString());
        }
        //   iLoginView.onLoginResult(response);
        iLoginView.hideProgress();
    }

    @Override
    public void onFailure(Throwable t) {
        iLoginView.hideProgress();
        iLoginView.onLoginError(t.toString());

    }

    @Override
    public void ondestroy() {
        iLoginView = null;
    }


}