import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DatabaseSingleItemSceneController extends MainController {

    @FXML
    private VBox detailsVBox;

    // Method to set the object and populate details
    public void setObject(Object obj) {
        detailsVBox.getChildren().clear(); // Clear existing content in the VBox

        if (obj instanceof Customer) {
            Customer customer = (Customer) obj;
            addFieldWithLabel("Username: ", customer.getUsername());
            addFieldWithLabel("Password: ", customer.getPassword());
            addFieldWithLabel("Date of Birth: ", customer.getDateOfBirth());
            addFieldWithLabel("Gender: ", customer.getGender().toString());
            addFieldWithLabel("Address: ", customer.getAddress());
            addFieldWithLabel("Balance: ", String.valueOf(customer.getBalance()));
        } else if (obj instanceof Admin) {
            Admin admin = (Admin) obj;
            addFieldWithLabel("Username: ", admin.getUsername());
            addFieldWithLabel("Password: ", admin.getPassword());
            addFieldWithLabel("Date of Birth: ", admin.getDateOfBirth());
            addFieldWithLabel("Gender: ", admin.getGender().toString());
            addFieldWithLabel("Role: ", admin.getRole());
            addFieldWithLabel("Working Hours: ", String.valueOf(admin.getWorkingHours()));
        } else if (obj instanceof Product) {
            Product product = (Product) obj;
            addFieldWithLabel("Name: ", product.getName());
            addFieldWithLabel("Price: ", String.valueOf(product.getPrice()));
            addFieldWithLabel("Number: ", String.valueOf(product.getnumberOfSameKind()));
            addFieldWithLabel("Category: ", product.getCategory());
        } else if (obj instanceof Category) {
            Category category = (Category) obj;
            addFieldWithLabel("Name: ", category.getCatName());
        }

        // Add Delete and Update Buttons
        addActionButtons(obj);
    }

    // Helper method to create a Label and TextField pair
    private void addFieldWithLabel(String labelText, String fieldValue) {
        HBox hbox = new HBox();
        hbox.setSpacing(10);

        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        TextField textField = new TextField(fieldValue);
        textField.setPrefWidth(200);

        hbox.getChildren().addAll(label, textField);
        detailsVBox.getChildren().add(hbox);
    }

    // Helper method to add action buttons
    private void addActionButtons(Object obj) {
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(20);

        // Delete Button
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #ff4c4c; -fx-text-fill: white; -fx-font-weight: bold;");
        deleteButton.setOnAction(event -> {
            System.out.println("Delete action triggered for: " + obj);

            // Call the specific delete method based on the object type
            try{
            if (obj instanceof Customer) {
               
                Database.removeUser((Customer) obj);
            } else if (obj instanceof Admin) {
                Database.removeUser((Admin) obj);
            } else if (obj instanceof Product) {
                Database.removeProduct((Product) obj);
            } else if (obj instanceof Category) {
                Database.removeCategory((Category) obj);
            }

            System.out.println("Object deleted: " + obj);
            Platform.runLater(() -> detailsVBox.getChildren().clear());}
            catch(Exception e){
                System.out.println(e);
            }
        });

        buttonBox.getChildren().add(deleteButton);

        // Conditionally add Update Button for non-Category objects
        if (!(obj instanceof Category)) {
            Button updateButton = new Button("Update");
            updateButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-weight: bold;");
            updateButton.setOnAction(event -> {
                System.out.println("Update action triggered. New values:");

                // Extract and update values from text fields
                for (int i = 0; i < detailsVBox.getChildren().size() - 1; i++) {
                    HBox hbox = (HBox) detailsVBox.getChildren().get(i);
                    TextField textField = (TextField) hbox.getChildren().get(1);
                    String newValue = textField.getText();
                    Label label = (Label) hbox.getChildren().get(0);

                    // Match the label text and call corresponding update method
                    if (obj instanceof Customer) {
                        Customer customer = (Customer) obj;
                        if (label.getText().startsWith("Username")) {
                            Database.updateUsername(customer, newValue);
                        } else if (label.getText().startsWith("Password")) {
                            Database.updatePassword(customer, newValue);
                        } else if (label.getText().startsWith("Date of Birth")) {
                            Database.updateDateOfBirth(customer, newValue);
                        } else if (label.getText().startsWith("Address")) {
                            Database.updateCustomerAddress(customer, newValue);
                        } else if (label.getText().startsWith("Balance")) {
                            Database.updateCustomerBalance(customer, Integer.parseInt(newValue));
                        }
                    } else if (obj instanceof Admin) {
                        Admin admin = (Admin) obj;
                        if (label.getText().startsWith("Username")) {
                            Database.updateUsername(admin, newValue);
                        } else if (label.getText().startsWith("Password")) {
                            Database.updatePassword(admin, newValue);
                        } else if (label.getText().startsWith("Date of Birth")) {
                            Database.updateDateOfBirth(admin, newValue);
                        } else if (label.getText().startsWith("Role")) {
                            Database.updateAdminRole(admin, newValue);
                        } else if (label.getText().startsWith("Working Hours")) {
                            Database.updateAdminWorkingHours(admin, Double.parseDouble(newValue));
                        }
                    } else if (obj instanceof Product) {
                        Product product = (Product) obj;
                        if (label.getText().startsWith("Name")) {
                            Database.updateProductName(product, newValue);
                        } else if (label.getText().startsWith("Price")) {
                            Database.updateProductPrice(product, Float.parseFloat(newValue));
                        } else if (label.getText().startsWith("Number")) {
                            Database.updateProductNumber(product, Integer.parseInt(newValue));
                        } else if (label.getText().startsWith("Category")) {
                            Database.updateProductCategoryName(product, newValue);
                        }
                    }
                }
            });

            buttonBox.getChildren().add(updateButton);
        }

        detailsVBox.getChildren().add(buttonBox);
    }
}
