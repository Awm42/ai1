package aip1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class View1 implements Initializable {

    @FXML private ImageView imagen1 = new ImageView();
    public static ImageView img2CImg1 = new ImageView();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        img2CImg1 = imagen1;
    } 
}
