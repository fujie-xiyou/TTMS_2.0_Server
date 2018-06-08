package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.CalendarTextField;
import jfxtras.scene.control.CalendarTimePicker;
import jfxtras.scene.control.CalendarTimeTextField;

public class TimeDicker extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new CalendarPicker()));
        primaryStage.show();
    }
}
