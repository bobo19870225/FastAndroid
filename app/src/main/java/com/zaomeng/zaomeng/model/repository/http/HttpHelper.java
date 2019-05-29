package com.zaomeng.zaomeng.model.repository.http;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;

import com.zaomeng.zaomeng.model.repository.dataBase.UserDao;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.BodyBean;
import com.zaomeng.zaomeng.model.repository.http.bean.LoginBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBodyBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.zaomeng.zaomeng.utils.SystemParameter.siteID;

/**
 * Created by Sampson on 2019-04-24.
 * FastAndroid
 */

public class HttpHelper<T> {

    private ApiService apiService;

    private UserDao userDao;


    private Context context;


    public HttpHelper(Context context, ApiService apiService, UserDao userDao) {
        this.context = context;
        this.apiService = apiService;
        this.userDao = userDao;
    }


    public T AnalyticalData(Resource<? extends Bean<T>> data, InterfaceLogin interfaceLogin, LifecycleOwner owner) {
        if (data.isSuccess()) {
            Bean<T> resource = data.getResource();
            if (resource != null) {
                int code = resource.getHeader().getCode();
                if (code == 0) {
                    return resource.getBody().getData();
                } else if (code == 10000) {
                    doreLogin(interfaceLogin, owner);
                } else {
                    Toast.makeText(context, resource.getHeader().getMsg(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Throwable error = data.getError();
            if (error != null) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return null;
    }

    private void doreLogin(InterfaceLogin interfaceLogin, LifecycleOwner owner) {
        String[] loginInfo = SharedPreferencesUtils.getLoginInfo(context);
        if (loginInfo != null && loginInfo[0] != null && loginInfo[1] != null) {
            apiService.appLogin(loginInfo[0], loginInfo[1], siteID, "1").observe(owner, beanResource -> {
                if (beanResource.isSuccess()) {
                    Bean<LoginBean> loginBeanBean = beanResource.getResource();
                    if (loginBeanBean != null) {
                        if (loginBeanBean.getHeader().getCode() == 0) {
                            BodyBean<LoginBean> body = loginBeanBean.getBody();
                            LoginBean loginBean = body.getData();
                            if (loginBean != null) {
                                String sessionID = body.getSessionID();
                                SharedPreferencesUtils.saveSessionID(context, sessionID);
                                SharedPreferencesUtils.saveMemberID(context, loginBean.getId());
                                ExecutorService DB_IO = Executors.newFixedThreadPool(2);
                                DB_IO.execute(() -> {
                                    userDao.InsertDateUser(loginBean);
                                    DB_IO.shutdown();//关闭线程
                                });
                            }
                            interfaceLogin.reLoad();
                        } else {
                            toast(loginBeanBean.getHeader().getMsg());
                        }
                    }
                } else {
                    Throwable error = beanResource.getError();
                    if (error != null) {
                        toast(error.toString());
                    }
                }
            });
        } else {
            interfaceLogin.skipLoginActivity();
        }
    }

    public BodyBean<T> AnalyticalDataBody(Resource<? extends Bean<T>> data, InterfaceLogin interfaceLogin, LifecycleOwner owner) {
        if (data.isSuccess()) {
            Bean<T> resource = data.getResource();
            if (resource != null) {
                int code = resource.getHeader().getCode();
                if (code == 0) {
                    return resource.getBody();
                } else if (code == 10000) {
                    doreLogin(interfaceLogin, owner);
                } else {
                    Toast.makeText(context, resource.getHeader().getMsg(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Throwable error = data.getError();
            if (error != null) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return null;
    }

    public PageDataBean<T> AnalyticalPageData(Resource<? extends PageBean<T>> data, InterfaceLogin interfaceLogin, LifecycleOwner owner) {
        if (data.isSuccess()) {
            PageBean<T> pageBean = data.getResource();
            if (pageBean != null) {
                int code = pageBean.getHeader().getCode();
                if (code == 0) {
                    return pageBean.getBody().getData();
                } else if (code == 10000) {
                    doreLogin(interfaceLogin, owner);
                } else {
                    Toast.makeText(context, pageBean.getHeader().getMsg(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Throwable error = data.getError();
            if (error != null) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return null;
    }

    public PageBodyBean<T> AnalyticalPageDataBody(Resource<? extends PageBean<T>> data, InterfaceLogin interfaceLogin, LifecycleOwner owner) {
        if (data.isSuccess()) {
            PageBean<T> resource = data.getResource();
            if (resource != null) {
                int code = resource.getHeader().getCode();
                if (code == 0) {
                    return resource.getBody();
                } else if (code == 10000) {
                    doreLogin(interfaceLogin, owner);
                } else {
                    toast(resource.getHeader().getMsg());
                }
            }
        } else {
            Throwable error = data.getError();
            if (error != null) {
                toast(error.toString());
            }
        }
        return null;
    }

    private void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

}
