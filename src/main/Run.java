package main;

import mvc.model.Data;
import mvc.view.View;
import mvc.controller.Controller;

public class Run {
    public static void main(String[] args) {
        Data data = new Data();
        View view = new View();
        Controller ctrl = new Controller(view, data);

        ctrl.run();
    }
}
