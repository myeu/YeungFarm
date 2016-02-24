import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Marisa Yeung
 */
public class Main extends Application {
    public static void main(String[] args) {
        int DAYS = 30;
        int MINUTES = (DAYS * 60 * 24);

        Timer timer = new Timer();

        Sun sun = new Sun();
        timer.addTimeSubscriber(sun);

        int numberOfTrays = 1;
        int dailyLightInterval = 18;
        Greenhouse g1 = new Greenhouse(timer, sun, numberOfTrays, dailyLightInterval);
        timer.addTimeSubscriber(g1);
        System.out.println(g1.getSetPointDLI() + "");

        int id = 1;
        Tray t1 = new Tray(id);
        System.out.println(t1.getId() + "");
        g1.addTray(t1);
        timer.addTimeSubscriber(t1);

        ExternalTempSimulator externalTempSimulator = new ExternalTempSimulator();
        timer.addTimeSubscriber(externalTempSimulator);

        double initTemp = 70;
        InternalTempSimulator internalTempSimulator = new InternalTempSimulator(initTemp);
        externalTempSimulator.addTempSubscriber(internalTempSimulator);
        timer.addTimeSubscriber(internalTempSimulator);

        HVAC hvac = new HVAC();
        hvac.setPower(true);
        hvac.addSubscriber(internalTempSimulator);
        timer.addTimeSubscriber(hvac);

        Thermostat thermostat = new Thermostat(hvac);
        g1.addThermostat(thermostat);
        internalTempSimulator.addSubscriber(thermostat);
        timer.addTimeSubscriber(thermostat);

        for (int i = 0; i < MINUTES; i++) {
            timer.incrementTime();
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/control_panel.fxml"));
        primaryStage.setTitle("Yeung Farm");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
