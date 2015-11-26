package algorithms;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m3-gordon
 */
public class XLFileWriter {

    public void writeToFile(String fileName, int generationLimit, double[] meanArray, double[] bestArray) {
        try {

            FileWriter outFile = null;
            outFile = new FileWriter(fileName);

            try (PrintWriter out1 = new PrintWriter(outFile)) {
                out1.print("Mean\tBest\n");
                for (int i = 0; generationLimit > i; i++) {
                    out1.print(meanArray[i] + "\t");
                    out1.print(bestArray[i] + "\n");
                }
            }
            outFile.close();
        } catch (IOException e) {
            Logger.getLogger(XLFileWriter.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
