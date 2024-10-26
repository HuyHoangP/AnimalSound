package com.hhp.animalsound.view;

import com.hhp.animalsound.model.Animal;

import java.util.List;

public interface OnMainCallback {
    default void callback(String key, Object data){};
    void showFragment(String tag, Object data, Boolean isBack);
    default void setCurrentList(String currentList){};
    default List<Animal> currentList(){
        return null;
    };
}
