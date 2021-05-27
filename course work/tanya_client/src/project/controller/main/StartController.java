package project.controller.main;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import project.Main;
import project.entity.Employee;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class StartController {

    private Main main;

    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passField;

    @FXML
    public void login() {
        String login = loginField.getText();
        String pass = passField.getText();
        if (login != null & pass != null && !login.isEmpty() && !pass.isEmpty()) {
            if (login.length() >= 8 & pass.length() >= 8) {
                Employee employee = new Employee();
                employee.setEmail(login);
                employee.setPass(pass);

                Employee logined = loginEmployee(employee);

                if (logined != null) {
                    main.setEmployee(loginEmployee(employee));
                    main.initRootLayout();

                    String fullName = logined.getPosition() + " " + logined.getSurname() + " " + logined.getName() + " " + logined.getPatronymic();
                    showNotification(fullName);
                }
            } else showAlert();
        } else showAlert();
    }

    private Employee loginEmployee(Employee employee) {
        try {
            URL url = new URL("http://localhost:8080/employees/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            String json = new Gson().toJson(employee);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
                os.flush();
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                System.out.println(response.toString());
                return new Gson().fromJson(response.toString(), Employee.class);
            }

        } catch (Exception e) {
            showAlert();
            return null;
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle("Ошибка авторизации");
        alert.setHeaderText("Ввод неккоректный или данные неверны");
        alert.setContentText("Логин и пароль должны состоять из минимум 8 символов.");
        alert.showAndWait();
    }

    private void showNotification(String name) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle("Информация");
        alert.setHeaderText("Вы успешно авторизовались как " + name);
        alert.showAndWait();
    }
}
