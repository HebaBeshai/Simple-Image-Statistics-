/***********************************************************************************************************************
 * @file  ImageStatistics.java
 * @brief This displays the demon deacon and displays stats on the image
 * @author Heba Beshai
 * @date   Appril 6, 2016
 ***********************************************************************************************************************/
//    import com.sun.prism.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ImageStatistics {
    public static void main(String[] args) throws IOException {
        int[][] image = null; // reference to a 2-D array
        File selectedFile = null; // reference to a File

        // JFileChooser object to select a file from the user’s home directory
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        // Tell user what to do
        JOptionPane.showMessageDialog(null, "Select an ASCII (.txt) file to open");

        // Use the JFileChooser object to select a file. If the user clicks on anything
        // but the OK button, then quit.
        int result = fileChooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "File not selected... quitting.");
            System.exit(0);
        }

        // Ok, so we have a selected file.
        // Get a reference to the file
        selectedFile = fileChooser.getSelectedFile();

        // Call readASCIIImageFromFile() to read in the image data
        image = readASCIIImageFromFile(selectedFile);

        JOptionPane.showMessageDialog(null, "Your image has " + image.length + " pixels of height by "
                + image[0].length + " pixels of width");

        // Display the image array in the canvas
        StdDraw.setXscale(0, image[0].length);
        StdDraw.setYscale(0, image[0].length);
        StdDraw.setPenRadius(0.008);
        int width = 0;
        int length = 0;

        for (int i = 0; i < image.length; i++){
            for (int j =0; j<image[0].length; j++){
                StdDraw.setPenColor(image[i][j], image[i][j], image[i][j]);
                StdDraw.point(i, j);
            }
        }

        //Display the image statistics;lowest intensity pixel, highest intensity pixel
        //the average intensity

        int[] minCoordinates;
        minCoordinates = findSmallest(image); //using method

        StdDraw.text( image[0].length*5/6, image[0].length*3/4, "darkest " +minCoordinates[0] + "," +
                minCoordinates[1]);

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(java.awt.Color.red);
        StdDraw.point(minCoordinates[0], minCoordinates[1]);

        // For the brightest pixel now
        int[] maxCoordinates;
        maxCoordinates = findLargest(image); //using method

        StdDraw.text(image[0].length*5/6, image[0].length*2/4, "brightest " +maxCoordinates[0] + "," +
                maxCoordinates[1]);

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(java.awt.Color.blue);
        StdDraw.point(maxCoordinates[0], maxCoordinates[1]);

        aver(image);


    }
    //Defining the Method
//}      //Method to find the location of a pixel with lowest intensity
    public static int[] findSmallest(int[][] myimage){
        int[]location = new int[2];
        int minE = 0;
        int minC = 10000;
        //Loops to find the location of the minimum intensity pixel
        int min = myimage[0][0];
        int minI=0, minJ=0;
        for (int i = 0; i< myimage.length; i++){
            for (int j =0; j< myimage[i].length;j++){
                if(minC > myimage[i][j]){
                    minC = myimage[i][j];
                    minI = i;
                    minJ = j;

                }

            }


        }
        location[0] = minI;
        location[1] = minJ;
        //return array location
        return location;
    }
    public static int[] findLargest(int[][] myimage){
        int[]location = new int[2];
        //Loops to find the location of the minimum intensity pixel
        int max = myimage[0][0];
        int maxI=0, maxJ=0;
        for (int i = 0; i< myimage.length; i++){
            for (int j =0; j<myimage[i].length;j++){
                    if (myimage[i][j] > max) {
                        max = myimage[i][j];
                        maxI = i;
                        maxJ = j;
                }
            }
        }

        location[0] = maxI;
        location[1] = maxJ;
        //return array location
        return location;
    }


    public static int[][] readASCIIImageFromFile(File selectedFile) throws IOException {
        Scanner in;
        in = new Scanner(selectedFile); // read data from the file instead of System.in


        // Read in the width and height
        int width, height;
        int[][] myimage;

        height = in.nextInt();
        width = in.nextInt();

        // create the 2-D array for the image of width and height
        myimage = new int[height][width];

        // Read in the data
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                myimage[i][width-j-1] = in.nextInt();
            }
        }
        return myimage;
    }


    public static int aver(int myimage[][]) {
    //    int max = 0;
       int maxI = 0, maxJ = 0;
        int sum = 0;
        int counter = 0;
        int average = 0;
        int[] location = new int[2];
        for (int i = 0; i < myimage.length; i++) {
            for (int j = 0; j < myimage[i].length; j++) {
                sum = myimage[i][j] + sum;
              counter++;

               /* if (myimage[i][j] > max) {
                    max = myimage[i][j];
                    maxI = i;
                    maxJ = j;


                }*/
            }
        }
       average = sum / counter;
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.setPenRadius(5);
        System.out.println(average);
       // StdDraw.text(100, 90, " brightest pixel is " + max + " at ( " + +maxI + "," + maxJ + " )");
        StdDraw.text(100, 110, "average pixel brightness is " + average);


        return average;
    }

}





