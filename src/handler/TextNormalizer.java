package handler;

import io.FileIO;
import common.FilePath;

public class TextNormalizer {

    private String fixALlWhitespace(String line) {
        line = line.replaceAll(" +", " ");

        return line;
    }

    private String capitlizeFirstLetter(String part) {
        return part.substring(0, 1).toUpperCase() + part.substring(1);
    }

    private String fixDot(String line) {
        String part[] = line.split("[.]");
        line = "";
        for (int i = 0; i < part.length; i++) {
            part[i] = capitlizeFirstLetter(part[i].trim());
            line = line + part[i].trim() + ".";
        }

        line = line.replaceAll("[.]", ". ");
        return line;
    }

    private String fixComma(String line) {
        String part[] = line.split("[,]");
        line = "";
        for (int i = 0; i < part.length - 1; i++) {
            line = line + part[i].trim() + ",";
        }

        line = line + part[part.length - 1].trim();
        line = line.replaceAll("[,]", ", ");

        return line;
    }

    private String fixColon(String line) {
        String part[] = line.split("[:]");
        line = "";
        for (int i = 0; i < part.length - 1; i++) {
            line = line + part[i].trim() + ": ";
        }

        line = line + part[part.length - 1].trim();
        return line;
    }

    private String fixQuotes(String line) {
        String part[] = line.split("\"");
        line = "";

        for (int i = 0; i < part.length - 1; i++) {
            part[i] = part[i].trim();
            if (i % 2 == 0) {
                // ..."
                line = line + part[i].trim() + " \"";
            } else {
                // "...
                line = line + part[i].trim() + "\"";
            }
        }

        line = line + part[part.length - 1];
        return line;
    }

    private String fixALlSigns(String line) {
        line = fixComma(line);
        line = fixDot(line);
        line = fixColon(line);
        line = fixQuotes(line);

        return line;
    }

    private String repairEachLine(String line) {
        line = fixALlWhitespace(line);
        line = fixALlSigns(line);

        return line;
    }

    public String splitAndRepair(String data) {
        String repairedFullData = "";
        String dataLine[] = data.split("\n");

        for (String line : dataLine) {
            if (line.isEmpty()) {
                continue;
            }
            repairedFullData = repairedFullData + repairEachLine(line) + "\n";
        }

        return repairedFullData;
    }

    public void repair() {
        FileIO fileIO = new FileIO();
        String data = fileIO.readFile(FilePath.IN_PATH);

        String normalizedData = this.splitAndRepair(data);
        fileIO.writeFile(FilePath.OUT_PATH, normalizedData);
    }
}
