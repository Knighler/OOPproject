import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {
    protected static Stage stage;
    protected static Scene CustomerHomePageScene;
    protected static Scene CustomerProductsScene;
    protected static Scene ProfileScene;
    protected static Scene AdminProfileScene;
    protected static Scene AdminDashboardScene;
    protected static Scene DatabaseAllItemsScene;
    protected static Scene DatabaseSingleItemScene;
    protected static Scene AddItemsSceneScene;
    protected static Scene SignUpScene;
    protected static Scene LogInSCene;

    public void setStageAndScene(Stage stage ,  Scene AdminProfileScene  ,Scene AdminDashboardScene,Scene DatabaseAllItemsScene,Scene DatabaseSingleItemScene,Scene AddItemsSceneScene,Scene SignUpScene,Scene LogInSCene) {
        MainController.stage = stage; 
    
        MainController.AdminProfileScene = AdminProfileScene;
        MainController.AdminDashboardScene = AdminDashboardScene;
        MainController.DatabaseAllItemsScene = DatabaseAllItemsScene;
        MainController.DatabaseSingleItemScene = DatabaseSingleItemScene;
        MainController.AddItemsSceneScene = AddItemsSceneScene;
        MainController.SignUpScene = SignUpScene;
        MainController.LogInSCene = LogInSCene;
        System.out.println(stage);
    }
/* 
    public void switchToHome() {
        Platform.runLater(() -> {
            if (stage != null && CustomerHomePageScene != null) {
                stage.setScene(CustomerHomePageScene);
            }
        });
    }

    public void switchToProducts() {
        
        Platform.runLater(() -> {

            if (stage != null && CustomerProductsScene != null) {
                stage.setScene(CustomerProductsScene);
                
            }
        });
    }


    public void switchToProfile() {
        Platform.runLater(() -> {
            if (stage != null && ProfileScene != null) {
                stage.setScene(ProfileScene);
            }
        });
    }*/
    
    public void switchToAdminProfile() {
        Platform.runLater(() -> {
            if (stage != null && AdminProfileScene != null) {
                System.out.println("afhdsf");
            }
        });
    }

    public void switchToAdminDashboard() {
        Platform.runLater(() -> {
            if (stage != null && AdminDashboardScene != null) {
                stage.setScene(AdminDashboardScene);
            }
        });
    }   
    
    public void switchToDatabaseAllItems() {
        Platform.runLater(() -> {
            if (stage != null && DatabaseAllItemsScene != null) {
                DatabaseAllItemsSceneController controller = (DatabaseAllItemsSceneController) DatabaseAllItemsScene.getUserData();
                 

                 DatabaseSingleItemSceneController singleItemController = (DatabaseSingleItemSceneController) DatabaseSingleItemScene.getUserData();
                 controller.initialize(DatabaseSingleItemScene, stage, singleItemController);
                 stage.setScene(DatabaseAllItemsScene);
            }
        });
    }
    
   

    public void switchToAddItems(ActionEvent e) {
        Platform.runLater(() -> {
            if (stage != null && AddItemsSceneScene != null) {
                Button button = (Button) e.getSource();
                String buttonId= button.getId();
                
                
                AddItemsSceneSceneController controller = (AddItemsSceneSceneController) AddItemsSceneScene.getUserData();
                controller.setChoice(buttonId);
                stage.setScene(AddItemsSceneScene);
            }
        });
    }

    
    public void switchToLogIn() {
        Platform.runLater(() -> {
            if (stage != null && SignUpScene != null) {
                stage.setScene(LogInSCene);
            }
        });
    }

    public void switchToSignup() {
        Platform.runLater(() -> {
            if (stage != null && SignUpScene != null) {
                stage.setScene(SignUpScene);
            }
        });
    }


    
        
}
