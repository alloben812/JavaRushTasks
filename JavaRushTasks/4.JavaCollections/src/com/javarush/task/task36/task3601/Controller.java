package com.javarush.task.task36.task3601;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NazarenkoDS on 16.06.2017.
 */
public class Controller {
    Model model = new Model();
    public List<String> onDataListShow() {
        return model.getStringDataList();
    }

}
