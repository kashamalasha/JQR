package mvc.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import mvc.model.Data;
import mvc.view.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;

public class Controller {
    //Attributes
    private final View view;
    private final Data data;

    private Scanner sc = new Scanner(System.in);

    //Constructor
    public Controller(View view, Data data) {
        this.view = view;
        this.data = data;
    }

    //Methods
    //Select an item in the main menu
    private int getMenuItem() {
        view.showMainMenu();
        if ((sc.hasNextInt())) {
            return sc.nextInt();
        } else {
            sc.next();
            return this.getMenuItem();
        }
    }

    private void setData() {
        view.showDataAsk();
        data.setInputData(sc.next());
    }

    public void run() {
        String filePath = "/Users/Dmitry.Burnyshev/IdeaProjects/JQR/QR.png";
        int size = 60;
        String fileType = "png";
        File myFile = new File(filePath);

        switch (this.getMenuItem()) {
            case 1: //Generate a QR
                this.setData();
                try {
                    Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap =
                            new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
                    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                    QRCodeWriter qrCodeWriter = new QRCodeWriter();
                    BitMatrix byteMatrix =
                            qrCodeWriter.encode(data.getInputData(), BarcodeFormat.QR_CODE, size, size, hintMap);
                    int width = byteMatrix.getWidth();
                    BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
                    image.createGraphics();

                    Graphics2D graphics = (Graphics2D) image.getGraphics();
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect(0, 0, width, width);
                    graphics.setColor(Color.BLACK);

                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < width; j++) {
                            if (byteMatrix.get(i, j)) {
                                graphics.fillRect(i, j, 1, 1);
                            }
                        }
                    }
                    ImageIO.write(image, fileType, myFile);
                } catch (WriterException | IOException e) {
                    e.printStackTrace();
                }
                view.showTheResult(filePath);
                break;
            case 0: //Exit
                System.exit(0);
                break;
        }
    }
}
