package handler;

import io.FileIO;
import common.FilePath;

public class TextNormalizer {

    private String repair(String line) {
        // Combine multiple whitespaces to 1 whitespace
        line = line.replaceAll(" +", " ");

        // Delete first whitespace if exist
        if (line.charAt(0) == ' ') {
            line = line.replaceFirst(" ", "");
        }

        // Capitalize first letter of line
        line = line.substring(0, 1).toUpperCase() + line.substring(1);

        // Repair all , . and :
        line = line.replaceAll(" +,+ ", ", ");
        line = line.replaceAll(" +[.]+ ", ". ");
        line = line.replaceAll(" +[:]+ ", ": ");

        // Capitalize letter after .
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '.' && i != line.length() - 1) {
                line = line.substring(0, i + 1)
                        + " " + Character.toUpperCase(line.charAt(i + 2))
                        + line.substring(i + 3);
            }
        }

        // Repair all " "
        int found = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '"') {
                if (found % 2 == 0) {
                    // ..."
                    line = line.substring(0, i + 1)
                            + line.substring(i + 2);
                } else {
                    // "...
                    line = line.substring(0, i - 1)
                            + line.substring(i);
                }
                found++;
            }
        }

        // Add . to the end of line if not existed
        if (line.charAt(line.length() - 1) != '.') {
            line = line.substring(0, line.length()) + ".";
        }
        return line;
    }

    public String splitAndNormalize(String data) {
        String repairedData = "";
        String dataLine[] = data.split("\n");

        for (String line : dataLine) {
            repairedData = repairedData + repair(line) + "\n";
        }

        return repairedData;
    }

    public void normalize() {
        FileIO fileIO = new FileIO();
        
        String data = fileIO.readFile(FilePath.IN_PATH);
        String normalizedData = this.splitAndNormalize(data);
        
        fileIO.writeFile(FilePath.OUT_PATH, normalizedData);
    }
}
