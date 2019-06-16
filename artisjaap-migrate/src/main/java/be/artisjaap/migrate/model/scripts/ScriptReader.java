package be.artisjaap.migrate.model.scripts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScriptReader {
    public static final int COLLECTION = 1;
	public static final int INSERT_SCRIPT = 2;
	public static Pattern MONGO_SAVE_COMMAND = Pattern.compile("db\\.([a-zA-Z]+)\\.save\\((.*)\\)");
	public static Pattern ENDING_LINE = Pattern.compile(";$");

	public static Matcher matchSaveCommand(String line) {
		return MONGO_SAVE_COMMAND.matcher(line);
	}

	public static boolean isEndingLine(String line) {
		return ENDING_LINE.matcher(line).find();
	}

}
