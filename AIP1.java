package aip1;



import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import org.opencv.core.Core;
import javafx.embed.swing.SwingFXUtils;
/**
 *
 * @author mogue
 */
public class AIP1 extends Application{
    
    public AIP1(){
        
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view1.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        AIP1 a = new AIP1();
        a.loadImage();
        launch(args);
    }
    
    public void loadImage(){
        URL url_img = getClass().getResource("1.jpg");
        String ruta = url_img.getPath();
        
        Mat imagen;
         if (ruta.startsWith("/")) {
            ruta = ruta.substring(1);
        }
         System.out.println(ruta);
        imagen = Imgcodecs.imread(ruta,CV_LOAD_IMAGE_GRAYSCALE);
        System.out.println(imagen);
        if(!imagen.empty()){
            System.out.println("if");
            //Image imagenMostrar = convertir(imagen);
            BufferedImage bufImage =  Mat2BufferedImage(imagen);
            Image imagenMostrar = SwingFXUtils.toFXImage(bufImage, null);
            View1.img2CImg1.setImage(new Image(getClass().getResource("/aip1/1.jpg").toExternalForm()));
        }else{
            System.out.println("fuck!");
        }
    }
    

    private Image convertir(Mat imagen) {
        MatOfByte byteMat = new MatOfByte();
        Imgcodecs.imencode(".bmp", imagen, byteMat);
        return new Image(new ByteArrayInputStream(byteMat.toArray()));
    }
    
    public static BufferedImage Mat2BufferedImage(Mat m){

        // source: http://answers.opencv.org/question/10344/opencv-java-load-image-to-gui/
        // Fastest code
        // The output can be assigned either to a BufferedImage or to an Image

        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);  
        return image;
    }
    
}
    

