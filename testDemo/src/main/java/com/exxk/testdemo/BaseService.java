package com.exxk.testdemo;

import java.util.ArrayList;
import java.util.List;

public class BaseService {

    public void test(){

        List<BaseInterface> baseInterfaces=new ArrayList<>();
        BaseBean baseBean=new BaseBean();
        baseInterfaces.add(baseBean);
    }
}
