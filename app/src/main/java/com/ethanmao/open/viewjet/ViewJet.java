package com.ethanmao.open.viewjet;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Ethan.mao
 * @date 2018/11/6
 *
 */
public class ViewJet {

    /**
     * 注入 Activity
     * @param activity
     */
    public static void bind(Activity activity){
         inject(new ViewFinder(activity),activity);
    }

    /**
     * 注入自定义控件
     * @param view
     */
    public static void bind(View view){
        inject(new ViewFinder(view),view);
    }

    /**
     * 注入 Fragment
     * @param view
     * @param object
     */
    public static void bind(View view, Object object){
        inject(new ViewFinder(view),object);
    }


    private static void inject(ViewFinder viewFinder, Object object) {
        // 注入属性
            injectField(viewFinder,object);
        // 注入方法
            injectMethod(viewFinder,object);
    }



    /**
     * 注入View到类中
     * @param viewFinder
     * @param object
     */
    private static void injectField(ViewFinder viewFinder, Object object) {

        Class clazz = object.getClass();

        Field[] fields = clazz.getDeclaredFields();
        // 检查注解标记
        if (fields != null){
            //遍历属性
            for (Field field: fields) {
                if ( field.isAnnotationPresent(BindView.class)){
                        int id = field.getAnnotation(BindView.class).value();
                        View view =  viewFinder.findViewById(id);
                    // 动态注入变量
                    try {
                        field.setAccessible(true);
                        field.set(object,view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }


            }
        }
    }


    private static void injectMethod(ViewFinder viewFinder, Object object) {
        // 轮询查找注解
            Class clazz = object.getClass();

            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
            OnClick onClick =  method.getAnnotation(OnClick.class);

                if (onClick != null){
                    int ids[] = onClick.value();
                    View view;
                    for (int id : ids){
                      view = viewFinder.findViewById(id);
                        view.setOnClickListener(new DecalerdOnClickListener(method,object));
                    }
                }
            }

    }

    private static class DecalerdOnClickListener implements View.OnClickListener{
        private Method method;
        private Object object;

        public DecalerdOnClickListener(Method method, Object object) {
            this.method = method;
            this.object = object;
        }

        @Override
        public void onClick(View view) {

            try {
                method.setAccessible(true);
                method.invoke(object,view);
            } catch (Exception e){
                e.getStackTrace();
            }
        }
    }




}
